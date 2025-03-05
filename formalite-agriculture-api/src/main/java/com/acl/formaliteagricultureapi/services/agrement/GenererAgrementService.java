package com.acl.formaliteagricultureapi.services.agrement;

import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author kol on 11/18/24
 * @project formalite-agriculture-api
 */
@Service
public interface GenererAgrementService {

    ResponseEntity<byte[]> genererAgrement(String code) throws JRException, IOException;

}
