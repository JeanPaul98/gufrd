package com.acl.formaliteagricultureapi.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

/**
 * @author kol on 29/09/2024
 * @project formalite-agriculture-api
 */
@Entity
@Table(name = "REDEVANCE", schema = "USERFMA")
@Getter
@Setter
@NoArgsConstructor
@Schema(implementation = Redevance.class)
public class Redevance implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_REDEVANCE")
    @SequenceGenerator(sequenceName = "S_REDEVANCE", allocationSize = 1, name = "S_REDEVANCE")
    @Column(name = "ID")
    private Long id;

    @Column(name = "DEMANDE")
    private String demande;

    @Column(name = "MONTANT")
    private double montant;

    @Column(name = "STATUS")
    private Boolean status;

    @Column(name = "DATE_DEMANDE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDemande;

    @ManyToOne
    @JoinColumn(name = "ID_FORMALITE")
    private Formalite formalite;

    public Redevance(Formalite formalite, String reference, double montant) {
        this.formalite = formalite;
        this.montant = montant;
        this.demande = reference;
    }

    public Redevance(String reference, double montant) {
        this.montant = montant;
        this.demande = reference;
    }
}
