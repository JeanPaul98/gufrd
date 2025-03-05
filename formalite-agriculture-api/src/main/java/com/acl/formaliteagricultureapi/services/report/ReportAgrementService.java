package com.acl.formaliteagricultureapi.services.report;

import com.acl.formaliteagricultureapi.dto.agrement.GenererAgrementDto;
import com.acl.formaliteagricultureapi.dto.formalite.GenererProcessVerbalDto;
import com.acl.formaliteagricultureapi.models.ProcesVerbal;
import net.sf.jasperreports.engine.JRException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author kol on 28/09/2024
 * @project formalite-agriculture-api
 */
@Service
public interface ReportAgrementService {

    InputStream reportAgrementProcessConfig(GenererAgrementDto genererAgrementDto,
                                            String filePath, String format) throws JRException, IOException;

}
