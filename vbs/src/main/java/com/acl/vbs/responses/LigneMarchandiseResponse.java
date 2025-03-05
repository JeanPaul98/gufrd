package com.acl.vbs.responses;

import com.acl.vbs.entities.Bl;
import com.acl.vbs.entities.Conteneur;
import com.acl.vbs.entities.Marchandise;
import com.acl.vbs.entities.Pays;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class LigneMarchandiseResponse {
    private Long idLigneMse;
    private String descMse;
    private BigDecimal poidsBrut;
    private Bl idBl;
    private Conteneur numConteneur;
    private Marchandise codeMarchandise;
    private Pays paysOrigine;
}
