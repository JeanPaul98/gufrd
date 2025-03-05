package com.acl.mswauth.converter;

import com.acl.mswauth.model.MswApplication;
import com.acl.mswauth.dto.ApplicationDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class MswApplicationConverter {

    public ApplicationDto convertDto(MswApplication mswApplication){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(mswApplication, ApplicationDto.class);
    }


    public MswApplication convertEntity(ApplicationDto applicationDto){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(applicationDto, MswApplication.class);
    }
}
