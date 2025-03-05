package com.acl.vbs.fret.services;

import com.acl.vbs.fret.requests.TransporteurRequest;
import com.acl.vbs.fret.responses.TransporteurResponse;

import java.util.List;

public interface TransporteurService {
    List<TransporteurResponse> listeTransporteur();

    TransporteurResponse creationTransporteur(TransporteurRequest request);

    TransporteurResponse prendreTransporteur(String raisonSociale);
}
