package com.acl.formaliteagricultureapi.serviceImpl.piecejointe;

import com.acl.formaliteagricultureapi.dto.piecejointe.TypePieceJointeDto;
import com.acl.formaliteagricultureapi.models.CategorieTypePiece;
import com.acl.formaliteagricultureapi.models.TypePieceJointe;
import com.acl.formaliteagricultureapi.playload.ApiResponseModel;
import com.acl.formaliteagricultureapi.repository.CategorieTypePieceRepository;
import com.acl.formaliteagricultureapi.repository.TypePieceJointeRepository;
import com.acl.formaliteagricultureapi.services.piecejointe.TypePieceJointeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


/**
 * @author Zansouyé on 02/09/2024
 * @project formalite-agriculture-api
 */
@Service
public class TypePieceJointeServiceImpl implements TypePieceJointeService {

    Logger logger= LoggerFactory.getLogger(TypePieceJointeServiceImpl.class);

    private final TypePieceJointeRepository typePieceJointeRepository;

    private final CategorieTypePieceRepository categorieTypePieceRepository;

    @Autowired
    private Environment env;

    public TypePieceJointeServiceImpl(TypePieceJointeRepository typePieceJointeRepository,
                                      CategorieTypePieceRepository categorieTypePieceRepository){
        this.typePieceJointeRepository=typePieceJointeRepository;
        this.categorieTypePieceRepository=categorieTypePieceRepository;

    }

    @Override
    public ResponseEntity<?> create(TypePieceJointeDto typePieceJointeDto) {
        Optional<TypePieceJointe>optionalTypePieceJointe= typePieceJointeRepository.
                findByLibelle(typePieceJointeDto.getLibelle());
            if(optionalTypePieceJointe.isPresent()){
                return new ResponseEntity<>(new ApiResponseModel(HttpStatus.CONFLICT.name(),
                        env.getProperty("message.existe")), HttpStatus.OK);
            }else{
                TypePieceJointe typePieceJointe= new TypePieceJointe(typePieceJointeDto.getLibelle(),
                        typePieceJointeDto.getDescription(),typePieceJointeDto.isObligatoire());
                TypePieceJointe returnTypePieceJointe= typePieceJointeRepository.save(typePieceJointe);

                return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                        env.getProperty("message.succes"),returnTypePieceJointe), HttpStatus.OK);
            }
    }

    @Override
    public ResponseEntity<?> list() {
          return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                env.getProperty("message.succes"),typePieceJointeRepository.findAll()), HttpStatus.OK);
    }

//    @Override
//    public ResponseEntity<?> findTypeByCategory(String ref) {
//        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
//                env.getProperty("message.succes"),typePieceJointeRepository.findAllByCategoryPiece(ref)), HttpStatus.OK);
//    }

    @Override
    public ResponseEntity<?> deleteTypePieceJointe(long id) {
        if (!typePieceJointeRepository.existsById(id)) {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.not_found"), null), HttpStatus.NOT_FOUND);
        }
        typePieceJointeRepository.deleteById(id);
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                env.getProperty("message.succes")), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateTypePieceJointe(TypePieceJointeDto typePieceJointeDto, long id) {

        TypePieceJointe typePieceJointe = typePieceJointeRepository.findById(id).orElseThrow();
        if (typePieceJointe == null) {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.not_found"), null), HttpStatus.NOT_FOUND);
        } else {
            typePieceJointe.setLibelle(typePieceJointeDto.getLibelle());
            typePieceJointe.setDescription(typePieceJointeDto.getDescription());
            typePieceJointe.setObligatoire(typePieceJointeDto.isObligatoire());
//            CategorieTypePiece categorieTypePiece = categorieTypePieceRepository.findById(typePieceJointeDto.getIdCategorieTypePiece()).orElseThrow();
//            if (categorieTypePiece == null){
//                return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
//                        env.getProperty("message.not_found"), null), HttpStatus.NOT_FOUND);
//            }
            //typePieceJointe.setCategorieTypePiece(categorieTypePiece);
            TypePieceJointe updatedTypePieceJointe = typePieceJointeRepository.save(typePieceJointe);

            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.succes", "Type de pièce jointe mis à jour avec succès"),
                    updatedTypePieceJointe), HttpStatus.OK);
        }





    }
}
