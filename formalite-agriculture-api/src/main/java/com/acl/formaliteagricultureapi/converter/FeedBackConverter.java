package com.acl.formaliteagricultureapi.converter;

import com.acl.formaliteagricultureapi.dto.feedback.FeedBackDto;
import com.acl.formaliteagricultureapi.dto.procesVerbal.InspecteurDto;
import com.acl.formaliteagricultureapi.models.FeedBackPublic;
import com.acl.formaliteagricultureapi.models.Inspecteur;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * @author kol on 10/6/24
 * @project formalite-agriculture-api
 */
@Service
public class FeedBackConverter {

    public FeedBackDto convertEntityToDto(FeedBackPublic request) {

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(request, FeedBackDto.class);
    }

    public FeedBackPublic convertDToEntity(FeedBackDto request) {

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(request, FeedBackPublic.class);
    }
}
