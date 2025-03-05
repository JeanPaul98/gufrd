package com.acl.formaliteagricultureapi.controller.general;

import com.acl.formaliteagricultureapi.services.general.TypePvService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/pv")
public class TypePvController {

    private final TypePvService typePvService;

    public TypePvController(TypePvService typePvService) {
        this.typePvService = typePvService;
    }


    @GetMapping
    public ResponseEntity<?> getAllTypePv() {
    return typePvService.getAllTypePv();
}
}
