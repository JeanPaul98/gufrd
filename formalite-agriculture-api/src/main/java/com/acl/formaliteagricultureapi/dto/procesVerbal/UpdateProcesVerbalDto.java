package com.acl.formaliteagricultureapi.dto.procesVerbal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author kol on 28/09/2024
 * @project formalite-agriculture-api
 */
@Getter
@Setter
@ToString
@Schema(description ="UpdateProcesVerbal" )
public class UpdateProcesVerbalDto {

    @Schema(description = "JPA Formalite: Id du process Verbal",
            name="idProcessVerbal",
            required=true)
    @NotNull
    private Long idProcessVerbal;

}
