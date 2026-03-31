package com.jpa_exemplo.jpa_exemplo;

import com.jpa_exemplo.jpa_exemplo.domain.repository.CustomJpaRepository;
import com.jpa_exemplo.jpa_exemplo.infrastructure.repository.CustomJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(
        basePackages = "com.jpa_exemplo.jpa_exemplo.domain.repository",
        repositoryBaseClass = CustomJpaRepositoryImpl.class
)
public class JpaExemploApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaExemploApplication.class, args);
	}

}
