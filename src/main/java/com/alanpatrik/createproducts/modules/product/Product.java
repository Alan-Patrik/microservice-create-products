package com.alanpatrik.createproducts.modules.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.implementation.bind.annotation.Empty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @NotNull(message = "Campo NOME é obrigatório!")
    @Size(min = 3, message = "No campo NOME deve ter no mínimo 3 caracteres.")
    @Column(name = "nome")
    private String name;

    @NotNull(message = "Campo PREÇO é obrigatório!")
    @Column(name = "preco")
    private double price;

    @NotNull(message = "Campo QUANTIDADE é obrigatório!")
    @Column(name = "quantidade")
    private int quantity;

    @CreatedDate
    @Column(name = "data_criacao")
    private LocalDateTime creationDate;

    @LastModifiedDate
    @Column(name = "ultima_atualizacao")
    private LocalDateTime lastModifiedDate;
}
