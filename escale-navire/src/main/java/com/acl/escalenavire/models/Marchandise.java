package com.acl.escalenavire.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "MARCHANDISE")
public class Marchandise {
    @Id
    @Size(max = 15)
    @Column(name = "CODE_MARCHANDISE", nullable = false, length = 15)
    private String codeMarchandise;

    @Size(max = 10)
    @NotNull
    @Column(name = "CODE_SOUS_CATEG_MSE", nullable = false, length = 10)
    private String codeSousCategMse;

    @Size(max = 400)
    @NotNull
    @Column(name = "LIB_MARCHANDISE", nullable = false, length = 400)
    private String libMarchandise;

    @Size(max = 400)
    @Column(name = "MOTS_CLES", length = 400)
    private String motsCles;

    @Size(max = 200)
    @Column(name = "DESC_MSE", length = 200)
    private String descMse;

    @Size(max = 15)
    @Column(name = "NUMERO_UN", length = 15)
    private String numeroUn;

    @Column(name = "QTE_LIMITE", precision = 15, scale = 2)
    private BigDecimal qteLimite;

}