package com.acl.mswauth.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PortAppliRequest implements Serializable {

    @NotBlank
    private String reference;
    @NotBlank
    private String codePort;
    @NotBlank
    private String url;

}
