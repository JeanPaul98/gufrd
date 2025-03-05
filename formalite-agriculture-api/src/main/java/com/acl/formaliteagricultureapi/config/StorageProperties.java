package com.acl.formaliteagricultureapi.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @author Zansouyé on 29/08/2024
 * @project formalite-agriculture-api
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
