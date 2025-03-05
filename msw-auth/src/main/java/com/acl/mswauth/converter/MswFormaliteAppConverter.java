package com.acl.mswauth.converter;

import com.acl.mswauth.dto.MswConnectedUserDto;
import com.acl.mswauth.dto.MswFormaliteAppliDto;
import com.acl.mswauth.model.MswConnectedUser;
import com.acl.mswauth.model.MswFormaliteApplication;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class MswFormaliteAppConverter {
    /**
     * Dto vers Entity
     * @param dto
     * @return
     */
    public MswFormaliteApplication convertEntity(MswFormaliteAppliDto dto){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, MswFormaliteApplication.class);
    }
}
