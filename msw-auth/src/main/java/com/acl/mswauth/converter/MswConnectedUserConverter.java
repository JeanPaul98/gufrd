package com.acl.mswauth.converter;

import com.acl.mswauth.dto.MswConnectedUserDto;
import com.acl.mswauth.model.MswConnectedUser;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class MswConnectedUserConverter {

    /**
     * Dto vers Entity
     * @param dto
     * @return
     */
    public MswConnectedUser convertEntity(MswConnectedUserDto dto){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, MswConnectedUser.class);
    }
}
