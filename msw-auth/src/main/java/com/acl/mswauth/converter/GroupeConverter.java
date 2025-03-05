package com.acl.mswauth.converter;

import com.acl.mswauth.model.MswGroupe;
import com.acl.mswauth.dto.GroupeDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class GroupeConverter {
    /**
     * Dto vers Entity
     * @param
     * @return
     */
    public MswGroupe convertEntity(GroupeDto groupeDto){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(groupeDto, MswGroupe.class);
    }
}
