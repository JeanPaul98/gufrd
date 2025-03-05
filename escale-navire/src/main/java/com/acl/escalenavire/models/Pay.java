package com.acl.escalenavire.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "PAYS")
public class Pay {
    @Id
    @Size(max = 3)
    @Column(name = "CODE_PAYS", nullable = false, length = 3)
    private String codePays;

    @Size(max = 5)
    @Column(name = "INDICATIF_PAYS", length = 5)
    private String indicatifPays;

    @Size(max = 80)
    @NotNull
    @Column(name = "NOM_PAYS", nullable = false, length = 80)
    private String nomPays;

    @Size(max = 60)
    @Column(name = "CAPITALE", length = 60)
    private String capitale;

    @Size(max = 60)
    @NotNull
    @Column(name = "NATIONALITE", nullable = false, length = 60)
    private String nationalite;

}