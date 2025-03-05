package com.acl.mswauth.request;

import lombok.*;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PortClientRequest implements Serializable {
	
	@NotBlank
	private String codePort;
    
	@NotBlank
	private String compteClient;
    
	@NotBlank
    private String codePortClient;
}
