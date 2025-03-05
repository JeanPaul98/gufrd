package com.acl.vbs.services.impls;

import com.acl.vbs.entities.Transporteur;
import com.acl.vbs.repositories.TransporteurRepository;
import com.acl.vbs.responses.TransporteurResponse;
import com.acl.vbs.services.TransporteurService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransporteurServiceImpl implements TransporteurService {

    private final TransporteurRepository transporteurRepository;

    public TransporteurServiceImpl(TransporteurRepository transporteurRepository) {
        this.transporteurRepository = transporteurRepository;
    }

    @Override
    public List<TransporteurResponse> listTransporteur() {
        return transporteurRepository.findAll().stream().map(this::toTransporteurResponse).toList();
    }


    private TransporteurResponse toTransporteurResponse(Transporteur transporteur) {
        TransporteurResponse response = new TransporteurResponse();
        BeanUtils.copyProperties(transporteur, response);
        return response;
    }
}
