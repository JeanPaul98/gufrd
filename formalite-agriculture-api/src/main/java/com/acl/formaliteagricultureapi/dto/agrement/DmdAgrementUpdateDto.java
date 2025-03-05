package com.acl.formaliteagricultureapi.dto.agrement;

import com.acl.formaliteagricultureapi.models.enumeration.StatutPaiement;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * @author kol on 11/18/24
 * @project formalite-agriculture-api
 */
@Getter
@Setter
public class DmdAgrementUpdateDto {

    @Schema(description = "JPA Formalite: Information la formalite",
            name="code")
    @NotNull
    private String code;

    private StatutPaiement statutPaiement;

    private String message;
}
