package com.acl.formaliteagricultureapi.serviceImpl.general;

import com.acl.formaliteagricultureapi.dto.produit.InsertProduitDto;
import com.acl.formaliteagricultureapi.models.*;
import com.acl.formaliteagricultureapi.playload.ApiResponseModel;
import com.acl.formaliteagricultureapi.repository.ProduitRepository;
import com.acl.formaliteagricultureapi.repository.StructureRepository;
import com.acl.formaliteagricultureapi.repository.TypeProduitRepository;
import com.acl.formaliteagricultureapi.repository.VarieteProduitRepository;
import com.acl.formaliteagricultureapi.services.general.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitServiceImpl implements ProduitService {

    private final ProduitRepository produitRepository;
    private final TypeProduitRepository typeProduitRepository;
    private final StructureRepository structureRepository;
    private final VarieteProduitRepository varieteProduitRepository;

    @Autowired
    private Environment env;


    public ProduitServiceImpl(ProduitRepository produitRepository, TypeProduitRepository typeProduitRepository, StructureRepository structureRepository, VarieteProduitRepository varieteProduitRepository) {
        this.produitRepository = produitRepository;
        this.typeProduitRepository = typeProduitRepository;
        this.structureRepository = structureRepository;
        this.varieteProduitRepository = varieteProduitRepository;
    }

    @Override
    public ResponseEntity<?> listProduit() {
        List<Produit> produit = produitRepository.findAll();
        if(produit.isEmpty()){
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.list.vide"),
                    produitRepository.findAll()), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.succes"), produitRepository.findAll()), HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> create(InsertProduitDto request) {
        if (request.getLibelle().isEmpty()){
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NO_CONTENT.name(),
                    "Produit n'existe pas"), HttpStatus.OK);
        }

        Produit pdt = new Produit();
        pdt.setLibelle(request.getLibelle());
        pdt.setDescription(request.getDescription());
        pdt.setCode(request.getCode());

        Optional<TypeProduit> tp = typeProduitRepository.findById(request.getTypeProduitId());
        tp.ifPresent(pdt::setTypeProduit);

        Optional<Structure> sp = structureRepository.findById(request.getStructureId());
        sp.ifPresent(pdt::setStructure);

        Optional<VarieteProduit> var = varieteProduitRepository.findById(request.getVarieteProduitId());
        var.ifPresent(pdt::setVarieteProduit);

        Produit saveProduit = produitRepository.save(pdt);

        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                "Opération effectuée avec succès", saveProduit), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> listProduitByRef(String ref) {

        List<Produit> produits = produitRepository.findByRef(ref);
        if(!produits.isEmpty()) {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.succes"), produits), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.list.vide"), produits), HttpStatus.OK);
        }

    }
}
