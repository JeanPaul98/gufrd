/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acl.formaliteenvironnementapi.models;


import com.acl.formaliteenvironnementapi.models.enumeration.Chaine;
import com.acl.formaliteenvironnementapi.models.enumeration.Etat;
import com.acl.formaliteenvironnementapi.models.enumeration.StatutPaiement;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


/**
 *
 * @author Olivier
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "FORMALITE", schema = "USERFME")
@Schema(implementation = Formalite.class)
public class Formalite implements Serializable {

    private static final long serialVersionUID = 1L;
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_FORMALITE_ENV")
    @SequenceGenerator(sequenceName = "S_FORMALITE_ENV", allocationSize = 1, name = "S_FORMALITE_ENV")
    @Column(name = "ID")
    private Long id;

    @Column(name = "NUM_GENERE")
    private String numGenere;

    @Column(name = "TYPE_DMD")
    private String typeDmd;


    @Column(name = "DECISION")
    private String decision;

    @Column(name = "OBSERVATIONS")
    private String observations;

    @Enumerated(EnumType.STRING)
    private Etat etat;

    @Column(name = "MONTANT_REDEVANCE")

    private double montantRedevance;

    @Column(name = "MOTIF_ANNULATION")
    private String motifAnnulation;

    @Column(columnDefinition = "varchar(50)")
    @Enumerated(EnumType.STRING)
    private Chaine chaine;

    @Column(columnDefinition = "varchar(50)", name = "STATUT_PAIEMENT")
    @Enumerated(EnumType.STRING)
    private StatutPaiement statutPaiement;

    @Column(name = "CODE_CLIENT")
    private String compteClient;

    @Column(name = "DATE_DMD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDmd;

    @Column(name = "DATE_TRAITEMENT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTraitement;

    @Column(name = "DATE_ANNULATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAnnulation;

    @Column(name = "DATE_REJET")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRejet;

    @Column(name = "DATE_ACCEPTATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAccepte;

    @Column(name = "DATE_SOUMISSION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSoumise;

    @Column(name = "utilisateur")
    private String utilisateur;

    @Column(name = "nom_navire")
    private String nomNavire;

    @Column(name = "imo_navire")
    private String imo;

    @Column(name = "num_atp_navire")
    private String atp;

    @Column(name = "affreteur")
    private String affreteur;


    @Column(name = "code_port")
    private String codePort;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;

    @ManyToOne
    @JoinColumn(name = "ID_STRUCTURE", referencedColumnName = "id")
    private Structure structure;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_declaration_dechets", referencedColumnName = "id")
    private DeclarationDechets declarationDechets;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_autorisation", referencedColumnName = "id")
    private Autorisation autorisation;


    public Formalite(String atp, String nomNavire, String imo, String affreteur, String compteClient){
        this.atp=atp;
        this.nomNavire=nomNavire;
        this.imo=imo;
        this.affreteur=affreteur;
        this.compteClient = compteClient;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Formalite formalite = (Formalite) o;
        return Objects.equals(id, formalite.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
