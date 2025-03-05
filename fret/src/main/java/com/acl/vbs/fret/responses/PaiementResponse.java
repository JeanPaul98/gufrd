package com.acl.vbs.fret.responses;

import lombok.Data;

import java.io.Serializable;

@Data
public class PaiementResponse implements Serializable {
    private String status;
    private String url;
}
