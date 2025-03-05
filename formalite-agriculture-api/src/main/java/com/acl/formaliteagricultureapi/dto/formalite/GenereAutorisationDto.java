package com.acl.formaliteagricultureapi.dto.formalite;

import com.acl.formaliteagricultureapi.dto.produit.DetAutorisationProduitDto;
import com.acl.formaliteagricultureapi.dto.produit.ReportDetAutorisationProduitDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author Zansouyé on 09/09/2024
 * @project formalite-agriculture-api
 */
@Getter
@Setter
@ToString
@Schema(description ="GenereAutorisationDto" )
public class GenereAutorisationDto {

    /*
    * Debut Formalite
     */
        private String nomImportateur;
    /*
    * Fin Formalite
     */

    /*
    *   Debut Det Autorisation
     */
    private  List<ReportDetAutorisationProduitDto> detAutorisationProduitDtos;
    /*
    *   Fin Det Autorisation
     */

    private String qrCode;
}
