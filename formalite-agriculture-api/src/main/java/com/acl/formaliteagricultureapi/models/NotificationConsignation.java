package com.acl.formaliteagricultureapi.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author Zansouyé on 16/09/2024
 * @project formalite-agriculture-api
 */
@Entity
@Table(name = "notification_consignation", schema = "USERFMA")
@Getter
@Setter
@NoArgsConstructor
@Schema(implementation = NotificationConsignation.class)
public class NotificationConsignation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_NOTIFICATION_CONSIGNATION")
    @SequenceGenerator(sequenceName = "S_NOTIFICATION_CONSIGNATION", allocationSize = 1, name = "S_NOTIFICATION_CONSIGNATION")
    @Column(name = "ID")
    private Long id;

    @Column(name = "moyen_transport")
    private String moyenTransport;

    private String reference;

    @Column(name = "motif_consignation")
    private String motifConsignation;

    @Column(name = "lieu_consignation")
    private String lieuConsignation;

    @Column(name = "analyse_demande")
    private String analyseDemande;

    @Column(name = "numero_autorisation")
    private String numeroAutorisation;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_consignation")
    private Date dateConsignation;

    @ManyToOne
    @JoinColumn(name = "inspecteur_id", referencedColumnName = "id")
    private Inspecteur inspecteur;

    @ManyToOne
    @JoinColumn(name = "formalite_id", referencedColumnName = "id")
    private Formalite formalite;

    public NotificationConsignation(String moyenTransport, String reference,
                                    String motifConsignation, String lieuConsignation,
                                    String analyseDemande, String numeroAutorisation,
                                    Date dateConsignation, Inspecteur inspecteur, Formalite formalite) {
        this.moyenTransport = moyenTransport;
        this.reference = reference;
        this.motifConsignation = motifConsignation;
        this.lieuConsignation = lieuConsignation;
        this.analyseDemande = analyseDemande;
        this.numeroAutorisation = numeroAutorisation;
        this.dateConsignation = dateConsignation;
        this.inspecteur = inspecteur;
        this.formalite = formalite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationConsignation that = (NotificationConsignation) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
