package com.acl.formaliteagricultureapi.dto.redevance;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * @author kol on 30/08/2024
 * @project formalite-agriculture-api
 */
@Getter
@Setter
@Schema(description ="RedevanceDto" )
public class RedevanceDto {

    @Schema(description = "JPA Formalite  : Id de la formalite",
            name="idFormalite",
            required=true)
    @NotNull
    private Long idFormalite;

    @Schema(description = "JPA Formalite  : formalite  de la demande",
            name="type",
            required=true)
    @NotNull
    private String type;



}
