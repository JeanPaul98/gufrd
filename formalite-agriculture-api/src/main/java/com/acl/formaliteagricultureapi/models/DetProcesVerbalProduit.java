package com.acl.formaliteagricultureapi.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Zansouyé on 23/08/2024
 * @project formalite-agriculture-api
 */
@Entity
@Table(name = "DET_PROCES_VERBAL_PRODUIT", schema = "USERFMA")
@Getter
@Setter
@NoArgsConstructor
@Schema(implementation = DetProcesVerbalProduit.class)
public class DetProcesVerbalProduit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_DET_AUTORISATION_PRODUIT")
    @SequenceGenerator(sequenceName = "S_DET_AUTORISATION_PRODUIT", allocationSize = 1, name = "S_DET_AUTORISATION_PRODUIT")
    @Column(name = "ID")
    private Long id;

    @Column(name = "DESCRIPTION_ENVOI")
    private String descriptionEnvoi;

    @Column(name = "ORIGINE")
    private String origine;

    @Column(name = "QUANTITE")
    private int quantite;

    @Column(name = "EMPLACEMENT")
    private String emplacement;

    @Column(name = "MESURE")
    private String mesure;

    @Column(name = "NATURE")
    private String nature;

    @Column(name = "MOYEN_TRANSPORT")
    private String moyenTransport;

    @Column(name = "MATIERE_ACTIVE")
    private String matiereActive;

    @JoinColumn(name = "id_produit", referencedColumnName = "id")
    @ManyToOne
    private Produit produit;

    @JoinColumn(name = "id_proces_verbal", referencedColumnName = "id")
    @ManyToOne
    private ProcesVerbal procesVerbal;

    public DetProcesVerbalProduit(int quantite, String emplacement, String mesure, String origine,
                                  String moyenTransport, String matiereActive, String descriptionEnvoie) {
        this.quantite = quantite;
        this.emplacement = emplacement;
        this.mesure = mesure;
        this.origine = origine;
        this.moyenTransport = moyenTransport;
        this.matiereActive = matiereActive;
        this.descriptionEnvoi = descriptionEnvoie;
    }
}
