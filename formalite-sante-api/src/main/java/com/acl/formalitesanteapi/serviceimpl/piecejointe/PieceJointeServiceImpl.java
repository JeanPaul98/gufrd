package com.acl.formalitesanteapi.serviceimpl.piecejointe;


import com.acl.formalitesanteapi.dto.piecejointe.FormaliteFileDto;
import com.acl.formalitesanteapi.dto.piecejointe.FormalitePieceJointeDto;
import com.acl.formalitesanteapi.dto.piecejointe.PieceJointeDto;
import com.acl.formalitesanteapi.models.Formalite;
import com.acl.formalitesanteapi.models.PieceJointe;
import com.acl.formalitesanteapi.models.TypePieceJointe;
import com.acl.formalitesanteapi.playload.ApiResponseModel;
import com.acl.formalitesanteapi.repository.FormaliteRepository;
import com.acl.formalitesanteapi.repository.PieceJointeRepository;
import com.acl.formalitesanteapi.repository.TypePieceJointeRepository;
import com.acl.formalitesanteapi.services.file.StorageService;
import com.acl.formalitesanteapi.services.piecejointe.PieceJointeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author jean paul 24/09/2024
 * @project formalite-sante-api
 */
@Service
public class PieceJointeServiceImpl implements PieceJointeService {

    private Logger logger= LoggerFactory.getLogger(PieceJointeServiceImpl.class);
    private final PieceJointeRepository pieceJointeRepository;

    private final FormaliteRepository formaliteRepository;

    private final StorageService storageService;

    private final TypePieceJointeRepository typePieceJointeRepository;

    @Autowired
    private Environment env;

    public PieceJointeServiceImpl(PieceJointeRepository pieceJointeRepository, FormaliteRepository formaliteRepository, StorageService storageService, TypePieceJointeRepository typePieceJointeRepository) {
        this.pieceJointeRepository = pieceJointeRepository;
        this.formaliteRepository = formaliteRepository;
        this.storageService = storageService;
        this.typePieceJointeRepository = typePieceJointeRepository;
    }


    @Transactional
    @Override
    public ResponseEntity<?> ajoutFile(FormalitePieceJointeDto request) throws JsonProcessingException {


        ObjectMapper objectMapper = new ObjectMapper();
        List<FormaliteFileDto> formaliteFileDtos= objectMapper.readValue(request.getFilenameTypePieceJointe(),new TypeReference<>(){});
        logger.info("formaliteFileDto "+formaliteFileDtos);
        request.setFormaliteFileDtoList(formaliteFileDtos);
        int i=0;
        int j=0;
        if(request.getFile()!=null){
            for(MultipartFile data : request.getFile()){
                i++;
                logger.info(" rentrer data "+i);
                logger.info("data name "+data.getOriginalFilename());
                for(FormaliteFileDto dataForm: request.getFormaliteFileDtoList()){
                    j++;
                    logger.info(" rentrer dataForm "+j);
                    logger.info(" dataForm 2 "+dataForm.getFilename());
                    if(data.getOriginalFilename().equalsIgnoreCase(dataForm.getFilename())){
                        logger.info("rentrer dans egalite de nom de fichier");
                        Optional<TypePieceJointe> optionalTypePieceJointe= typePieceJointeRepository.
                                findById(dataForm.getIdTypePieceJointe());

                        if(optionalTypePieceJointe.isPresent()){
                            Path file = storageService.load(data.getOriginalFilename());
                            String url  = "http://102.164.230.196:4300/"+data.getOriginalFilename();
                            logger.info("optionalTypePieceJointe.isPresent()");
                            Optional<Formalite> optionalFormalite= formaliteRepository.findById(request
                                    .getIdFormalite());
                            if(optionalFormalite.isPresent()){
                                logger.info("optionalFormalite.isPresent()");
                                //Enregistrement de la piece jointe
                                PieceJointe pieceJointe= new PieceJointe(optionalTypePieceJointe.get()
                                        .getLibelle(), data.getOriginalFilename(),
                                       url,optionalFormalite.get(),
                                        optionalTypePieceJointe.get());

                                pieceJointeRepository.save(pieceJointe);
                                // Enregistrement du fichier dans un dossier
                                storageService.store(data);
                            }else{

                                return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                                        env.getProperty("message.not.found.entity")),HttpStatus.OK);
                            }
                        }else {
                            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                                    env.getProperty("message.not.found.entity")), HttpStatus.OK);

                        }
                    }
                }
            }
        }else{
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.not.found.entity")),HttpStatus.OK);
        }


        logger.info("fin processus ");
        return  new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                env.getProperty("message.succes")), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<?> listePieceJointe(Long request) {
        Optional<Formalite> formalite = formaliteRepository.findById(request);
        if(formalite.isPresent()){

            List<PieceJointe> pieceJointes = pieceJointeRepository.findByFormalite(formalite.get().getId());
            List<PieceJointeDto> pieceJointeDtos = new ArrayList<>();

            pieceJointes.forEach(d-> {
                PieceJointeDto pieceJointeDto = new PieceJointeDto();
                pieceJointeDto.setNomOriginePieceJointe(d.getNomOriginePieceJointe());
                pieceJointeDto.setUrlPj(d.getUrlPj());
                pieceJointeDto.setTypePieceJointe(d.getTypePieceJointe().getLibelle());
                pieceJointeDtos.add(pieceJointeDto);
            });

            return  new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.succes"), pieceJointeDtos), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.not.found.entity")),HttpStatus.OK);
        }
    }

}
