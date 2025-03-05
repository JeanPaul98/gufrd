package com.acl.formaliteagricultureapi.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Zansouyé
 */
@Entity
@Table(name = "DET_AUTORISATION_PRODUIT", schema = "USERFMA")
@Getter
@Setter
@NoArgsConstructor
@Schema(implementation = DetAutorisationProduit.class)
public class DetAutorisationProduit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_DET_AUTORISATION_PRODUIT")
    @SequenceGenerator(sequenceName = "S_DET_AUTORISATION_PRODUIT", allocationSize = 1, name = "S_DET_AUTORISATION_PRODUIT")
    @Column(name = "ID")
    private Long id;


    @Column(name = "QUANTITE")
    private int quantite;

    @Column(name = "CONTENEUR")
    private String conteneur;

    @Column(name = "PROVENANCE")
    private String provenance;

    @Column(name = "ORIGINE")
    private String origine;

    @Column(name = "NOMBRE_CARTON")
    private int nombreCarton;

    @Column(name = "POID_NET")
    private double poidNet;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_PRODUCTION")
    private Date dateProduction;

    private String unite;

    @JoinColumn(name = "id_produit", referencedColumnName = "id")
    @ManyToOne
    private Produit produit;

    @JoinColumn(name = "id_autorisation", referencedColumnName = "id")
    @ManyToOne
    private Autorisation autorisation;


    public DetAutorisationProduit(int quantite, String unite, Produit produit, Autorisation autorisation, double poidNet) {
        this.quantite = quantite;
        this.unite = unite;
        this.produit = produit;
        this.autorisation = autorisation;
        this.poidNet = poidNet;
    }

    public DetAutorisationProduit(int quantite, String unite, int nombreCarton,
                                  Date dateProduction, String origine, Produit produit,
                                  Autorisation autorisation) {
        this.quantite = quantite;
        this.unite = unite;
        this.nombreCarton = nombreCarton;
        this.dateProduction = dateProduction;
        this.origine = origine;
        this.produit = produit;
        this.autorisation = autorisation;
    }

    public DetAutorisationProduit(Produit produit, int quantite,
                                  String provenance, int nombreCarton,
                                  String unite,
                                  String origine, Autorisation autorisation, double poidNet, String conteneur) {
        this.quantite = quantite;
        this.provenance = provenance;
        this.nombreCarton = nombreCarton;
        this.unite = unite;
        this.origine = origine;
        this.produit = produit;
        this.autorisation = autorisation;
        this.poidNet = poidNet;
        this.conteneur = conteneur;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetAutorisationProduit that = (DetAutorisationProduit) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
