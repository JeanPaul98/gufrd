package com.acl.vbs.fret.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class AffretementRequest implements Serializable {
    @NotNull
    private Long conducteur;
    @NotNull
    private Long camion;
    @NotNull
    private Long declarationFret;
    @NotNull
    @Min(0)
    private Long poidsAffecte;
}
