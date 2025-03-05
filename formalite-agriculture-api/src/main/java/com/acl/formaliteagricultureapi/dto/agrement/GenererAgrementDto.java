package com.acl.formaliteagricultureapi.dto.agrement;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author kol on 11/18/24
 * @project formalite-agriculture-api
 */
@Getter
@Setter
@Slf4j
public class GenererAgrementDto {


    private String arrete;

    private String numeroDemande;

    private List<DetAgrementDto> detAgrementDtoList;

    public GenererAgrementDto(String numero, String arrete, List<DetAgrementDto> detAgrementDtoList) {
        this.numeroDemande = numero;
        this.arrete = arrete;
        this.detAgrementDtoList = detAgrementDtoList;
    }
}
