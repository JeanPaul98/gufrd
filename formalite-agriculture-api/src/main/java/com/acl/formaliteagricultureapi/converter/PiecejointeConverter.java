package com.acl.formaliteagricultureapi.converter;

import com.acl.formaliteagricultureapi.dto.piecejointe.PieceJointeDto;
import com.acl.formaliteagricultureapi.dto.produit.ProduitDto;
import com.acl.formaliteagricultureapi.models.PieceJointe;
import com.acl.formaliteagricultureapi.models.Produit;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * @author kol on 10/4/24
 * @project formalite-agriculture-api
 */
@Service
public class PiecejointeConverter {

    /**
     * Entity vers Dto
     * @param pieceJointe
     * @return
     */
    public PieceJointeDto convertDtoToEntity(PieceJointe pieceJointe) {

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(pieceJointe, PieceJointeDto.class);
    }
}
