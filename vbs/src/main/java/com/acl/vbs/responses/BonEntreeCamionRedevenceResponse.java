package com.acl.vbs.responses;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class BonEntreeCamionRedevenceResponse implements Serializable {
    private BigDecimal redevance;
}
