package com.acl.formalitesanteapi.dto.formalite;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Zansouyé on 27/08/2024
 * @project formalite-agriculture-api
 */
@Getter
@Setter
@ToString
@Schema(description ="UpdateFormaliteDto" )
public class UpdateFormaliteDto {

    @Schema(description = "JPA Formalite: Information la formalite",
            name="idFormalite",
            required=true)
    @NotNull
    private Long idFormalite;
    
}
