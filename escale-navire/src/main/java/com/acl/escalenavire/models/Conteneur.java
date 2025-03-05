package com.acl.escalenavire.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "CONTENEUR")
public class Conteneur {
    @Id
    @Size(max = 30)
    @Column(name = "NUM_CONTENEUR", nullable = false, length = 30)
    private String numConteneur;

    @NotNull
    @Column(name = "INTERDIT", nullable = false)
    private Boolean interdit = false;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "LIBRE", nullable = false)
    private Boolean libre = false;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "VIDE", nullable = false)
    private Boolean vide = false;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "SORTIE_MARITIME", nullable = false)
    private Boolean sortieMaritime = false;

}