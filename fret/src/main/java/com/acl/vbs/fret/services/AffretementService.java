package com.acl.vbs.fret.services;

import com.acl.vbs.fret.requests.AffretementRequest;
import com.acl.vbs.fret.responses.AffretementResponse;

import java.util.List;

public interface AffretementService {
    AffretementResponse create (AffretementRequest request);

    List<AffretementResponse> getAll();
}
