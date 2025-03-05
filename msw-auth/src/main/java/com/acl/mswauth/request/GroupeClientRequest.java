package com.acl.mswauth.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupeClientRequest implements Serializable {

    //@NotBlank
    private String codeClient;
   
    @NotBlank
    private String groupeClient;
    
    private Long structureId;
}
