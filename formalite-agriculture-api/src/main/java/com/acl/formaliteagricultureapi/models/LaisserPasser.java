package com.acl.formaliteagricultureapi.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author Zansouyé on 10/09/2024
 * @project formalite-agriculture-api
 */
@Entity
@Table(name = "laisser_passer", schema = "USERFMA")
@Getter
@Setter
@NoArgsConstructor
@Schema(implementation = LaisserPasser.class)
public class LaisserPasser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_LAISSER_PASSER")
    @SequenceGenerator(sequenceName = "S_LAISSER_PASSER", allocationSize = 1, name = "S_LAISSER_PASSER")
    @Column(name = "ID")
    private Long id;

    private String abattoir;

    private String provenance;

    @Column(name = "REFERENCE_CERTIFICAT")
    private String referenceCertificat;

    @Column(name = "REFERENCE_CONTENEUR")
    private String referenceConteneur;

    @Column(name = "NUM_AUTORISATION_DEPOTAGE")
    private String numeroAutorisationDepotage;

    @Column(name = "DATE_AUTORISATION_DEPOTAGE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAutorisationDepotage;

    @Column(name = "MOYEN_TRANSPORT")
    private String moyenTransport;

    @Column(name = "DATE_INSPECTION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateInspection;

    @Column(name = "numero_laisser")
    private String numeroLaisserPasser;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;

    @ManyToOne
    @JoinColumn(name = "formalite_id", referencedColumnName = "id")
    private Formalite formalite;

    @ManyToOne
    @JoinColumn(name = "inspecteur_id", referencedColumnName = "id")
    private Inspecteur inspecteur;

    public LaisserPasser(String abattoir, String provenance, String referenceCertificat,
                         String referenceConteneur, String numeroAutorisationDepotage,
                         Date dateAutorisationDepotage, String moyenTransport, Date dateInspection,
                         String numeroLaisserPasser, Date createdAt, Formalite formalite,
                         Inspecteur inspecteur) {
        this.abattoir = abattoir;
        this.provenance = provenance;
        this.referenceCertificat = referenceCertificat;
        this.referenceConteneur = referenceConteneur;
        this.numeroAutorisationDepotage = numeroAutorisationDepotage;
        this.dateAutorisationDepotage = dateAutorisationDepotage;
        this.moyenTransport = moyenTransport;
        this.dateInspection = dateInspection;
        this.numeroLaisserPasser = numeroLaisserPasser;
        this.createdAt = createdAt;
        this.formalite = formalite;
        this.inspecteur = inspecteur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LaisserPasser that = (LaisserPasser) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
