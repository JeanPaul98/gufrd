package com.acl.formaliteagricultureapi.dto.procesVerbal;

import com.acl.formaliteagricultureapi.models.Inspecteur;
import com.acl.formaliteagricultureapi.models.ProcesVerbal;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Zansouyé on 26/08/2024
 * @project formalite-agriculture-api
 */
@Getter
@Setter
@Schema(description = "DetPVInspecteurDto")
public class DetPVInspecteurDto {

    /*
    * Debut Detail Proces Verbal Inspecteur
     */

    @Schema(description = "JPA Formalite: Information detPVInspecteur",
            name="fonction",
            required=true)
    @NotNull
    private String fonction;

    /*
    * Fin Detail Proces Verbal Inspecteur
     */

    /*
    * Debut Inspecteur
     */

    @Schema(description = "JPA Formalite: Information Inspecteur",
            name="idInspecteur",
            required=true)
    @NotNull
    private Long  idInspecteur;

    @Schema(description = "JPA Formalite: Nom inspecteur",
            name="nomInspecteur"
            )
    private String  nomInspecteur;

    public DetPVInspecteurDto(Long id, String nomInspecteur, String fonction) {
        this.idInspecteur = id;
        this.nomInspecteur = nomInspecteur;
        this.fonction = fonction;
    }

    /*
     * Fin Inspecteur
     */
}
