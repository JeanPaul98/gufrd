package com.acl.formaliteagricultureapi.dto.redevance;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author kol on 16/09/2024
 * @project formalite-agriculture-api
 */
@Getter
@Setter
@ToString
public class CallbackDto {

    private String numero ;

    private String transactionId;

    private double montant;

    private String status;

    private String reference;
}
