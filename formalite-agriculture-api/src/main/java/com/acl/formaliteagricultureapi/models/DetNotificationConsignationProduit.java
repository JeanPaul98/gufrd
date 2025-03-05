package com.acl.formaliteagricultureapi.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Zansouyé on 16/09/2024
 * @project formalite-agriculture-api
 */
@Entity
@Table(name = "DET_NOTIFICATION_CONSIGNATION_PRODUIT", schema = "USERFMA")
@Getter
@Setter
@NoArgsConstructor
@Schema(implementation = DetNotificationConsignationProduit.class)
public class DetNotificationConsignationProduit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_DET_NOTIFICATION_CONSIGNATION_PRODUIT")
    @SequenceGenerator(sequenceName = "S_DET_NOTIFICATION_CONSIGNATION_PRODUIT", allocationSize = 1, name = "S_DET_NOTIFICATION_CONSIGNATION_PRODUIT")
    @Column(name = "ID")
    private Long id;

    @Column(name = "PROVENANCE")
    private String provenance;

    @Column(name = "ORIGINE")
    private String origine;

    @Column(name = "DESTINATION")
    private String destination;

    @Column(name = "QUANTITE")
    private int quantite;

    @Column(name = "nombre")
    private int nombre;

    private String unite;

    @JoinColumn(name = "ID_PRODUIT", referencedColumnName = "ID")
    @ManyToOne
    private Produit produit;

    @JoinColumn(name = "ID_NOTIFICATION_CONSIGNATION", referencedColumnName = "ID")
    @ManyToOne
    private NotificationConsignation notificationConsignation;

    public DetNotificationConsignationProduit(String provenance, String origine, String destination,
                                              int quantite, int nombre, String unite, Produit produit,
                                              NotificationConsignation notificationConsignation) {
        this.provenance = provenance;
        this.origine = origine;
        this.destination = destination;
        this.quantite = quantite;
        this.nombre = nombre;
        this.unite = unite;
        this.produit = produit;
        this.notificationConsignation = notificationConsignation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetNotificationConsignationProduit that = (DetNotificationConsignationProduit) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
