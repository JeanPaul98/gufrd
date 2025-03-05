package com.acl.mswauth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PortDto implements Serializable {

    @NotBlank
    private String code;

    @NotBlank
    private String codePays;

    @NotBlank
    private String nom;

    private String locode;
}
