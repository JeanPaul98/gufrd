package com.acl.formaliteagricultureapi.services.formalite;

import com.acl.formaliteagricultureapi.dto.formalite.UpdateFormaliteDto;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author kol on 10/4/24
 * @project formalite-agriculture-api
 */
@Service
public interface GenerateCertificatService {
    ResponseEntity<byte[]> generateCertificat(Long idFormalite) throws JRException, IOException;

}
