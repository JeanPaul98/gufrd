package com.acl.formaliteagricultureapi.models;

import com.acl.formaliteagricultureapi.models.enumeration.EtatTypeAgrement;
import com.acl.formaliteagricultureapi.models.enumeration.Operation;
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
@Table(name = "TYPE_PRODUIT", schema = "USERFMA")
@Getter
@Setter
@NoArgsConstructor
@Schema(implementation = TypeProduit.class)
public class TypeProduit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_TYPE_PRODUIT")
    @SequenceGenerator(sequenceName = "S_TYPE_PRODUIT", allocationSize = 1, name = "S_TYPE_PRODUIT")
    @Column(name = "ID")
    private Long id;

    @Column(name = "LIBELLE")
    private String libelle;

    private String ref;

    @Column(name="prix_unitaire")
    private double prixUnitaire;

    private int min;

    private int max;

    private boolean borne;

    @Enumerated(EnumType.STRING)
    @Column(name = "OPERATION")
    private Operation operation;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeProduit that = (TypeProduit) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
