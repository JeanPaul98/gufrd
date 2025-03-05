package com.acl.formaliteagricultureapi.services.autorisation.laisserPasser;

import com.acl.formaliteagricultureapi.dto.autorisation.LaisserPasserDto;
import com.acl.formaliteagricultureapi.dto.formalite.UpdateFormaliteDto;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author Zansouyé on 10/09/2024
 * @project formalite-agriculture-api
 */
@Service
public interface LaisserPasserService {

    ResponseEntity<?> createLaisserPasser(LaisserPasserDto laisserPasserDto);

    ResponseEntity<byte[]> generateLaisserPasser(UpdateFormaliteDto updateFormaliteDto) throws JRException, IOException;
}
