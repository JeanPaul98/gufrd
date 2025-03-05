package com.acl.formaliteagricultureapi.services.processVerval.pharma;

import com.acl.formaliteagricultureapi.dto.procesVerbal.PvPhytoPharmaDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 27/08/2024
 * @project formalite-agriculture-api
 */
@Service
public interface PvPhytoProduitPharmaService {

    ResponseEntity<?> create(PvPhytoPharmaDto request);
}
