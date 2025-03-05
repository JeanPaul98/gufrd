package com.acl.formaliteagricultureapi.dto.formalite;

import com.acl.formaliteagricultureapi.dto.procesVerbal.DetPVInspecteurDto;
import com.acl.formaliteagricultureapi.dto.procesVerbal.DetPvProduitDto;
import com.acl.formaliteagricultureapi.dto.procesVerbal.UpdateDetPvProduitDto;
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
 * @author kol on 28/09/2024
 * @project formalite-agriculture-api
 */
@Getter
@Setter
@ToString
@Schema(description ="GenererProcessVerbalDto" )
public class GenererProcessVerbalDto {


    @Schema(description = "JPA Formalite: Information de la formalite",
            name="datearrivee",
            required=true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date datearrivee;

    /*
     * Fin Formalite
     */

    /*
     * Debut ProcesVerbal
     */
    @Schema(description = "JPA Formalite: Information du navire",
            name="via",
            required=true)
    @NotNull
    private String via;

    @Schema(description = "JPA Formalite: Information de Proces Verbal",
            name="dateInspection",
            required=true)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateInspection;


    @Schema(description = "JPA Formalite: Information de Proces Verbal",
            name="dateDepartPrevue",
            required=true)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDepartPrevue;

    @Schema(description = "JPA Formalite: Information de Proces Verbal",
            name="partieNavireVisitee",
            required=false)
    private String partieNavireVisitee;

    @Schema(description = "JPA Formalite: Information de Proces Verbal",
            name="officierNavire",
            required=true)
    @NotNull
    private String officierNavire;

    @Schema(description = "JPA Formalite: Information de Proces Verbal",
            name="lieuInspection",
            required=true)
    @NotNull
    private String lieuInspection;


    @Schema(description = "JPA Formalite: Information de Proces Verbal",
            name="commandant",
            required=false)
    private String commandant;

    @Schema(description = "JPA Formalite: Information de Proces Verbal",
            name="datePv",
            required=true)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date datePv;

    @Schema(description = "JPA Formalite: Information de Proces Verbal",
            name="remarque",
            required=false)
    private String remarque;

    @Schema(description = "JPA Formalite: Information de Proces Verbal",
            name="agentPV",
            required=true)
    private String agentPV;

    @Schema(description = "JPA Formalite: Information de Proces Verbal",
            name="qrCode")
    private String qrCode;

    /*
     * Fin ProcesVerbal
     */

    /*
      Debut Detail Proces Verbal Produit
     */

    private List<UpdateDetPvProduitDto> detPvProduitDtoList;

    /*
      Fin Detail Proces Verbal Produit
     */

    /*
     *   Debut DetProcesVerbalInspecteur
     */
    private List<DetPVInspecteurDto>detPVInspecteurDtos;

    /*
     *   Fin DetProcesVerbalInspecteur
     */
}
