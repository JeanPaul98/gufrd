package com.acl.mswauth.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author kol on 28/09/2024
 * @project msw-auth
 */
@Getter
@Setter
public class ClientGroupeDto {

    private String compteClient;

    private List<GroupeDto> groupeDtos;

}
