package com.acl.vbs.fret.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;


@Data
public class TypePieceRequest implements Serializable {
    @NotNull
    @NotBlank
    private String libelle;
}
