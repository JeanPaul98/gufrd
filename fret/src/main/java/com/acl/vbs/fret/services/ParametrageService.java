package com.acl.vbs.fret.services;

import com.acl.vbs.fret.requests.ParametrageRequest;
import com.acl.vbs.fret.responses.ParametrageResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface ParametrageService {
    List<ParametrageResponse> listeParametrage();

    ParametrageResponse creationParametrage(@Valid ParametrageRequest request);

    ParametrageResponse prendreParametrage(Long id);
}
