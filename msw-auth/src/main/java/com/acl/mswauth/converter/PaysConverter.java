package com.acl.mswauth.converter;

import com.acl.mswauth.model.MswPays;
import com.acl.mswauth.request.PaysRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PaysConverter {

    /**
     * Dto vers Entity
     * @param paysRequest
     * @return
     */
    public MswPays convertEntity(PaysRequest paysRequest){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(paysRequest, MswPays.class);
    }
}
