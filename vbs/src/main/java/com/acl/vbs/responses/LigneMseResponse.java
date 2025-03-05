package com.acl.vbs.responses;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class LigneMseResponse {
    private Long idLigneMse;
    private String descMse;
    private String numBl;
    private String numConteneur;
    private String libMarchandise;
    private String descMarchandise;
    private String libTypeConteneur;
    private BigDecimal poidsBrut;
    private String libAireStockage;
}
