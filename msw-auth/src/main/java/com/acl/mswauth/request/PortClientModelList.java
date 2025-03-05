package com.acl.mswauth.request;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PortClientModelList {
    private String nomPort;
    private String codePort;
    private String pays;
    private String codePays;
    private String nomClient;
    private String loginName;
    private String codeClient;
    private String sessionId;
}
