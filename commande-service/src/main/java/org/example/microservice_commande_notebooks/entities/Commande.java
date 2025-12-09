package org.example.microservice_commande_notebooks.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


    @Entity
    @Table(name = "commandes")
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public class Commande {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String clientName;
        private String productName;
        private Integer quantity;
        private Double price;
        private LocalDateTime orderDate;
        private String status; // e.g. PENDING, SHIPPED, DELIVERED


}
