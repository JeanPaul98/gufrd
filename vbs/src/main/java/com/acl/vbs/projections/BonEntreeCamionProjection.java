package com.acl.vbs.projections;

import java.math.BigDecimal;
import java.util.Date;

public interface BonEntreeCamionProjection {

    String getNumBonEntreeCamion();

    Date getDateBonEntree();

    String getImmatriculation();

    String getNomConducteur();

    String getRemorque();

    BigDecimal getPoidsVide();

    BigDecimal getPoidsCharge();

    String getObservationPesage();

    String getRaisonSocialTransitaire();

    String getNomTransitaire();

    String getLibellePays();

    BigDecimal getRedevance();
}
