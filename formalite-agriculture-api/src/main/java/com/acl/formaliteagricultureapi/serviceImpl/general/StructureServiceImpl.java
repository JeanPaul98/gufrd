package com.acl.formaliteagricultureapi.serviceImpl.general;

import com.acl.formaliteagricultureapi.dto.produit.StructureDto;
import com.acl.formaliteagricultureapi.models.Produit;
import com.acl.formaliteagricultureapi.models.Structure;
import com.acl.formaliteagricultureapi.playload.ApiResponseModel;
import com.acl.formaliteagricultureapi.repository.ProduitRepository;
import com.acl.formaliteagricultureapi.repository.StructureRepository;
import com.acl.formaliteagricultureapi.services.general.StructureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StructureServiceImpl implements StructureService {

    private final StructureRepository structureRepository;
    private final ProduitRepository produitRepository;

    public StructureServiceImpl(StructureRepository structureRepository, ProduitRepository produitRepository) {
        this.structureRepository = structureRepository;
        this.produitRepository = produitRepository;
    }

    @Override
    public ResponseEntity<?> listStructure() {
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                "Liste structure", structureRepository.findAll()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> createStructure(StructureDto request) {

        Structure structure = new Structure();
        structure.setCode(request.getCode());
        structure.setLibelle(request.getLibelle());
        structure.setTypeStructure(request.getTypeStructure());
        Optional<Produit> pd = produitRepository.findById(request.getProduitId());
        pd.ifPresent(structure::setProduit);

        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.CREATED.name(),
                "Structure creer", structureRepository.save(structure)), HttpStatus.OK);
    }
}
