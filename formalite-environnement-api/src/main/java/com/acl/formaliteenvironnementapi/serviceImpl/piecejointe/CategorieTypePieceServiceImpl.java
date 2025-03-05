package com.acl.formaliteenvironnementapi.serviceImpl.piecejointe;


import com.acl.formaliteenvironnementapi.dto.piecejointe.CategorieTypePieceDto;
import com.acl.formaliteenvironnementapi.models.CategorieTypePiece;
import com.acl.formaliteenvironnementapi.playload.ApiResponseModel;
import com.acl.formaliteenvironnementapi.repository.CategorieTypePieceRepository;
import com.acl.formaliteenvironnementapi.services.piecejointe.CategorieTypePieceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Zansouyé on 02/09/2024
 * @project formalite-agriculture-api
 */
@Service
public class CategorieTypePieceServiceImpl implements CategorieTypePieceService {

    Logger logger= LoggerFactory.getLogger(CategorieTypePieceServiceImpl.class);

    private final CategorieTypePieceRepository categorieTypePieceRepository;

    @Autowired
    private Environment env;

    public CategorieTypePieceServiceImpl(CategorieTypePieceRepository categorieTypePieceRepository){
        this.categorieTypePieceRepository=categorieTypePieceRepository;
    }


    @Override
    public ResponseEntity<?> create(CategorieTypePieceDto categorieTypePieceDto) {
        Optional<CategorieTypePiece>optionalCategorieTypePiece= categorieTypePieceRepository.
                findByRef(categorieTypePieceDto.getRef());

        if(optionalCategorieTypePiece.isPresent()){
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.CONFLICT.name(),
                    env.getProperty("message.existe")), HttpStatus.OK);
        }else{
            CategorieTypePiece categorieTypePiece= new CategorieTypePiece(categorieTypePieceDto.
                    getLibelle(), categorieTypePieceDto.getRef());

            CategorieTypePiece saveCategorieTypePiece= categorieTypePieceRepository.save(categorieTypePiece);
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.succes"), saveCategorieTypePiece), HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> listCategories() {
        List<CategorieTypePiece> categorieTypePieces = categorieTypePieceRepository.findAll();
        if(!categorieTypePieces.isEmpty()){
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.succes"), categorieTypePieces), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.list.vide"), categorieTypePieces), HttpStatus.OK);
        }

    }

    @Override
    public ResponseEntity<?> updateCategories(CategorieTypePieceDto categorieTypePieceDto, long id) {
        CategorieTypePiece categorie = categorieTypePieceRepository.findById(id).orElseThrow();

        if (categorie == null) {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.not_found"), null), HttpStatus.NOT_FOUND);
        } else {
            categorie.setLibelle(categorieTypePieceDto.getLibelle());
            categorie.setRef(categorieTypePieceDto.getRef());

            CategorieTypePiece categoriePieceJointe = categorieTypePieceRepository.save(categorie);

            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.succes", "Categorie mis à jour avec succès"),
                    categoriePieceJointe), HttpStatus.OK);
            }
    }
}
