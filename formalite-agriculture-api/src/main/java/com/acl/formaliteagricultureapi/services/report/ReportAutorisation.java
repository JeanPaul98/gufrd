package com.acl.formaliteagricultureapi.services.report;

import com.acl.formaliteagricultureapi.dto.autorisation.LaisserPasserDto;
import com.acl.formaliteagricultureapi.dto.formalite.GenereAutorisationDto;
import com.acl.formaliteagricultureapi.models.Formalite;
import net.sf.jasperreports.engine.JRException;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;


/**
 * @author Zansouyé on 09/09/2024
 * @project formalite-agriculture-api
 */
@Service
public interface ReportAutorisation {

    InputStream reportAutorisationConfig(GenereAutorisationDto genereAutorisationDto,
                                         String filePath, String format,
                                         Formalite formalite) throws JRException, IOException;


    InputStream reportAutorisationLaisserPasser(LaisserPasserDto laisserPasserDto,
                                                String filePath, String format) throws JRException, IOException;
}
