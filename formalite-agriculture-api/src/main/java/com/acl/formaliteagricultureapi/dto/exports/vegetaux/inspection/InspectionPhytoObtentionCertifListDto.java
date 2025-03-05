package com.acl.formaliteagricultureapi.dto.exports.vegetaux.inspection;

import com.acl.formaliteagricultureapi.dto.produit.DetPhytosanitaireProduitDto;
import com.acl.formaliteagricultureapi.dto.produit.DetTraitementProduitDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @author kol on 25/08/2024
 * @project formalite-agriculture-api
 */
@Getter
@Setter
@ToString
@Schema(description ="InspectionPhytoObtentionCertifDto" )
public class InspectionPhytoObtentionCertifListDto {

    /*
    Début formalité
    Information navire
     */
    @Schema(description = "JPA Formalite: id formalite",
            name="idFormalite")
    private Long idFormalite;

    @Schema(description = "JPA Formalite: id phytosanitaire",
            name="idPhytosanitaire")
    private Long idPhytosanitaire;

    @Schema(description = "JPA Formalite: Information du navire",
            name="atp")
    private String atp;

    @Schema(description = "JPA Formalite: Information du navire",
            name="nomNavire")
    private String nomNavire;

    @Schema(description = "JPA Formalite: Information du navire",
            name="imo")
    private String imo;

    @Schema(description = "JPA Formalite: Information de l'affreteur",
            name="affreteur")
    private String affreteur;

    @Schema(description = "JPA Formalite: Information de l'affreteur",
            name="numGenerer")
    private String numGenerer;

    @Schema(description = "JPA Formalite: Type inspection phytosanitaire",
            name="numGenerer")
    private String typeInspPhytosanitaire;

    @Schema(description = "JPA Formalite: Information de l'affreteur",
            name="compteClient")
    private String compteClient;

    @Schema(description = "JPA Formalite: Information de l'affreteur",
            name="redevance")
    private double redevance;
    /*
    Fin formalite
     */

    /*
    Debut Inspection pour Obtention certificat
     */
    @NotNull
    private String lieuDestination;

    @NotNull
    private String adresseExpediteur;

    @NotNull
    private String nomExpediteur;

    @NotNull
    private String nomDestinataire;

    @NotNull
    private String adresseDestinataire;

    @NotNull
    private String moyenTransport;

    @NotNull
    private Date dateHeureEmbarquement;

    private String renseignementComplementaire;

    private boolean traitementProduit;

    /*
    Debut détail produit denrée
     */
    private List<DetPhytosanitaireProduitDto> detPhytosanitaireProduitDtos;

    /*
    Information traitement du produit
     */

    private List<DetTraitementProduitDto> detTraitementProduitDtos;

    /*
        Fin détail traitement produit
     */

      /*
        complement
     */

    @Schema(description = "JPA Formalite: Information du navire",
            name="chaine",
            required=true)
    private String chaine;

    @Schema(description = "JPA Formalite: Montant de la redevance",
            name="montantRedevance")
    @NotNull
    private double montantRedevance;


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


    @Schema(description = "JPA formalite: Information sur l'inspection phytosanitaire de navire",
            name="etat",
            required=true)
    @NotNull
    private String etat;

    public InspectionPhytoObtentionCertifListDto(Long idFormalite, String atp, String imo, String etat,
                                                 String numGenerer, String typePhytosanitaire,
                                                 Date dateSoumission, Date dateDemande, String affreteur,
                                                 Date dateAccepte, List<DetPhytosanitaireProduitDto> detPhytosanitaireProduitDtos,
                                                 double montantRedevance, String compteClient) {
    this.idFormalite = idFormalite;
    this.atp = atp;
    this.imo = imo;
    this.etat = etat;
    this.numGenerer = numGenerer;
    this.typeInspPhytosanitaire = typePhytosanitaire;
    this.dateSoumission = dateSoumission;
    this.dateDemande = dateDemande;
    this.affreteur = affreteur;
    this.dateAccepte = dateAccepte;
    this.detPhytosanitaireProduitDtos = detPhytosanitaireProduitDtos;
    this.montantRedevance = montantRedevance;
    this.compteClient = compteClient;


    }

    /*
    Fin complement
     */

}
