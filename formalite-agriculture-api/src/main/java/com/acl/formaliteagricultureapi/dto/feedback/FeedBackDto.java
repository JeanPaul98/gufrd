package com.acl.formaliteagricultureapi.dto.feedback;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author kol on 10/6/24
 * @project formalite-agriculture-api
 */
@Getter
@Setter
@ToString
public class FeedBackDto {

    /*
 Formalite
  */
    @Schema(description = "JPA Formalite: ID de la formalite",
            name = "idFormalite",
            required = true)
    @NotNull
    private Long idFormalite;

    /*
    Fin de Formalite

     */

    /*
    Debut feed back
     */
    @Schema(description = "JPA feedback",
            name = "record",
            required = true)
    @NotNull
    private String record;

    @Schema(description = "JPA feedback",
            name = "process",
            required = true)
    @NotNull
    private String process;

    @Schema(description = "JPA feedback",
            name = "step",
            required = true)
    @NotNull
    private String step;

    @Schema(description = "JPA feedback",
            name = "order",
            required = true)
    @NotNull
    private long order;


    @Schema(description = "JPA feedback",
            name = "feedbackTaskId",
            required = true)
    @NotNull
    private String feedbackTaskId;

    /*
    fin feedback
     */

    @Schema(description = "JPA feedback",
            name = "description")
    private String description;

}
