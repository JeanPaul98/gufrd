package com.acl.formaliteagricultureapi.dto.autorisation.demande;

import com.acl.formaliteagricultureapi.dto.piecejointe.PieceJointeFormaliteDto;
import com.acl.formaliteagricultureapi.dto.produit.DetAutorisationProduitDto;
import com.acl.formaliteagricultureapi.dto.societe.SocieteDto;
import com.acl.formaliteagricultureapi.models.enumeration.TypeRegime;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @author kol on 23/09/2024
 * @project formalite-agriculture-api
 */
@Getter
@Setter
@ToString
@Schema(description ="AutorisationImportDto" )
public class AutorisationImportationDto {

    /*
   Debut formalite
   */
    @Schema(description = "JPA Formalite: Personne connectée login",
            name="compteClient",
            required=true)
    @NotNull
    private String compteClient;

    @Schema(description = "JPA Formalite: Numéro d'identification fiscal",
            name="nif",
            required=true)
    @NotNull
    private String nif;

    @Schema(description = "JPA Formalite: Numero agrément",
            name="numeroAgrement",
            required=true)
    @NotNull
    private String numeroAgrement;

    @Schema(description = "JPA Formalite: Numero dossier du demandeur ATD",
            name="numeroDossier",
            required=true)
    @NotNull
    private String numeroDossier;
    /*
      Fin formalite
     */

    /*
     * Debut autorisation
     */
    @Schema(description = "JPA Formalite: Provenance de la marchandise",
            name="provenance",
            required=true)
    @NotNull
    private String provenance;

    @Schema(description = "JPA Formalite: Provenance Date d'embarquement",
            name="dateEmbarquement",
            required=true)
    @NotNull
    private Date dateEmbarquement;

    @Schema(description = "JPA Formalite: Type regime (transit ou Conso)",
            name="typeRegime",
            required=true)
    @NotNull
    private TypeRegime typeRegime;

    /*
    Fin autorisation
     */

    /*
    Details Autorisation Produit
     */
    @Schema(description = "JPA Detail autorisation Produit: Designation produit , quantite, poids",
            name="detAutorisationProduitDtos")
    private List<DetAutorisationProduitDto> detAutorisationProduitDtos;

    /*
    Details Autorisation Produit
    */

    /*
    Détails Piece jointe formalite

    */

    /*
    Debut piece jointe
     */
    List<PieceJointeFormaliteDto> pieceJointeDtoList ;

    /*
    Fin piece jointe
     */
}
