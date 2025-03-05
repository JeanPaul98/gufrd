package com.acl.vbs.fret.services;

import com.acl.vbs.fret.requests.CamionRequest;
import com.acl.vbs.fret.requests.ChargeurRequest;
import com.acl.vbs.fret.responses.CamionResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface CamionService {
    List<CamionResponse> listeCamion();

    CamionResponse creationCamion(CamionRequest request);

    CamionResponse prendreCamion(String immatriculation);
}
