package com.acl.formaliteenvironnementapi.models;

import com.acl.formaliteenvironnementapi.models.enumeration.EtatTypeAgrement;
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
@Table(name = "CATEGORIE_TYPE_PIECE", schema = "USERFME")
@Getter
@Setter
@NoArgsConstructor
@Schema(implementation = CategorieTypePiece.class)
public class CategorieTypePiece implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_CATEGORIE_TYPE_PIECE")
    @SequenceGenerator(sequenceName = "S_CATEGORIE_TYPE_PIECE", allocationSize = 1, name = "S_CATEGORIE_TYPE_PIECE")
    @Column(name = "ID")
    private Long id;

    private String libelle;

    private String ref;

    @Enumerated(EnumType.STRING)
    private EtatTypeAgrement etatTypeAgrement;

    public CategorieTypePiece(String libelle, String ref) {
        this.libelle = libelle;
        this.ref = ref;
    }
}
