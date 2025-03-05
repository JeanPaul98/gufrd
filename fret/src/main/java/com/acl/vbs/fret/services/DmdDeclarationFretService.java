package com.acl.vbs.fret.services;


import com.acl.vbs.fret.requests.DmdDeclarationFretRequest;
import com.acl.vbs.fret.responses.DmdDeclarationFretResponse;

import java.util.List;

public interface DmdDeclarationFretService {
    DmdDeclarationFretResponse create(DmdDeclarationFretRequest request);

    List<DmdDeclarationFretResponse> getAllByDeclarant();

    List<DmdDeclarationFretResponse> getAllByChargeur();

    DmdDeclarationFretResponse getById(Long idDeclarationFret);
}