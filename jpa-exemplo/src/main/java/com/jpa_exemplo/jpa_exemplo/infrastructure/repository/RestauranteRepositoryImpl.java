package com.jpa_exemplo.jpa_exemplo.infrastructure.repository;
import com.jpa_exemplo.jpa_exemplo.domain.model.Restaurante;
import com.jpa_exemplo.jpa_exemplo.domain.repository.RestauranteRepository;
import com.jpa_exemplo.jpa_exemplo.domain.repository.RestauranteRepositoryCustom;
import com.jpa_exemplo.jpa_exemplo.infrastructure.repository.Spec.RestauranteSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryCustom {

    @Autowired @Lazy
    private RestauranteRepository restauranteRepository;

    @Override
    public List<Restaurante> findComFreteGratis(String nome) {
        return restauranteRepository.findAll(
                RestauranteSpecs.comFreteGratis().and(RestauranteSpecs.comNomeSemelhante(nome))
        );


    }
}

