package com.acl.escalenavire.dto;

import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.acl.escalenavire.models.LigneMse}
 */
@Value
public class LigneMseDto implements Serializable {

    String descMse;

    String plomb;

    String typeLigneMse;

    String codeMarchandise;

    String numConteneur;

    String codeSituMse;

    BigDecimal poidsBrut;

    String codeCondition;

    String paysOrigine;

    String manutentionnaire;

}