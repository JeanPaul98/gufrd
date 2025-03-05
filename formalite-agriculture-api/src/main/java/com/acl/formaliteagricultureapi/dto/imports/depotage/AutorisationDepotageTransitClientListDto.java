package com.acl.formaliteagricultureapi.dto.imports.depotage;

import com.acl.formaliteagricultureapi.dto.piecejointe.PieceJointeDto;
import com.acl.formaliteagricultureapi.dto.produit.DetAutorisationProduitDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @author Zansouyé on 20/08/2024
 * @project formalite-agriculture-api
 */
@Getter
@Setter
@Schema(description ="AutorisationEnelevementClientListDto" )
public class AutorisationDepotageTransitClientListDto {

    //Debut formalite
    private Long idFormalite;

    @Schema(description = "JPA Formalite: Numéro d'identification fiscal",
            name="nif",
            required=true)
    @NotNull
    private String nif;

    @Schema(description = "JPA Formalite: Information du navire",
            name="atp",
            required=true)
    @NotNull
    private String atp;

    @Schema(description = "JPA Formalite: Numero dossier du demandeur ATD",
            name="numeroDossier",
            required=true)
    @NotNull
    private String numeroDossier;

    @Schema(description = "JPA Formalite: Information du navire",
            name="numGenerer",
            required=true)
    @NotNull
    private String numGenerer;

    @Schema(description = "JPA Formalite: Information du navire",
            name="nomNavire",
            required=true)
    @NotNull
    private String nomNavire;

    @Schema(description = "JPA Formalite: Montant de la redevance",
            name="montantRedevance")
    @NotNull
    private double montantRedevance;

    @Schema(description = "JPA Formalite: Information du navire",
            name="imo",
            required=true)
    @NotNull
    private String imo;

    @Schema(description = "JPA Formalite: numeroBL",
            name="numeroBL")
    private String numeroBL;

    @Schema(description = "JPA Formalite: numeroAutorisation",
            name="numeroAutorisation")
    private String numeroAutorisation;

    List<PieceJointeDto> pieceJointeList;


    @Schema(description = "JPA Formalite: Information du navire",
            name="affreteur",
            required=true)
    @NotNull
    private String affreteur;

    @Schema(description = "JPA Formalite: Information du navire",
            name="chaine",
            required=true)
    private String chaine;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDemande;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSoumission;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTraitement;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRejet;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAccepte;

    private String compteClient;

    @Schema(description = "JPA formalite: Information sur l'autorisation",
            name="etat",
            required=true)
    @NotNull
    private String etat;

    //Fin formalite

    @Schema(description = "JPA Autorisation: Information sur l'autorisation",
            name="datearrivee",
            required=true)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date datearrivee;

    private Long idAutorisation;

    private String nomImportateur;

    private String motifRejet;

    private String statutPaiement;




    //Fin Autorisation

    //Debut Det Autorisation

    List<DetAutorisationProduitDto> detAutorisationProduitDtos;
    //Fin Det Autorisation

    //Debut Type Autorisation

    /**
     * Debut piece jointe
     */
    List<PieceJointeDto> pieceJointeDtoList;

    /*
    Fin piece jointe
     */

    @Schema(description = "JPA Autorisation: Information sur l'autorisation",
            name="typeAutorisation",
            required=true)
    @NotNull
    private String typeAutorisation;


    @Schema(description = "JPA Autorisation: Information sur l'autorisation",
            name="refTypeAutorisation",
            required=true)
    @NotNull
    private String refTypeAutorisation;

    //Fin Type Autorisation

    public AutorisationDepotageTransitClientListDto(Long idFormalite, String atp, String nomNavire,
                                                    String immo, String affreteur,
                                                     Date datearrivee, String chaine, Date dateDemande,
                                                    String etat, String typeAutorisation, String refTypeAutorisation, String numGenerer,
                                                    Date dateRejet, Date dateAccepte, Date dateTraitement, Date dateSoumission,
                                                    List<DetAutorisationProduitDto> detAutorisationProduitDtos) {
        this.idFormalite = idFormalite;
        this.atp = atp;
        this.nomNavire = nomNavire;
        this.imo = immo;
        this.affreteur = affreteur;
        this.datearrivee = datearrivee;
        this.chaine = chaine;
        this.dateDemande = dateDemande;
        this.etat = etat;
        this.numGenerer = numGenerer;
        this.typeAutorisation = typeAutorisation;
        this.refTypeAutorisation = refTypeAutorisation;
        this.dateRejet = dateRejet;
        this.dateAccepte = dateAccepte;
        this.dateTraitement = dateTraitement;
        this.dateSoumission = dateSoumission;
        this.detAutorisationProduitDtos = detAutorisationProduitDtos;
    }

    public AutorisationDepotageTransitClientListDto(Long idAutorisation, Long idFormalite, String atp,
                                                    String nomNavire, String immo, String affreteur,
                                                    String conteneur, Date datearrivee, String chaine,
                                                    Date dateDemande, String etat, String typeAutorisation,
                                                    String typeReference, String numGenerer, Date dateRejet,
                                                    Date dateAccepte, Date dateTraitement, Date dateSoumission,
                                                    List<DetAutorisationProduitDto> detAutorisationProduitDtoList,
                                                    String compteClient, String nomImportateur, double montantRedevance,
                                                    String motifRejet) {

        this.idFormalite = idFormalite;
        this.atp = atp;
        this.nomNavire = nomNavire;
        this.imo = immo;
        this.affreteur = affreteur;
        this.datearrivee = datearrivee;
        this.chaine = chaine;
        this.dateDemande = dateDemande;
        this.etat = etat;
        this.numGenerer = numGenerer;
        this.typeAutorisation = typeAutorisation;
        this.refTypeAutorisation = refTypeAutorisation;
        this.dateRejet = dateRejet;
        this.dateAccepte = dateAccepte;
        this.dateTraitement = dateTraitement;
        this.dateSoumission = dateSoumission;
        this.detAutorisationProduitDtos = detAutorisationProduitDtos;
    }
}
