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
@Table(name = "PIECE_JOINTE", schema = "USERFMA")
@Getter
@Setter
@NoArgsConstructor
@Schema(implementation = PieceJointe.class)
public class PieceJointe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_PIECE_JOINTE")
    @SequenceGenerator(sequenceName = "S_PIECE_JOINTE", allocationSize = 1, name = "S_PIECE_JOINTE")
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOM_GENERE_PIECE_JOINTE")
    private String nomGenerePieceJointe;

    @Column(name = "NOM_ORIGINE_PIECE_JOINTE")
    private String nomOriginePieceJointe;

    @Column(name = "URL_PJ")
    private String urlPj;

    @ManyToOne
    @JoinColumn(name = "ID_FORMALITE", referencedColumnName = "ID")
    private Formalite formalite;

    @ManyToOne
    @JoinColumn(name = "ID_TYPE_PIECE_JOINTE", referencedColumnName = "ID")
    private TypePieceJointe typePieceJointe;

    @ManyToOne
    @JoinColumn(name = "ID_DMD_AUTORISATION_AGREMENT", referencedColumnName = "ID")
    private DmdAutorisationAgrement dmdAutorisationAgrement;

    public PieceJointe(String nomGenerePieceJointe, String nomOriginePieceJointe, String urlPj, Formalite formalite, TypePieceJointe typePieceJointe) {
        this.nomOriginePieceJointe = nomOriginePieceJointe;
        this.urlPj = urlPj;
        this.formalite = formalite;
        this.typePieceJointe = typePieceJointe;
        this.nomGenerePieceJointe = nomGenerePieceJointe;
    }
}
