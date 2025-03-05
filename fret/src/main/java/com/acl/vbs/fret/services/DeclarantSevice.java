package com.acl.vbs.fret.services;

import com.acl.vbs.fret.requests.DeclarantRequest;
import com.acl.vbs.fret.responses.DeclarantResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeclarantSevice {
    DeclarantResponse create(DeclarantRequest request);
    List<DeclarantResponse> list();
}
