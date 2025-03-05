package com.acl.vbs.fret.services;

import com.acl.vbs.fret.requests.GarantRequest;
import com.acl.vbs.fret.responses.GarantResponse;

import java.util.List;

public interface GarantService {

    GarantResponse create(GarantRequest request);
    List<GarantResponse> list();
}
