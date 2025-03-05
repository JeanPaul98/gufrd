/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acl.mswauth.playload;

import com.acl.mswauth.model.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 *
 * @author Jean Paul
 */
@Getter
@Setter
public class JwtAuthenticationResponse {

    private String accessToken;
    private String tokenType = "Bearer";
    private ReturnUser returnUser;

    public JwtAuthenticationResponse(String accessToken, String tokenType, ReturnUser returnUser) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.returnUser = returnUser;
    }
}
