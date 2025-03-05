package com.acl.vbs.fret.services;

import com.acl.vbs.fret.requests.ChargeurRequest;
import com.acl.vbs.fret.responses.ChargeurResponse;

import java.util.List;

public interface ChargeurService {
    List<ChargeurResponse> listeChargeur();

    ChargeurResponse creationChargeur(ChargeurRequest request);

    ChargeurResponse prendreChargeur(String nom);
}
