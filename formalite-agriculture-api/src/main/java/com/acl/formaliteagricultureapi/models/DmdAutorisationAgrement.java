package com.acl.formaliteagricultureapi.models;

import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import com.acl.formaliteagricultureapi.models.enumeration.StatutAgrement;
import com.acl.formaliteagricultureapi.models.enumeration.StatutPaiement;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UUID;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author Zansouyé on 03/10/2024
 * @project formalite-agriculture-api
 */
@Entity
@Table(name = "DMD_AUTORISATION_AGREMENT", schema = "USERFMA")
@Getter
@Setter
@NoArgsConstructor
@Schema(implementation = DmdAutorisationAgrement.class)
public class DmdAutorisationAgrement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_AGREMENT")
    @SequenceGenerator(sequenceName = "S_AGREMENT", allocationSize = 1, name = "S_AGREMENT")
    @Column(name = "ID")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "NUMERO")
    private String numero;

    @Column(name = "ACTIVITES")
    private String activite;

    @Column(name = "OBSERVATION")
    private String observation;

    @Column(name = "MOTIF_REJET")
    private String motifRejet;

    @Enumerated(EnumType.STRING)
    private Etat etat;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut")
    private StatutAgrement statutAgrement;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUT_PAIEMENT")
    private StatutPaiement statutPaiement;

    @Column(name = "DATE_DEMANDE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDemande;

    @Column(name = "DATE_AGREMENT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAgrement;

    @Column(name = "DATE_EXPIRATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateExpiration;

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

    @Column(name = "DATE_TRAITER")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTraiter;

    @Column(name = "COMPTE_DEMANDEUR")
    private String compteClient;

    @Column(name = "compte_agent_accepte")
    private String compteAgentAccepter;

    @Column(name = "compte_agent_rejete")
    private String compteAgentRejet;

    @Column(name = "compte_agent_traite")
    private String compteAgentTraiter;

    @Column(name = "AGRMT_A_RENOUVELER")
    private boolean renouveler;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_AT")
    private Date updateAt;

    @ManyToOne
    @JoinColumn(name = "ID_SOCIETE", referencedColumnName = "ID")
    private Societe societe;

    @ManyToOne
    @JoinColumn(name = "ID_AGREMENT", referencedColumnName = "ID")
    private Agrement agrement;


    public DmdAutorisationAgrement(String numero, String activite, String observation,
                                   Date dateAgrement, Date dateExpiration, String compteClient,
                                   Societe societe, Agrement agrement) {
        this.numero = numero;
        this.activite = activite;
        this.observation = observation;
        this.dateAgrement = dateAgrement;
        this.dateExpiration = dateExpiration;
        this.compteClient = compteClient;
        this.societe = societe;
        this.agrement = agrement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DmdAutorisationAgrement that = (DmdAutorisationAgrement) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
