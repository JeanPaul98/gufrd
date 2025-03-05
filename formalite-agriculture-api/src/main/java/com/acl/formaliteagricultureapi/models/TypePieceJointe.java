package com.acl.formaliteagricultureapi.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Zansouyé on 28/08/2024
 * @project formalite-agriculture-api
 */
@Entity
@Table(name = "TYPE_PIECE_JOINTE", schema = "USERFMA")
@Getter
@Setter
@NoArgsConstructor
@Schema(implementation = TypePieceJointe.class)
public class TypePieceJointe implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_TYPE_PIECE_JOINTE")
    @SequenceGenerator(sequenceName = "S_TYPE_PIECE_JOINTE", allocationSize = 1, name = "S_TYPE_PIECE_JOINTE")
    @Column(name = "ID")
    private Long id;

    @Column(name = "LIBELLE", nullable = false)
    private String libelle;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "OBLIGATOIRE")
    private boolean obligatoire;

    /*@Column(name = "AUTRE")
    private boolean autre;*/

//    @JoinColumn(name = "ID_CATEGORIE_TYPE_PIECE", referencedColumnName = "ID")
//    @ManyToOne
//    private CategorieTypePiece categorieTypePiece;

    public TypePieceJointe(String libelle, String description, boolean obligatoire
//                           CategorieTypePiece categorieTypePiece
    ) {
        this.libelle = libelle;
        this.description = description;
        this.obligatoire = obligatoire;
//        this.categorieTypePiece = categorieTypePiece;
    }
}
