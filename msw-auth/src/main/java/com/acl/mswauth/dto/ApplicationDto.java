package com.acl.mswauth.dto;

import lombok.*;

import java.io.Serializable;
import jakarta.validation.constraints.NotBlank;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDto implements Serializable {

	@NotBlank
    private String name;

	@NotBlank
    private String description;

    private String logo;

    private String reference;

    private int ordre;

}
