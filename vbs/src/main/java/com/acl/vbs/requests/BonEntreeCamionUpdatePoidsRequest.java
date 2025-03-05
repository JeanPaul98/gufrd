package com.acl.vbs.requests;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BonEntreeCamionUpdatePoidsRequest {
    private BigDecimal poidsVide;
    private BigDecimal poidsCharge;
    private String observationPesage;
}
