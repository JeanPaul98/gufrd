package com.acl.vbs.services.impls;

import com.acl.vbs.entities.Pays;
import com.acl.vbs.repositories.PaysRepository;
import com.acl.vbs.responses.PaysResponse;
import com.acl.vbs.services.PaysService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PaysServiceImpl implements PaysService {

    private final PaysRepository repository;

    @Override
    public List<PaysResponse> listPays() {
        return repository.findAll().stream().map(this::toPaysResponse).toList();
    }

    private PaysResponse toPaysResponse(Pays pays) {
        PaysResponse response = new PaysResponse();
        BeanUtils.copyProperties(pays, response);
        return response;
    }
}
