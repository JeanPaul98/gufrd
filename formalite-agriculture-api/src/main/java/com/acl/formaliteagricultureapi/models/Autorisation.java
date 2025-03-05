/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acl.formaliteagricultureapi.models;



import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


/**
 *
 * @author Olivier
 */
@Entity
@Table(name = "AUTORISATION", schema = "USERFMA")
@Getter
@Setter
@NoArgsConstructor
@Schema(implementation = Autorisation.class)
public class Autorisation implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_AUTORISATION")
    @SequenceGenerator(sequenceName = "S_AUTORISATION", allocationSize = 1, name = "S_AUTORISATION")
    @Column(name = "ID")
    private Long id;


    @Column(name = "PROVENANCE")
    private String provenance;

    @Column(name = "NUMERO_AUTORISATION")
    private String numroAutorisation;


    @Column(name = "DESIGNATION")
    private String designation;

    @Column(name = "DATE_EMBARQUEMENT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateembarquement;


    @Column(name = "DATE_ARRIVEE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datearrivee;

    @Column(name = "NOMBRE_TOTAL")
    private int nombretotal;

    @Column(name = "CONTENEUR")
    private String conteneur;

    @Column(name = "MOTIF_REFUS")
    private String motifRefus;

    @Column(name = "OBSERVATION")
    private String observation;

    @Column(name = "NUMERO_BL")
    private String numeroBL;


    @Column(name = "ACCORDER")
    private int accorder;
    
    @Column(name = "MONTANT_REDEVANCE")
    private double montantRedevance;
    
    @Column(name = "REDEVANCE_PAYER")
    private boolean redevancePayer;

    
    @JoinColumn(name = "ID_TYPE_AUTORISATION", referencedColumnName = "id")
    @ManyToOne
    private TypeAutorisation typeAutorisation;

    @OneToOne(mappedBy = "autorisation")
    private Formalite formalite;

    public Autorisation(String provenance,Date datearrivee, Date DateEmbarquement){
        this.provenance = provenance;
        this.datearrivee = datearrivee;
        this.dateembarquement = DateEmbarquement;
    }

    public Autorisation(String conteneur, Date datearrivee, String designation, String provenance) {
        this.conteneur= conteneur;
        this.designation=designation;
        this.provenance = provenance;
        this.datearrivee=datearrivee;
    }


    public Autorisation(String provenance, Date dateembarquement) {
        this.provenance = provenance;
        this.dateembarquement = dateembarquement;
    }

    public Autorisation(String provenance) {
        this.provenance = provenance;
    }
}
