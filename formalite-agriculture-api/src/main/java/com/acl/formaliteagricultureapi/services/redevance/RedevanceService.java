package com.acl.formaliteagricultureapi.services.redevance;

import com.acl.formaliteagricultureapi.dto.formalite.UpdateFormaliteDto;
import com.acl.formaliteagricultureapi.dto.redevance.CallbackDto;
import com.acl.formaliteagricultureapi.dto.redevance.PaiementDto;
import com.acl.formaliteagricultureapi.dto.redevance.RedevanceDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 30/08/2024
 * @project formalite-agriculture-api
 */
@Service
public interface RedevanceService {

    public ResponseEntity<?> paye(UpdateFormaliteDto request);

    ResponseEntity<?> callback(CallbackDto request);

    ResponseEntity<?> callbackAtd(CallbackDto request);

    public ResponseEntity<?> payement(PaiementDto request);
}
