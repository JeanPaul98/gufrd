package com.acl.vbs.fret.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "DMD_DECLARATION_FRET", schema = "VBSUSER")
public class DmdDeclarationFret implements Serializable {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_VBS_DMD_DECLARATION_FRET")
    @SequenceGenerator(sequenceName = "S_VBS_DMD_DECLARATION_FRET", allocationSize = 1, name = "S_VBS_DMD_DECLARATION_FRET")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "ID_DECLARANT")
    private Declarant declarant;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "ID_CHARGEUR", nullable = false)
    private Chargeur chargeur;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "ID_DESTINATAIRE_MARCHANDISE", nullable = false)
    private DestinataireMarchandise destinataireMarchandise;

    @Column(name = "SENS_TRAFIC", nullable = false, length = 50)
    private String sensTrafic;

    @Column(name = "MODE_TRANSPORT", nullable = false, length = 50)
    private String modeTransport;

    @Column(name = "ORIGINE_MARCHANDISE", nullable = false, length = 50)
    private String origineMarchandise;

    @Column(name = "BL_NUMERO_CONNAISSEMENT", nullable = false, length = 50)
    private String blNumeroConnaissement;

    @Column(name = "PORT_EMBARQUEMENT", nullable = false, length = 50)
    private String portEmbarquement;

    @Column(name = "NOM_NAVIRE", nullable = false, length = 50)
    private String nomNavire;

    @Column(name = "NATIONALITE_NAVIRE", nullable = false, length = 50)
    private String nationaliteNavire;

    @Column(name = "NOMBRE_CAMIONS_SOUHAITE", nullable = false)
    private Long nombreCamionsSouhaite;

    @Column(name = "PRIX_TRANSPORT_SOUHAITE_TONNE", nullable = false)
    private Long prixTransportSouhaiteTonne;

    @Column(name = "AFFICHAGE_PRIX_RECEPISSE", nullable = false)
    private boolean affichagePrixRecepisse;

    @Column(name = "PAYS_PROVENANCE", nullable = false, length = 50)
    private String paysProvenance;

    @Column(name = "VILLE_PROVENANCE", nullable = false, length = 50)
    private String villeProvenance;

    @Column(name = "PAYS_DESTINATION", nullable = false, length = 50)
    private String paysDestination;

    @Column(name = "VILLE_DESTINATION", nullable = false, length = 50)
    private String villeDestination;

    @Column(name = "NATURE_MARCHANDISE", nullable = true, length = 50)
    private String natureMarchandise;

    @Column(name = "CODE_SH", nullable = true, length = 20)
    private String codeSh;

    @Column(name = "PRODUIT_GRANDE_CONSOMMATION", nullable = true)
    private boolean produitGrandeConsommation;

    @Column(name = "CREATED_BY", nullable = false, length = 50)
    private String createdBy;

    @Column(name = "CREATED_AT", nullable = false)
    private Date createdAt;

    @Column(name = "UPDATE_BY", length = 50)
    private String updateBy;

    @Column(name = "UPDATE_AT")
    private Date updateAt;

    @PrePersist
    public void setCreatedDate() {
        this.createdAt = this.updateAt = new Date();
    }

    @PreUpdate
    public void setUpdateDate() {
        this.updateAt = new Date();
    }

}