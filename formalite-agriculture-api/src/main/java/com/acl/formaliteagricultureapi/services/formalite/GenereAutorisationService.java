package com.acl.formaliteagricultureapi.services.formalite;

import com.acl.formaliteagricultureapi.dto.formalite.GenereAutorisationDto;
import com.acl.formaliteagricultureapi.dto.formalite.UpdateFormaliteDto;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author Zansouyé on 09/09/2024
 * @project formalite-agriculture-api
 */
@Service
public interface GenereAutorisationService {

    ResponseEntity<byte[]> genereAutorisation(UpdateFormaliteDto updateFormaliteDto) throws JRException, IOException;
}
