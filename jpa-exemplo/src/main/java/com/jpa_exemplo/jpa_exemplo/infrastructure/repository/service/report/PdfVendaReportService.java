package com.jpa_exemplo.jpa_exemplo.infrastructure.repository.service.report;

import com.jpa_exemplo.jpa_exemplo.domain.repository.filter.VendaDiariaFilter;
import com.jpa_exemplo.jpa_exemplo.domain.service.VendaQueryService;
import com.jpa_exemplo.jpa_exemplo.domain.service.VendaReportService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;

@Service
public class PdfVendaReportService implements VendaReportService {

    @Autowired
    private VendaQueryService vendaQueryService;

    @Override
    public byte[] emitirVendasDiarias(VendaDiariaFilter filtro, String timeOffset) {
        try {
            InputStream inputStream = this.getClass().getResourceAsStream("/relatorios/vendas.jasper");

            if (inputStream == null) {
                throw new RuntimeException("Arquivo /relatorios/vendas.jasper não encontrado.");
            }

            var parametros = new HashMap<String, Object>();
            parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));

            var vendasDiarias = vendaQueryService.consultarVendasDiarias(filtro, timeOffset);
            var dataSource = new JRBeanCollectionDataSource(vendasDiarias);

            var jasperPrint = JasperFillManager.fillReport(inputStream, parametros, dataSource);

            return JasperExportManager.exportReportToPdf(jasperPrint);

        } catch (JRException e) {
            throw new RuntimeException("Erro ao gerar relatório PDF de vendas diárias.", e);
        }
    }
}