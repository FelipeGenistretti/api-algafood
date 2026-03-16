package com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.endereco;

import com.jpa_exemplo.jpa_exemplo.domain.model.Cidade;
import com.jpa_exemplo.jpa_exemplo.domain.model.Endereco;
import com.jpa_exemplo.jpa_exemplo.domain.model.Estado;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Endereco.Request.EnderecoRequestDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Endereco.Response.CidadeResumoResponseDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Endereco.Response.EnderecoResponseDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-04T22:14:29-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 25 (Oracle Corporation)"
)
@Component
public class EnderecoMapperImpl implements EnderecoMapper {

    @Override
    public Endereco toEntity(EnderecoRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Endereco endereco = new Endereco();

        endereco.setCidade( enderecoRequestDTOToCidade( dto ) );
        endereco.setCep( dto.cep() );
        endereco.setLogradouro( dto.logradouro() );
        endereco.setNumero( dto.numero() );
        endereco.setComplemento( dto.complemento() );
        endereco.setBairro( dto.bairro() );

        return endereco;
    }

    @Override
    public EnderecoResponseDTO toResponse(Endereco entity) {
        if ( entity == null ) {
            return null;
        }

        CidadeResumoResponseDTO cidade = null;
        String cep = null;
        String logradouro = null;
        String numero = null;
        String complemento = null;
        String bairro = null;

        cidade = cidadeToCidadeResumoResponseDTO( entity.getCidade() );
        cep = entity.getCep();
        logradouro = entity.getLogradouro();
        numero = entity.getNumero();
        complemento = entity.getComplemento();
        bairro = entity.getBairro();

        EnderecoResponseDTO enderecoResponseDTO = new EnderecoResponseDTO( cep, logradouro, numero, complemento, bairro, cidade );

        return enderecoResponseDTO;
    }

    @Override
    public List<EnderecoResponseDTO> toCollectionResponse(List<Endereco> endereco) {
        if ( endereco == null ) {
            return null;
        }

        List<EnderecoResponseDTO> list = new ArrayList<EnderecoResponseDTO>( endereco.size() );
        for ( Endereco endereco1 : endereco ) {
            list.add( toResponse( endereco1 ) );
        }

        return list;
    }

    @Override
    public void updateEntity(EnderecoRequestDTO dto, Endereco endereco) {
        if ( dto == null ) {
            return;
        }

        if ( endereco.getCidade() == null ) {
            endereco.setCidade( new Cidade() );
        }
        enderecoRequestDTOToCidade1( dto, endereco.getCidade() );
        endereco.setCep( dto.cep() );
        endereco.setLogradouro( dto.logradouro() );
        endereco.setNumero( dto.numero() );
        endereco.setComplemento( dto.complemento() );
        endereco.setBairro( dto.bairro() );
    }

    protected Cidade enderecoRequestDTOToCidade(EnderecoRequestDTO enderecoRequestDTO) {
        if ( enderecoRequestDTO == null ) {
            return null;
        }

        Cidade cidade = new Cidade();

        cidade.setId( enderecoRequestDTO.cidadeId() );

        return cidade;
    }

    private String cidadeEstadoNome(Cidade cidade) {
        if ( cidade == null ) {
            return null;
        }
        Estado estado = cidade.getEstado();
        if ( estado == null ) {
            return null;
        }
        String nome = estado.getNome();
        if ( nome == null ) {
            return null;
        }
        return nome;
    }

    protected CidadeResumoResponseDTO cidadeToCidadeResumoResponseDTO(Cidade cidade) {
        if ( cidade == null ) {
            return null;
        }

        String estado = null;
        Long id = null;
        String nome = null;

        estado = cidadeEstadoNome( cidade );
        id = cidade.getId();
        nome = cidade.getNome();

        CidadeResumoResponseDTO cidadeResumoResponseDTO = new CidadeResumoResponseDTO( id, nome, estado );

        return cidadeResumoResponseDTO;
    }

    protected void enderecoRequestDTOToCidade1(EnderecoRequestDTO enderecoRequestDTO, Cidade mappingTarget) {
        if ( enderecoRequestDTO == null ) {
            return;
        }

        mappingTarget.setId( enderecoRequestDTO.cidadeId() );
    }
}
