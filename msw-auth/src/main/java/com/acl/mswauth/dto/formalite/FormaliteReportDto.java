package com.acl.mswauth.dto.formalite;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @author kol on 10/21/24
 * @project msw-auth
 */
@Getter
@Setter
@ToString
public class FormaliteReportDto {

    private Date dateDebut;
    private Date dateFin;
    private String total;
    private String declarant;

    private List<FormaliteStatReportDto> formaliteStatistiqueDtos;
}
