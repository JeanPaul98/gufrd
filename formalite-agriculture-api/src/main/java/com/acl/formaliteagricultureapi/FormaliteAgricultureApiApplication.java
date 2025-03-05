package com.acl.formaliteagricultureapi;


import com.acl.formaliteagricultureapi.services.file.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@Slf4j
public class FormaliteAgricultureApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FormaliteAgricultureApiApplication.class, args);
    }


}
