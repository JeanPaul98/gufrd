package com.acl.mswauth.playload;

import com.acl.mswauth.model.Role;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class ReturnUser {
    private String login;
    private String fullname;
    private String compteClient;
    private String nomClient;
    private String email;
    private String fonction;
    private String mobile;
    private String nomStructure;
    private Set<Role> roles;

    public ReturnUser(String login, String fullname, String compteClient,
                      String nomClient, String email, Set<Role> roles) {
    	
        this.login = login;
        this.fullname = fullname;
        this.compteClient = compteClient;
        this.nomClient = nomClient;
        this.email = email;
        this.roles = roles;
    }
    
    public ReturnUser(String login, String fullname, String compteClient,
                      String nomClient, String email,String mobile, String fonction, Set<Role> roles) {

                      this(login, fullname, compteClient, nomClient, email, roles);
                      this.mobile = mobile;
                      this.fonction = fonction;
}
    
    
    public ReturnUser(String login, String fullname, String nomStructure, 
    		          String email, String mobile, String fonction, Set<Role> roles) {
    	
    	this(login, fullname, null, null, email, roles);
        this.mobile = mobile;
        this.fonction = fonction;
        this.nomStructure = nomStructure;


}
}
