package com.acl.formaliteenvironnementapi.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

/**
 * @author jean paul 24/09/2024
 * @project formalite-sante-api
 */
@Configuration
@Getter
@Setter
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String location = "/uploads";

    private String destination = "http://102.164.230.196:4300";

}
