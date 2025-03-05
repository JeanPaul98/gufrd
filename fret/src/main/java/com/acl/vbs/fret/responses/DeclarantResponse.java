package com.acl.vbs.fret.responses;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class DeclarantResponse implements Serializable {
    private Long id;
    private String maisonTransit;
    private String telephone;
    private String mail;
    private String compteClient;
    private String createdBy;
    private Date createdAt;
    private String updateBy;
    private Date updateAt;
}
