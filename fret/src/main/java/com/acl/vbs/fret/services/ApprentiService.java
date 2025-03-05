package com.acl.vbs.fret.services;

import com.acl.vbs.fret.requests.ApprentiRequest;
import com.acl.vbs.fret.responses.ApprentiResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ApprentiService {
    ApprentiResponse create(ApprentiRequest request);
    List<ApprentiResponse> list();
}
