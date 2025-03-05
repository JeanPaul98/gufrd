package com.acl.mswauth.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
public class GroupeDto {

    @NotBlank
    private String nomGroupe;

}
