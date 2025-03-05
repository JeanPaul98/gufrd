package com.acl.vbs.responses;

import com.acl.vbs.entities.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class BonEntreeCamionResponse {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateBonEntree;
    private String remorque;
    private Camion immatriculation;
    private Boolean transporteMse;
    private SensTrafic codeSensTrafic;
    private Transitaire transitaire;
    private Site sitePesage;
    private Boolean statutPayement;
    private Date datePayement;
}
