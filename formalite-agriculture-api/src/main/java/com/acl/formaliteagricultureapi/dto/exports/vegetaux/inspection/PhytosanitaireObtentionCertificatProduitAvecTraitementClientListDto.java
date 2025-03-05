package com.acl.formaliteagricultureapi.dto.exports.vegetaux.inspection;

import java.util.Date;
import java.util.List;

import com.acl.formaliteagricultureapi.dto.produit.DetPhytosanitaireProduitAvecTraitementDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description ="PhytosanitaireObtentionCertificatProduitAvecTraitementClientListDto" )
public class PhytosanitaireObtentionCertificatProduitAvecTraitementClientListDto {
	
	//Debut formalite
    private Long idFormalite;
    
    @Schema(description = "JPA Formalite: Information du navire",
            name="numGenerer")
    @NotNull
    private String numGenerer;

    @Schema(description = "JPA Formalite: Information du navire",
            name="atp")
    @NotNull
    private String atp;

    @Schema(description = "JPA Formalite: Information du navire",
            name="nomNavire")
    @NotNull
    private String nomNavire;

    @Schema(description = "JPA Formalite: Information du navire",
            name="imo")
    @NotNull
    private String imo;

    @Schema(description = "JPA Formalite: Information du navire",
            name="affreteur")
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


    @Schema(description = "JPA formalite: Information sur l'inspection phytosanitaire de navire",
            name="etat",
            required=true)
    @NotNull
    private String etat;

    //Fin formalite
    
//Debut Phytosanitaire Obtention de Certificat
    
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
    
    //Détails produits 
    
    private List<DetPhytosanitaireProduitAvecTraitementDto> detPhytosanitaireProduitDtos;

    @Schema(description = "JPA Autorisation: Information sur l'inspection phytosanitaire",
            name="typeInspPhyto",
            required=true)
    @NotNull    //Type InspectionPhyto
    private String typeInspectionPhyto;


    @Schema(description = "JPA Autorisation: Information sur l'inspection phytosanitaire",
            name="refTypeInspectionPhyto",
            required=true)
    @NotNull
    private String refTypeInspectionPhyto;


	public PhytosanitaireObtentionCertificatProduitAvecTraitementClientListDto(Long idFormalite,  String numGenerer,
			String atp,  String nomNavire,  String imo, String affreteur,
			String chaine, Date dateDemande, Date dateSoumission, Date dateTraitement, Date dateRejet, Date dateAccepte,
			String etat,  String lieuDestination,  String adresseExpediteur,
			@NotNull String nomExpediteur, @NotNull String nomDestinataire, @NotNull String adresseDestinataire,
			@NotNull String typeInspectionPhyto, @NotNull String refTypeInspectionPhyto,
			List<DetPhytosanitaireProduitAvecTraitementDto> detPhytosanitaireProduitDtos) {
		
		this.idFormalite = idFormalite;
		this.numGenerer = numGenerer;
		this.atp = atp;
		this.nomNavire = nomNavire;
		this.imo = imo;
		this.affreteur = affreteur;
		this.chaine = chaine;
		this.dateDemande = dateDemande;
		this.dateSoumission = dateSoumission;
		this.dateTraitement = dateTraitement;
		this.dateRejet = dateRejet;
		this.dateAccepte = dateAccepte;
		this.etat = etat;
		this.lieuDestination = lieuDestination;
		this.adresseExpediteur = adresseExpediteur;
		this.nomExpediteur = nomExpediteur;
		this.nomDestinataire = nomDestinataire;
		this.adresseDestinataire = adresseDestinataire;
		this.typeInspectionPhyto = typeInspectionPhyto;
		this.refTypeInspectionPhyto = refTypeInspectionPhyto;
		this.detPhytosanitaireProduitDtos = detPhytosanitaireProduitDtos;
	}
    
    
    
}
