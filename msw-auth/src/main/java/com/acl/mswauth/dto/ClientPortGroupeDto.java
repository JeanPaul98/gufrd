package com.acl.mswauth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

/**
 * @author kol on 01/10/2024
 * @project msw-auth
 */
@Getter
@Setter
@Schema(description ="ClientPortGroupeDto" )
public class ClientPortGroupeDto {

    /*
Debut client
    */
    @Schema(description = "JPA: compte client de l'utilisateur ",
            name="compteClient",
            required=true)
    @NotNull
    private String compteClient;

    @Schema(description = "JPA: Groupe utilisateur, groupe utilisateur ",
            name="groupes")
    List<GroupeDtoUpdate> groupes;

    @Schema(description = "JPA: Groupe utilisateur: code port client",
            name="codePort")
    String  codePort;
}
