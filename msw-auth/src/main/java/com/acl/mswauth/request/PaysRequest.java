package com.acl.mswauth.request;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaysRequest implements Serializable {

    private String codePays;
    private String nom;


}
