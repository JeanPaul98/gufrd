package com.acl.formaliteagricultureapi.dto.atd;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author kol on 10/7/24
 * @project formalite-agriculture-api
 */
@Getter
@Setter
@ToString
public class QrCodeDto {

    private String slug;
    private  String refDocument;
     private String  typeDocument;
  private String  modeUtilisation;
    private String        autoriteCompetente;
   private String nomSociete;
 private String   nif;
   private String  cat;
    private String        exp;
    private String rat;
}
