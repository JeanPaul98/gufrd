package com.acl.vbs.fret.services;

import com.acl.vbs.fret.requests.PaiementRequest;
import com.acl.vbs.fret.responses.PaiementResponse;

public interface PaiementService {
    PaiementResponse payementDeclaration(Long declarationId);
}
