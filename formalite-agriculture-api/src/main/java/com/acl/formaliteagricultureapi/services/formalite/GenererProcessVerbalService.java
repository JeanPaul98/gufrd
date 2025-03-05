package com.acl.formaliteagricultureapi.services.formalite;

import com.acl.formaliteagricultureapi.dto.procesVerbal.UpdateProcesVerbalDto;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author kol on 28/09/2024
 * @project formalite-agriculture-api
 */
@Service
public interface GenererProcessVerbalService {

    ResponseEntity<byte[]> genererProcessVerbalNavire(Long idProcessVerbal) throws JRException, IOException;


}
