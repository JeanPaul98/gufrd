package com.acl.vbs.services;

import com.acl.vbs.entities.LigneMse;
import com.acl.vbs.responses.LigneMseResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LigneMseService {
    List<LigneMseResponse> filterNumBl(String numBl);

    List<LigneMseResponse> filterNumConteneur(String numConteneur);

//    List<LigneMseResponse> filterNumChassi(String numChassis);

//    Page<LigneMse> list(int page, int size);

}
