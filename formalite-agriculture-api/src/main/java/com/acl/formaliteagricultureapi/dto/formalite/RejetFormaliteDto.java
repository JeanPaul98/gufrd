package com.acl.formaliteagricultureapi.dto.formalite;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author kol on 05/09/2024
 * @project formalite-agriculture-api
 */
@Getter
@Setter
@ToString
@Schema(description ="RejetFormaliteDto" )
public class RejetFormaliteDto {

    @Schema(description = "JPA Formalite: Information la formalite",
            name="idFormalite",
            required=true)
    @NotNull
    private Long idFormalite;

    @Schema(description = "JPA Formalite: Motif de rejet",
            name="motifRejet",
            required=true)
    @NotNull
    private String motifRejet;

    @Schema(description = "JPA Formalite: Code agent pour la validation",
            name="codeAgent",
            required=true)
    @NotNull
    private String codeAgent;

}
