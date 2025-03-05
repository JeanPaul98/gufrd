package com.acl.formalitesanteapi.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author kol on 08/09/2024
 * @project formalite-sante-api
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "INSPECTION", schema = "USERFMS")
@Schema(implementation = Inspection.class)
public class Inspection implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_INSPECTION_SANTE")
    @SequenceGenerator(sequenceName = "S_INSPECTION_SANTE", allocationSize = 1, name = "S_INSPECTION_SANTE")
    @Column(name = "ID")
    private Long id;


    @Column(name = "PROVENANCE")
    private String provenance;

    @Column(name = "DEMANDEUR")
    private String demandeur;

    @Column(name = "ADRESSE_DEMANDEUR")
    private String adresseDemandeur;

    @Column(name = "NATIONALITE")
    private String nationalite;

    @Column(name = "DESTINATION")
    private String destination;

    @Column(name = "PAVILLON")
    private String pavillon;

    @Column(name = "NRT")
    private String nrt;

    @Column(name = "REMARQUE")
    private String remarque;

    @Column(name = "COMMANDANT")
    private String commandant;


    @Column(name = "CARGAISON")
    private int  cargaison;

    @Column(name = "TONNAGE")
    private int  tonnage;


    @Column(name = "OP_DESINFECTION")
    private boolean opDesinfection;

    @Column(name = "OP_DERATISATION")
    private boolean opDeratisation;

    @Column(name = "OP_DESINSECTISATION")
    private boolean opDesinfitisation;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_DECLARATION")
    private Date dateDeclaration;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_REINSPECTION")
    private Date dateReinspection;


    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;

    @ManyToOne
    @JoinColumn(name = "ID_TYPE_INSPECTION")
    private TypeInspection typeInspection;



    public Inspection(String provenance, String destination,
                      String nationalite, String commandant, Date dateDeclaration) {
        this.provenance = provenance;
        this.destination = destination;
        this.nationalite = nationalite;
        this.commandant = commandant;
        this.dateDeclaration = dateDeclaration;
    }

    public Inspection(String demandeur, String adresseDemandeur) {
        this.demandeur = demandeur;
        this.adresseDemandeur = adresseDemandeur;
    }

    public Inspection(String provenance, String nrt, String pavillon,
                      String remarque, Date dateReinspection, int cargaison,
                      int tonnage) {
        this.provenance = provenance;
        this.nrt= nrt;
        this.pavillon = pavillon;
        this.remarque = remarque;
        this.dateReinspection = dateReinspection;
        this.cargaison = cargaison;
        this.tonnage = tonnage;

    }
}
