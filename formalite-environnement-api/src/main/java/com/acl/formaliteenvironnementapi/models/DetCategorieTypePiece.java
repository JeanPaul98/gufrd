package com.acl.formaliteenvironnementapi.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Zansouyé on 27/09/2024
 * @project formalite-agriculture-api
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "DET_CATEGORIE_TYPE_JOINTE", schema = "USERFME")
@Schema(implementation = DetCategorieTypePiece.class)
public class DetCategorieTypePiece implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_DET_CATEGORIE_TYPE_JOINTE")
    @SequenceGenerator(sequenceName = "S_DET_CATEGORIE_TYPE_JOINTE", allocationSize = 1, name = "S_DET_CATEGORIE_TYPE_JOINTE")
    @Column(name = "ID")
    private Long id;

    @JoinColumn(name = "ID_CATEGORIE_TYPE_PIECE", referencedColumnName = "ID")
    @ManyToOne
    private CategorieTypePiece categorieTypePiece;

    @JoinColumn(name = "ID_TYPE_PIECE", referencedColumnName = "ID")
    @ManyToOne
    private TypePieceJointe typePieceJointe;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetCategorieTypePiece that = (DetCategorieTypePiece) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
