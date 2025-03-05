package com.acl.vbs.responses;

import com.acl.vbs.entities.Pays;
import com.acl.vbs.entities.Transporteur;
import lombok.Data;

import java.io.Serializable;

@Data
public class CamionResponse implements Serializable {
    private String immatriculation;
    private String nomConducteur;
    private Pays codePays;
    private Transporteur codeTransporteur;
}
