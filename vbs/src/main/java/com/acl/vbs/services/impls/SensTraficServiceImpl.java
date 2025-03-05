package com.acl.vbs.services.impls;

import com.acl.vbs.entities.SensTrafic;
import com.acl.vbs.repositories.SensTraficRepository;
import com.acl.vbs.services.SensTraficService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensTraficServiceImpl implements SensTraficService {

    private final SensTraficRepository sensTraficRepository;

    public SensTraficServiceImpl(SensTraficRepository sensTraficRepository) {
        this.sensTraficRepository = sensTraficRepository;
    }

    @Override
    public List<SensTrafic> getSensTrafic() {
        return sensTraficRepository.list();
    }

}
