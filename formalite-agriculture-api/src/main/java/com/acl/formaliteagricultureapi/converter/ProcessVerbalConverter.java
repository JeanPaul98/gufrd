package com.acl.formaliteagricultureapi.converter;

import com.acl.formaliteagricultureapi.dto.procesVerbal.PvPhytoSanitaireCargConteneurDto;
import com.acl.formaliteagricultureapi.dto.procesVerbal.navire.PvPhytoSanitaireNavireDto;
import com.acl.formaliteagricultureapi.dto.procesVerbal.navire.PvPhytoSanitaireNavireListDto;
import com.acl.formaliteagricultureapi.models.ProcesVerbal;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

/**
 * @author kol on 10/09/2024
 * @project formalite-agriculture-api
 */
@Service
public class ProcessVerbalConverter {

    public PvPhytoSanitaireNavireDto convertDtoNavireToEntity(ProcesVerbal procesVerbal) {

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(procesVerbal, PvPhytoSanitaireNavireDto.class);
    }

    public PvPhytoSanitaireNavireListDto convertDtoNavireListToEntity(ProcesVerbal procesVerbal) {

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(procesVerbal, PvPhytoSanitaireNavireListDto.class);
    }

    public PvPhytoSanitaireCargConteneurDto convertDtoCargaisonToEntity(ProcesVerbal procesVerbal) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(procesVerbal, PvPhytoSanitaireCargConteneurDto.class);
    }
}
