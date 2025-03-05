package com.acl.formaliteagricultureapi.converter;

import com.acl.formaliteagricultureapi.dto.procesVerbal.InspecteurDto;
import com.acl.formaliteagricultureapi.dto.procesVerbal.PvPhytoSanitaireCargConteneurDto;
import com.acl.formaliteagricultureapi.models.Inspecteur;
import com.acl.formaliteagricultureapi.models.ProcesVerbal;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * @author kol on 11/09/2024
 * @project formalite-agriculture-api
 */
@Service
public class InspecteurConverter {

    public InspecteurDto convertEntityToDto(Inspecteur inspecteur) {

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(inspecteur, InspecteurDto.class);
    }
}
