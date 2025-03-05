package com.acl.formaliteagricultureapi.dto.atd;

import lombok.Getter;
import lombok.Setter;

/**
 * @author kol on 10/7/24
 * @project formalite-agriculture-api
 */
@Getter
@Setter
public class ResponseQrCodeDto {

    private  boolean success;

    private String file;

    private String link;
}
