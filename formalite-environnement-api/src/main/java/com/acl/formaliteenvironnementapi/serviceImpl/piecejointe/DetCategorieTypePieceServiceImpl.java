package com.acl.formaliteenvironnementapi.serviceImpl.piecejointe;


import com.acl.formaliteenvironnementapi.dto.piecejointe.DetCategorieTypeJointeDto;
import com.acl.formaliteenvironnementapi.dto.piecejointe.TypePieceJointeDto;
import com.acl.formaliteenvironnementapi.models.CategorieTypePiece;
import com.acl.formaliteenvironnementapi.models.DetCategorieTypePiece;
import com.acl.formaliteenvironnementapi.models.TypePieceJointe;
import com.acl.formaliteenvironnementapi.playload.ApiResponseModel;
import com.acl.formaliteenvironnementapi.repository.CategorieTypePieceRepository;
import com.acl.formaliteenvironnementapi.repository.DetCategorieTypePieceRepository;
import com.acl.formaliteenvironnementapi.repository.TypePieceJointeRepository;
import com.acl.formaliteenvironnementapi.services.piecejointe.DetCategorieTypePieceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DetCategorieTypePieceServiceImpl implements DetCategorieTypePieceService {

    private final DetCategorieTypePieceRepository detCategorieTypePieceRepository;
    private final CategorieTypePieceRepository categorieTypePieceRepository;
    private final TypePieceJointeRepository typePieceJointeRepository;

    @Autowired
    private Environment env;

    public DetCategorieTypePieceServiceImpl(DetCategorieTypePieceRepository detCategorieTypePieceRepository, CategorieTypePieceRepository categorieTypePieceRepository, TypePieceJointeRepository typePieceJointeRepository) {
        this.detCategorieTypePieceRepository = detCategorieTypePieceRepository;
        this.categorieTypePieceRepository = categorieTypePieceRepository;
        this.typePieceJointeRepository = typePieceJointeRepository;
    }

    @Override
    public ResponseEntity<?> listDetCategorieTypePiece() {
        List<DetCategorieTypePiece> result = detCategorieTypePieceRepository.findAll();
        if (!result.isEmpty()) {
            Map<Long, List<TypePieceJointeDto>> categorieTypePiecesMap = new HashMap<>();

            for (DetCategorieTypePiece det : result) {
                Long categorieId = det.getCategorieTypePiece().getId();
                TypePieceJointeDto typePieceJointeDto = new TypePieceJointeDto(
                        det.getTypePieceJointe().getLibelle(),
                        det.getTypePieceJointe().getDescription(),
                        det.getTypePieceJointe().isObligatoire()
                );
                categorieTypePiecesMap
                        .computeIfAbsent(categorieId, k -> new ArrayList<>())
                        .add(typePieceJointeDto);
            }
            List<Map<String, Object>> responseList = new ArrayList<>();
            for (Map.Entry<Long, List<TypePieceJointeDto>> entry : categorieTypePiecesMap.entrySet()) {
                Map<String, Object> categorieData = new HashMap<>();
                categorieData.put("idCategorieTypePiece", entry.getKey());
                categorieData.put("typePieceJointes", entry.getValue());
                responseList.add(categorieData);
            }

            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.succes"),responseList), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.list.vide"), null), HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> createDetCategorieTypePiece(DetCategorieTypeJointeDto detCategorieTypeJointeDto) {

        Optional<CategorieTypePiece> categorieOptional = categorieTypePieceRepository
                .findById(detCategorieTypeJointeDto.getIdCategorieTypePiece());

        if (categorieOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponseModel(
                    HttpStatus.NOT_FOUND.name(),
                    "Catégorie non trouvée"));
        }

        CategorieTypePiece categorieTypePiece = categorieOptional.get();
        List<TypePieceJointeDto> typePieceJointes = new ArrayList<>();

        for (TypePieceJointeDto typePieceDto : detCategorieTypeJointeDto.getTypePieceJointes()) {
            Optional<TypePieceJointe> typePieceOptional = typePieceJointeRepository
                    .findByLibelle(typePieceDto.getLibelle());

            if (typePieceOptional.isPresent()) {
                TypePieceJointe typePieceJointe = typePieceOptional.get();

                DetCategorieTypePiece detCat = new DetCategorieTypePiece();
                detCat.setCategorieTypePiece(categorieTypePiece);
                detCat.setTypePieceJointe(typePieceJointe);
                detCategorieTypePieceRepository.save(detCat);
                TypePieceJointeDto responseTypePieceDto = new TypePieceJointeDto(
                        typePieceJointe.getLibelle(),
                        typePieceJointe.getDescription(),
                        typePieceJointe.isObligatoire()
                );

                typePieceJointes.add(responseTypePieceDto);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponseModel(
                        HttpStatus.NOT_FOUND.name(),
                        "Type de pièce jointe non trouvé"));
            }
        }
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.CREATED.name(),
                env.getProperty("message.succes"),typePieceJointes), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<?> getDetCategorieTypePieceByRef(String ref) {
        List<DetCategorieTypePiece> result = detCategorieTypePieceRepository.findByCategorieTypePieceRef(ref);

        if (!result.isEmpty()) {
            Map<Long, List<TypePieceJointeDto>> categorieTypePiecesMap = new HashMap<>();

            for (DetCategorieTypePiece det : result) {
                Long categorieId = det.getCategorieTypePiece().getId();
                TypePieceJointeDto typePieceJointeDto = new TypePieceJointeDto(
                        det.getTypePieceJointe().getLibelle(),
                        det.getTypePieceJointe().getDescription(),
                        det.getTypePieceJointe().isObligatoire()
                );
                categorieTypePiecesMap
                        .computeIfAbsent(categorieId, k -> new ArrayList<>())
                        .add(typePieceJointeDto);
            }
            List<Map<String, Object>> responseList = new ArrayList<>();
            for (Map.Entry<Long, List<TypePieceJointeDto>> entry : categorieTypePiecesMap.entrySet()) {
                Map<String, Object> categorieData = new HashMap<>();
                categorieData.put("idCategorieTypePiece", entry.getKey());
                categorieData.put("typePieceJointes", entry.getValue());
                responseList.add(categorieData);
            }
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.CREATED.name(),
                    "Relations créées avec succès", responseList), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.list.vide"), null), HttpStatus.OK);
        }
    }

}
