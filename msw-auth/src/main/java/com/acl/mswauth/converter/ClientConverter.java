package com.acl.mswauth.converter;


import com.acl.mswauth.model.MswClient;

import com.acl.mswauth.dto.ClientDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ClientConverter {

    /**
     * Client Request
     * @param mswClient
     * @return
     */


    public ClientDto convertDto(MswClient mswClient){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(mswClient, ClientDto.class);
    }

    /**
     *
     * @param clientDto
     * @return
     */

    public MswClient convertEntity(ClientDto clientDto){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(clientDto, MswClient.class);
    }
}
