package com.acl.formaliteagricultureapi.converter;

import com.acl.formaliteagricultureapi.dto.feedback.FeedBackDto;
import com.acl.formaliteagricultureapi.dto.feedback.paiement.FeedbackPaiementDto;
import com.acl.formaliteagricultureapi.models.FeedBackPublic;
import com.acl.formaliteagricultureapi.models.FeedbackSrvPaiement;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * @author kol on 10/7/24
 * @project formalite-agriculture-api
 */
@Service
public class FeedbackPaiementConverter {

    public FeedbackPaiementDto convertEntityToDto(FeedbackSrvPaiement request) {

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(request, FeedbackPaiementDto.class);
    }

    public FeedbackSrvPaiement convertDToEntity(FeedbackPaiementDto request) {

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(request, FeedbackSrvPaiement.class);
    }
}
