package com.acl.vbs.services.impls;

import com.acl.vbs.entities.LigneMse;
import com.acl.vbs.responses.LigneMseResponse;
import com.acl.vbs.projections.LigneMseProjection;
import com.acl.vbs.projections.VaProjection;
import com.acl.vbs.repositories.LigneMseRepository;
import com.acl.vbs.services.LigneMseService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LigneMseServiceImpl implements LigneMseService {

    private final LigneMseRepository ligneMseRepository;

    public LigneMseServiceImpl(LigneMseRepository ligneMseRepository) {
        this.ligneMseRepository = ligneMseRepository;
    }

    @Override
    public List<LigneMseResponse> filterNumBl(String numBl) {
        List<VaProjection> projection = ligneMseRepository.findLigneMseNumBl(numBl);
        return projection.stream().map(this::toLigneMseResponse).toList();
    }

    @Override
    public List<LigneMseResponse> filterNumConteneur(String numConteneur) {
        List<VaProjection> projection = ligneMseRepository.findLigneMseNumConteneur(numConteneur);
        return projection.stream().map(this::toLigneMseResponse).toList();
    }

//    @Override
//    public List<LigneMseResponse> filterNumChassi(String numChassis) {
//        List<LigneMseProjection> projection = ligneMseRepository.findLigneMseChassis(numChassis);
//        return projection.stream().map(this::toLigneMseResponse).toList();
//    }


    private LigneMseResponse toLigneMseResponse(VaProjection ligneMseProjection) {
        LigneMseResponse response = new LigneMseResponse();
        BeanUtils.copyProperties(ligneMseProjection, response);
        return response;
    }

}
