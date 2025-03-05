package com.acl.mswauth.servicesImpl.certification;

import com.acl.mswauth.model.DemandeCertification;
import com.acl.mswauth.model.enumeration.Statut;
import com.acl.mswauth.playload.ApiResponseMessage;
import com.acl.mswauth.playload.ApiResponseModel;
import com.acl.mswauth.repositories.DemandeCertificationRepository;
import com.acl.mswauth.service.certification.DdmCertificationListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kol on 11/11/24
 * @project msw-auth
 */
@Service
public class DdmCertificationListServiceImpl implements DdmCertificationListService {

    private final DemandeCertificationRepository demandeCertificationRepository;

    @Autowired
    private Environment env ;

    public DdmCertificationListServiceImpl(DemandeCertificationRepository demandeCertificationRepository) {
        this.demandeCertificationRepository = demandeCertificationRepository;
    }

    @Override
    public ResponseEntity<?> getAllCertificationList(int page, int size) {

        Pageable pageable =  PageRequest.of(page, size);

        Page<DemandeCertification> certificationList = demandeCertificationRepository.getDemandeCertificationByEtat(
                Statut.TERMINE,pageable
        );

        if(certificationList.hasContent()) {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.application.vide"),
                    certificationList),
                    HttpStatus.OK);
        }
        else  {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.application.sucess"),
                    certificationList),
                    HttpStatus.OK);
        }
    }
}
