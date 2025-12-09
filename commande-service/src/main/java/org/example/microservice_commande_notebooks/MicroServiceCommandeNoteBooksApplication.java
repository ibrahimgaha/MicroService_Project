package org.example.microservice_commande_notebooks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = {"org.example.microservice_commande_notebooks.entities"})
@EnableJpaRepositories(basePackages = {"org.example.microservice_commande_notebooks.repositories"})
public class MicroServiceCommandeNoteBooksApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroServiceCommandeNoteBooksApplication.class, args);
    }
}
