package com.acl.formaliteagricultureapi.dto.formalite;

import com.acl.formaliteagricultureapi.dto.feedback.FeedBackDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * @author kol on 10/6/24
 * @project formalite-agriculture-api
 */
@Getter
@Setter
public class FeedBackFormaliteDto {


    @Schema(description = "JPA feedback",
            name = "title",
            required = true)
    @NotNull
    private String title;

    @Schema(description = "JPA feedback",
            name = "message",
            required = true)
    @NotNull
    private String message;

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

    @Schema(description = "JPA feedback",
            name = "motifRejet")
    private String motifRejet;

    @Schema(description = "JPA feedback",
            name = "piece")
    private String piece;

    @Schema(description = "JPA feedback, transaction ID",
            name = "transactionId")
    private String transactionId;

    private  FormaliteUpdateFeedDto formaliteUpdateFeedDto;
}
