package com.alanpatrik.createproducts.modules.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
@EntityListeners(AuditingEntityListener.class)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String name;

    @Column(name = "preco")
    private double price;

    @Column(name = "quantidade")
    private int quantity;

    @CreatedDate
    @Column(name = "data_criacao")
    private LocalDateTime creationDate;

    @LastModifiedDate
    @Column(name = "ultima_atualizacao")
    private LocalDateTime lastModifiedDate;
}
