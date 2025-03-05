package com.acl.formaliteagricultureapi.dto.imports.demande.alimentAnimaux;

import com.acl.formaliteagricultureapi.dto.piecejointe.PieceJointeDto;
import com.acl.formaliteagricultureapi.dto.produit.DetAutorisationProduitDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @author Zansouyé on 20/08/2024
 * @project formalite-agriculture-api
 */
@Getter
@Setter
@ToString
@Schema(description ="AutorisationEnelevementClientListDto" )
@NoArgsConstructor
public class AutorisationEnlevementClientListDto {

    //Debut formalite


    @Schema(description = "JPA Formalite: Information du navire",
            name = "numGenerer",
            required = true)
    @NotNull
    private String numGenerer;

    @Schema(description = "JPA Formalite: Information du navire",
            name = "chaine",
            required = true)
    private String chaine;

    @Schema(description = "JPA Formalite: Montant de la redevance",
            name = "montantRedevance")
    @NotNull
    private double montantRedevance;

    private String compteClient;

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


    @Schema(description = "JPA formalite: Information sur l'autorisation",
            name = "etat",
            required = true)
    @NotNull
    private String etat;

    private Long idFormalite;


    //Fin formalite

    //Debut Autorisation
    @Schema(description = "JPA Autorisation: Information sur l'autorisation",
            name = "provenance",
            required = true)
    @NotNull
    private String provenance;

    private Long idAutorisation;

    private String nomSociete;

    private String typeRegime;

    private String numeroAgrementTransit;

    private String nif;

    private String numeroDossier;


    List<PieceJointeDto> pieceJointeList;

    private String statutDemande;


    //Fin Autorisation


    //Debut Det Autorisation

    List<DetAutorisationProduitDto> detAutorisationProduitDtos;
    //Fin Det Autorisation

    //Debut Type Autorisation

    @Schema(description = "JPA Autorisation: Information sur l'autorisation",
            name = "typeAutorisation",
            required = true)
    @NotNull
    private String typeAutorisation;


    @Schema(description = "JPA Autorisation: Information sur l'autorisation",
            name = "refTypeAutorisation",
            required = true)
    @NotNull
    private String refTypeAutorisation;

    @Schema(description = "JPA Autorisation: Motif Rejet",
            name = "motifRejet",
            required = false)
    private String motifRejet;

    @Schema(description = "JPA Autorisation: numéro agrement",
            name = "numeroAgrement",
            required = false)
    @NotNull
    private String numeroAgrement;

    @Schema(description = "JPA Autorisation: activite",
            name = "activite",
            required = false)
    @NotNull
    private String activite;

    private String statutPaiement;

    private Date dateEmbarquement;


    // Fin Type Autorisation

    public AutorisationEnlevementClientListDto(Long idFormalite,
                                               String provenance, Date datearrivee, String chaine, Date dateDemande,
                                               String etat, String typeAutorisation, String refTypeAutorisation,
                                               String numGenerer,Date dateRejet, Long idAutorisation, Date dateAccepte,
                                               Date dateTraitement, Date dateSoumission, String compteClient,
                                               String motifRejet,String nomImportateur, double montantRedevance,
                                               List<DetAutorisationProduitDto> detAutorisationProduitDtoList) {
        this.idFormalite = idFormalite;
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
        this.idAutorisation = idAutorisation;
        this.motifRejet = motifRejet;
        this.compteClient = compteClient;
        this.provenance = provenance;
        this.montantRedevance = montantRedevance;
        this.detAutorisationProduitDtos = detAutorisationProduitDtoList;
    }

    public AutorisationEnlevementClientListDto(
            String provenance, String chaine, Date dateDemande,
            String etat, String typeAutorisation, String typeReference,
            String numGenerer, Date dateRejet, Long idAutorisation,
            Date dateAccepte, Date dateTraitement, Date dateSoumission,
            String compteClient, String motifRejet, double montantRedevance,
            List<DetAutorisationProduitDto> detAutorisationProduitDtoList) {

        this.idFormalite = idFormalite;
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
        this.idAutorisation = idAutorisation;
        this.motifRejet = motifRejet;
        this.compteClient = compteClient;
        this.provenance = provenance;
        this.montantRedevance = montantRedevance;
        this.detAutorisationProduitDtos = detAutorisationProduitDtoList;
    }

    public AutorisationEnlevementClientListDto(Long idAutorisation, Long idFormalite, String atp,
                                               String nomNavire, String immo, String affreteur,
                                               String conteneur, Date datearrivee, String chaine,
                                               Date dateDemande, String etat, String typeAutorisation,
                                               String typeReference, String numGenerer, Date dateRejet,
                                               Date dateAccepte, Date dateTraitement, Date dateSoumission,
                                               List<DetAutorisationProduitDto> detAutorisationProduitDtoList,
                                               String compteClient, String nomImportateur, double montantRedevance,
                                               String motifRejet) {

        this.idFormalite = idFormalite;
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
        this.idAutorisation = idAutorisation;
        this.motifRejet = motifRejet;
        this.compteClient = compteClient;
        this.provenance = provenance;
        this.montantRedevance = montantRedevance;
        this.detAutorisationProduitDtos = detAutorisationProduitDtoList;
    }
}
