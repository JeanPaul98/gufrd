package com.acl.formaliteagricultureapi.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Zansouyé
 */
@Entity
@Table(name = "PRODUIT", schema = "USERFMA")
@Getter
@Setter
@NoArgsConstructor
@Schema(implementation = Produit.class)
public class Produit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_PRODUIT")
    @SequenceGenerator(sequenceName = "S_PRODUIT", allocationSize = 1, name = "S_PRODUIT")
    @Column(name = "ID")
    private Long id;

    @Column(name = "LIBELLE")
    private String libelle;

    @Column(name = "CODE")
    private String code;

    @Column(name = "REF")
    private String ref;


    @Column(name = "NOM_COMMERCIAL")
    private String nomCommercial;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne
    @JoinColumn(name = "ID_TYPE_PRODUIT", referencedColumnName = "id")
    private TypeProduit typeProduit;

    
    @JoinColumn(name = "id_structure", referencedColumnName = "id")
    @ManyToOne
    private Structure structure;

    @JoinColumn(name = "id_variete_produit", referencedColumnName = "id")
    @ManyToOne
    private VarieteProduit varieteProduit;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produit produit = (Produit) o;
        return Objects.equals(id, produit.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
