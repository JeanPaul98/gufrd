package com.acl.vbs.responses;

import lombok.Data;

import java.io.Serializable;

@Data
public class TransporteurResponse implements Serializable {
    private String codeTransporteur;
    private String libTransporteur;
}
