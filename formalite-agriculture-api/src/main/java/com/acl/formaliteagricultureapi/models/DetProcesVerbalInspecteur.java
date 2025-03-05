package com.acl.formaliteagricultureapi.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Zansouyé
 *
 */
@Entity
@Table(name = "DET_PROCES_VERBAL_INSPECTEUR", schema = "USERFMA")
@Getter
@Setter
@NoArgsConstructor
@Schema(implementation = DetProcesVerbalInspecteur.class)
public class DetProcesVerbalInspecteur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_DET_PROCES_VERBAL_INSPECTEUR")
    @SequenceGenerator(sequenceName = "S_DET_PROCES_VERBAL_INSPECTEUR", allocationSize = 1, name = "S_DET_PROCES_VERBAL_INSPECTEUR")
    @Column(name = "ID")
    private Long id;


    /*@Column(name = "DESCRIPTION_ENVOI")
    private String descriptionEnvoi;


    @Column(name = "PAYS_ET_LIEU_ORIGINE")
    private String paysEtLieuOrigin;

    @Column(name = "QUANTITE")
    private int quantite;


    @Column(name = "VARIETE")
    private String varietes;


    @Column(name = "EMPLACEMENT")
    private String emplacement;


    @Column(name = "MESURE")
    private String mesure;


    @Column(name = "NATURE")
    private String nature;*/

    @Column(name = "FONCTION")
    private String fonction;

    @JoinColumn(name = "ID_PROCES_VERBAL", referencedColumnName = "id")
    @ManyToOne
    private ProcesVerbal procesverbal;

    @JoinColumn(name = "ID_INSPECTEUR", referencedColumnName = "id")
    @ManyToOne
    private Inspecteur inspecteur;
}
