package com.acl.formaliteagricultureapi.dto.imports.demande.animauxVivant;

import com.acl.formaliteagricultureapi.dto.piecejointe.PieceJointeDto;
import com.acl.formaliteagricultureapi.dto.produit.DetAutorisationProduitDto;
import com.acl.formaliteagricultureapi.models.PieceJointe;
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
@Schema(description ="AutorisationAnimauxVivantClientListDto" )
public class AutorisationAnimauxVivantClientListDto {

    /*
    Debut formalite
     */
    private Long idFormalite;

    private String numeroAgrement;

    private String nomSociete;

    private String nif;

    private String numeroDossier;


    private String compteClient;

    private String numGenerer;

    @Schema(description = "JPA Formalite: Information du navire",
            name="chaine")
    private String chaine;

    @Schema(description = "JPA Formalite: Information du navire",
            name="dateDemande",
            required=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDemande;

    @Schema(description = "JPA Formalite: Information du navire",
            name="dateSoumission",
            required=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSoumission;

    @Schema(description = "JPA Formalite: Information du navire",
            name="dateTraitement",
            required=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTraitement;

    @Schema(description = "JPA Formalite: Information du navire",
            name="dateRejet",
            required=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRejet;

    @Schema(description = "JPA Formalite: Information du navire",
            name="dateAccepte",
            required=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAccepte;


    @Schema(description = "JPA formalite: Information sur l'autorisation",
            name="etat",
            required=true)
    @NotNull
    private String etat;


    //Fin formalite

    //Debut Autorisation

    @Schema(description = "JPA Autorisation: Information sur l'autorisation",
            name="datearrivee",
            required=true)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date datearrivee;

    @Schema(description = "JPA formalite: Information sur l'autorisation",
            name="provenance",
            required=true)
    @NotNull
    private String provenance;

    @Schema(description = "JPA formalite: Information sur l'autorisation",
            name="dateEmbarquement",
            required=true)
    @NotNull
    private Date dateEmbarquement;

    private String typeRegime;

    private String numeroAgrementTransit;

    private Long idAutorisation;
    //Fin Autorisation

    /**
     * Début Piece jointe
     */

    List<PieceJointeDto> pieceJointeList;

    /*
    Fin type piece jointe
     */

    //Debut Det Autorisation

    List<DetAutorisationProduitDto> detAutorisationProduitDtos;
    //Fin Det Autorisation


    @Schema(description = "JPA Autorisation: Information sur l'autorisation",
            name="typeAutorisation",
            required=true)
    @NotNull    //Type Autorisation
    private String typeAutorisation;


    @Schema(description = "JPA Autorisation: Information sur l'autorisation",
            name="refTypeAutorisation",
            required=false)
    private String refTypeAutorisation;

    /*
    Information complémentaire
     */
    @Schema(description = "JPA Autorisation: I",
            name="motifRejet",
            required=false)
    private String motifRejet;


    @Schema(description = "JPA Autorisation: I",
            name="statutPaiement",
            required=false)
    private String statutPaiement;

    @Schema(description = "JPA Autorisation: I",
            name="statutDemande",
            required=false)
    private String statutDemande;




    public AutorisationAnimauxVivantClientListDto(Long idAutorisation ,Long idFormalite, String atp, String nomNavire,
                                                  String immo, String affreteur,
                                                  String conteneur, Date datearrivee, String chaine, Date dateDemande,
                                                  String etat, String typeAutorisation, String refTypeAutorisation, String numGenerer,
                                                  Date dateRejet, Date dateAccepte, Date dateTraitement, Date dateSoumission,
                                                  List<DetAutorisationProduitDto> detAutorisationProduitDtos, String compteClient,
                                                  String nomImportateur, double montantRedevance, String motifRejet) {
        this.idFormalite = idFormalite;
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
        this.compteClient = compteClient;
        this.motifRejet = motifRejet;
        this.idAutorisation = idAutorisation;
    }
}
