package com.acl.mswauth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PortClientDto {

    private String compteClient;

    private List<PortDto> portDtos;

}
