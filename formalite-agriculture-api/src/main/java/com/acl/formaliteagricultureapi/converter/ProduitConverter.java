package com.acl.formaliteagricultureapi.converter;

import com.acl.formaliteagricultureapi.dto.produit.ProduitDto;
import com.acl.formaliteagricultureapi.models.Produit;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * @author kol on 22/08/2024
 * @project formalite-agriculture-api
 */
@Service
public class ProduitConverter {


    /**
     * Dto vers Entity
     * @param dto
     * @return
     */
  /*  public Produit convertEntity(ProduitDto dto) {

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Produit.class);
    }*/

    /**
     * Entity vers Dto
     * @param produit
     * @return
     */
    public ProduitDto convertDtoToEntity(Produit produit) {

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(produit, ProduitDto.class);
    }
}
