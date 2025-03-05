package com.acl.formaliteagricultureapi.dto.imports.cargaison;

import com.acl.formaliteagricultureapi.dto.piecejointe.PieceJointeDto;
import com.acl.formaliteagricultureapi.dto.produit.DetPhytosanitaireProduitDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @author Jules on 21/08/2024
 * @project formalite-agriculture-api
 */
@Getter
@Setter
@NoArgsConstructor
@Schema(description ="PhytosanitaireCargaisonClientListDto" )
public class PhytosanitaireCargaisonClientListDto {

    //Debut formalite
    private Long idFormalite;

    @Schema(description = "JPA Formalite: Numéro d'identification fiscal",
            name="nif")
    private String nif;
    
    @Schema(description = "JPA Formalite: Information du navire",
            name="numGenerer",
            required=true)
    @NotNull
    private String numGenerer;

    @Schema(description = "JPA Formalite: Information du navire",
            name="atp",
            required=true)
    @NotNull
    private String atp;

    @Schema(description = "JPA Formalite: Information du navire",
            name="nomNavire",
            required=true)
    @NotNull
    private String nomNavire;

    @Schema(description = "JPA Formalite: Information du navire",
            name="imo",
            required=true)
    @NotNull
    private String imo;

    @Schema(description = "JPA Formalite: Information du navire",
            name="affreteur",
            required=true)
    @NotNull
    private String affreteur;

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

    //Fin formalite

    /*
     * Debut Inspection Phytosanitaire de Navire
     */
    
    @NotNull
    private String nomDemandeur;
    
    @NotNull
    private String professionDemandeur;
    
    @NotNull
    private String adresseDemandeur;
    
    @NotNull
    private String lieuInspection;

    @NotNull
    String structureDemandeur;
    
    @NotNull
    private Date datePrevueInspection;

    private Long idPhytosanitaire;

    //Fin Visite d'Inspection phytosanitaire 

    private String numeroDossier;

    private List<DetPhytosanitaireProduitDto> detPhytosanitaireProduitDtos;

    

    @Schema(description = "JPA Autorisation: Information sur l'inspection phytosanitaire sanitaire",
            name="typeInspPhyto",
            required=true)
    @NotNull    //Type InspectionPhyto
    private String typeInspectionPhyto;


    @Schema(description = "JPA Autorisation: Information sur l'inspection phytosanitaire ",
            name="refTypeInspectionPhyto",
            required=true)
    @NotNull
    private String refTypeInspectionPhyto;

    private String compteClient;

    private String statuPaiement;

    List<PieceJointeDto> pieceJointeList;


    public PhytosanitaireCargaisonClientListDto(Long idFormalite, String atp, String nomNavire,
                                                  String imo, String affreteur,
                                                  String chaine,                                                   
//                                                  Date datePrevueInspection,
                                                  Date dateDemande,
                                                  String structureDemandeur,
                                                  String etat, String typeInspectionPhyto, String refTypeInspectionPhyto, String numGenerer,
                                                  Date dateRejet, Date dateAccepte, Date dateTraitement, Date dateSoumission,
                                                  List<DetPhytosanitaireProduitDto> detPhytosanitaireProduitDtos) {
        this.idFormalite = idFormalite;
        this.atp = atp;
        this.nomNavire = nomNavire;
        this.imo = imo;
        this.affreteur = affreteur;     
//        this.datePrevueInspection = datePrevueInspection;
        this.chaine = chaine;
        this.dateDemande = dateDemande;
        this.structureDemandeur = structureDemandeur;
        this.etat = etat;
        this.numGenerer = numGenerer;
        this.typeInspectionPhyto = typeInspectionPhyto;
        this.refTypeInspectionPhyto = refTypeInspectionPhyto;
        this.dateRejet = dateRejet;
        this.dateAccepte = dateAccepte;
        this.dateTraitement = dateTraitement;
        this.dateSoumission = dateSoumission;
        this.detPhytosanitaireProduitDtos =  detPhytosanitaireProduitDtos;
       
    }
    
    
    public PhytosanitaireCargaisonClientListDto(Long idFormalite, String atp, String nomNavire,
            String imo, String affreteur,
            String chaine, 
            String nomDemandeur,
            String professionDemandeur,
            String adresseDemandeur,
            String lieuInspection,
            Date datePrevueInspection,
            Date dateDemande,
            String etat, String typeInspectionPhyto, String refTypeInspectionPhyto, String numGenerer,
            Date dateRejet, Date dateAccepte, Date dateTraitement, Date dateSoumission,
            List<DetPhytosanitaireProduitDto> detPhytosanitaireProduitDtos) {
    	
			this.idFormalite = idFormalite;
			this.atp = atp;
			this.nomNavire = nomNavire;
			this.imo = imo;
			this.affreteur = affreteur; 
			this.nomDemandeur = nomDemandeur;
			this.professionDemandeur = professionDemandeur;
			this.adresseDemandeur = adresseDemandeur;
			this.lieuInspection  = lieuInspection;
			this.datePrevueInspection = datePrevueInspection;
			this.chaine = chaine;
			this.dateDemande = dateDemande;
			this.etat = etat;
			this.numGenerer = numGenerer;
			this.typeInspectionPhyto = typeInspectionPhyto;
			this.refTypeInspectionPhyto = refTypeInspectionPhyto;
			this.dateRejet = dateRejet;
			this.dateAccepte = dateAccepte;
			this.dateTraitement = dateTraitement;
			this.dateSoumission = dateSoumission;
			this.detPhytosanitaireProduitDtos =  detPhytosanitaireProduitDtos;
		}
    
    
    
}
