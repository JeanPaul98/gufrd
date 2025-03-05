package com.acl.formaliteagricultureapi.services.report;

import com.acl.formaliteagricultureapi.dto.formalite.GenerateCertificatDto;
import com.acl.formaliteagricultureapi.dto.formalite.GenererProcessVerbalDto;
import com.acl.formaliteagricultureapi.models.Certificat;
import com.acl.formaliteagricultureapi.models.Formalite;
import com.acl.formaliteagricultureapi.models.ProcesVerbal;
import net.sf.jasperreports.engine.JRException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author kol on 10/4/24
 * @project formalite-agriculture-api
 */
@Service
public interface ReportCertificatService {

    InputStream reportProcessConfig(GenerateCertificatDto generateCertificatDto,
                                    String filePath, String format,
                                    Formalite formalite) throws JRException, IOException;
}
