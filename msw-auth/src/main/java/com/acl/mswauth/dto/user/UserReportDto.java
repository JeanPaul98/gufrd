package com.acl.mswauth.dto.user;

import com.acl.mswauth.dto.DetailUserDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @author kol on 10/15/24
 * @project msw-auth
 */
@Getter
@Setter
@ToString
public class UserReportDto {

    private Date dateDebut;
    private Date dateFin;
    private int total;
    private int entreprise;
    private int ligneMaritime;
    private int transitaire;
    private int consignataire;
    private int chargeur;
    private int operateurTerminaux;

    private List<DetailUserDto> detailUsers;
}
