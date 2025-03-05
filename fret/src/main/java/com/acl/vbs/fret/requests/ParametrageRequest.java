package com.acl.vbs.fret.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class ParametrageRequest implements Serializable {
    @NotNull
    @NotBlank
    private String nationalite;

    @NotNull
    @Min(0)
    private Long quota;
}
