package com.acl.vbs.fret.services;

import com.acl.vbs.fret.requests.DestinataireMarchandiseRequest;
import com.acl.vbs.fret.responses.DestinataireMarchandiseResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DestinataireMarchandiseService {

    DestinataireMarchandiseResponse create(DestinataireMarchandiseRequest request);

    List<DestinataireMarchandiseResponse> getAll();
}
