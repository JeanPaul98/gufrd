package com.acl.mswauth.converter;

import com.acl.mswauth.model.MswPort;
import com.acl.mswauth.dto.PortDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PortConverter {
    /**
     * Dto vers Entity
     * @param portDto
     * @return
     */
    public MswPort convertEntity(PortDto portDto){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(portDto, MswPort.class);
    }
}
