package com.acl.formaliteagricultureapi.dto.formalite;

import com.acl.formaliteagricultureapi.models.enumeration.StatutPaiement;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

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

    private StatutPaiement statutPaiement;

}
