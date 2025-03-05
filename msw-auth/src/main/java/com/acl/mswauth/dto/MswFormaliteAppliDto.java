package com.acl.mswauth.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MswFormaliteAppliDto {

    private Long id;
    private String name;
    private String description;
    private String url;
    private String referenceApplication;
}
