package com.acl.vbs.projections;

import java.math.BigDecimal;

public interface VaProjection {

    Long getIdLigneMse();

    String getDescMse();

    String getNumBl();

    String getNumConteneur();

    String getLibMarchandise();

    String getDescMarchandise();

    String getLibTypeConteneur();

    BigDecimal getPoidsBrut();

//    String getNumCahssis();

    String getLibAireStockage();
}
