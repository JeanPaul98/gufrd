package com.acl.vbs.responses;

import com.acl.vbs.entities.Camion;
import com.acl.vbs.entities.SensTrafic;
import com.acl.vbs.entities.Site;
import com.acl.vbs.entities.Transitaire;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class DetailBonEntreeCamionResponse implements Serializable {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateBonEntree;
    private String remorque;
    private Camion immatriculation;
    private Boolean transporteMse;
    private SensTrafic codeSensTrafic;
    private Transitaire transitaire;
    private Site sitePesage;
    private List<LigneMarchandiseResponse> ligneMseList;
}
