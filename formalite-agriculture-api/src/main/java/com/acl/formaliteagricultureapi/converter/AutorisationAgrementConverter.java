package com.acl.formaliteagricultureapi.converter;

import com.acl.formaliteagricultureapi.dto.agrement.AgrementBySocieteReturnDto;
import com.acl.formaliteagricultureapi.dto.agrement.AutorisationAgrementDto;
import com.acl.formaliteagricultureapi.models.AutorisationAgrement;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

/**
 * @author kol on 11/18/24
 * @project formalite-agriculture-api
 */
@Service
public class AutorisationAgrementConverter {

    public AutorisationAgrement convertEntityToDto(AutorisationAgrementDto agrement) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(agrement, AutorisationAgrement.class);
    }
}
