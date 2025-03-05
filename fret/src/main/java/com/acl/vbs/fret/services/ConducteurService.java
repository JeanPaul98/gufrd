package com.acl.vbs.fret.services;

import com.acl.vbs.fret.requests.ConducteurRequest;
import com.acl.vbs.fret.responses.ConducteurResponse;
import com.acl.vbs.fret.responses.DeclarantResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ConducteurService {
    ConducteurResponse create(ConducteurRequest request);
    List<ConducteurResponse> list();
}
