package com.acl.vbs.fret.responses;

import lombok.Data;

import java.io.Serializable;

@Data
public class MSWUserResponse implements Serializable {
    private Long idUser;
    private String login;
    private String fullname;
    private String lastName;
    private String firstName;
    private String email;
    private String mobile;
    private String entreprise;
    private String fonction;
    private String groupe;
    private String compteClient;
    private ChargeurResponse chargeur;
    private ParametrageResponse parametrage;
}
