package com.acl.formaliteagricultureapi.converter;

import com.acl.formaliteagricultureapi.dto.agrement.DemandeAgrementListDto;
import com.acl.formaliteagricultureapi.dto.agrement.DemandeAutorisationAgrementDto;
import com.acl.formaliteagricultureapi.dto.agrement.AgrementBySocieteReturnDto;

import com.acl.formaliteagricultureapi.models.AutorisationAgrement;
import com.acl.formaliteagricultureapi.models.DmdAutorisationAgrement;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

/**
 * @author kol on 22/09/2024
 * @project formalite-agriculture-api
 */
@Service
public class DemandeAutorisationAgrementConverter {

    /**
     *
     * @param agrement
     * @return
     */
    public AgrementBySocieteReturnDto convertEntityToDto(AutorisationAgrement agrement) {

        ModelMapper modelMapper = new ModelMapper();
       modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(agrement, AgrementBySocieteReturnDto.class);
    }

    /**
     *
     * @param agrementDto
     * @return
     */
    public AutorisationAgrement convertEntity(DemandeAutorisationAgrementDto agrementDto) {

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(agrementDto, AutorisationAgrement.class);
    }

    public DemandeAgrementListDto convertEntityDto(DmdAutorisationAgrement request) {

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(request, DemandeAgrementListDto.class);
    }
}
