package com.acl.mswauth.request;

import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StructureRequest implements Serializable {
	
	private String nom;
	
	private String email;
	
	private String adresse;
	
	private String telephone;
	
	private String siteWeb;
    
    private String codePays; 
   
}
