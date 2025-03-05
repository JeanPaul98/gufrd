package com.acl.formaliteagricultureapi.dto.formalite;

import lombok.Getter;
import lombok.Setter;

/**
 * @author kol on 10/6/24
 * @project formalite-agriculture-api
 */
@Getter
@Setter
public class FormaliteUpdateFeedDto {

    String etat;

    String StatutPaiement;

    double montantRedevance;

}
