package com.acl.vbs.services;

import com.acl.vbs.requests.CamionRequest;
import com.acl.vbs.responses.CamionResponse;

import java.util.List;

public interface CamionService {
    List<CamionResponse> listeCamion();

    CamionResponse creationCamion(CamionRequest request);

    CamionResponse prendreCamion(String immatriculation);
}
