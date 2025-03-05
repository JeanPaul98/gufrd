package com.acl.formaliteagricultureapi.serviceImpl.general;

import com.acl.formaliteagricultureapi.dto.societe.TypeSocieteDto;
import com.acl.formaliteagricultureapi.models.TypeSociete;
import com.acl.formaliteagricultureapi.playload.ApiResponseModel;
import com.acl.formaliteagricultureapi.repository.TypeSocieteRepository;
import com.acl.formaliteagricultureapi.services.general.TypeSocieteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TypeSocieteServiceImpl implements TypeSocieteService {

    private final TypeSocieteRepository typeSocieteRepository;

    @Autowired
    private Environment env;

    public TypeSocieteServiceImpl(TypeSocieteRepository typeSocieteRepository) {
        this.typeSocieteRepository = typeSocieteRepository;
    }

    @Override
    public ResponseEntity<?> listTypeSociete() {
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                env.getProperty("message.succes"), typeSocieteRepository.findAll()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> addTypeSociete(TypeSocieteDto typeSocieteDto) {
        Optional<TypeSociete> searchType = typeSocieteRepository.findByReference(typeSocieteDto.getRef());
        if (searchType.isPresent()) {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    "Type de societe déja créer"), HttpStatus.OK);
        }else{
            TypeSociete typeSociete = new TypeSociete();
            typeSociete.setRef(typeSocieteDto.getRef());
            typeSociete.setLibelle(typeSocieteDto.getLibelle());
            TypeSociete typeSocieteSaved = typeSocieteRepository.save(typeSociete);
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.succes"), typeSocieteSaved), HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> updateTypeSociete(TypeSocieteDto typeSocieteDto,long id) {
        Optional<TypeSociete> searchType = typeSocieteRepository.findById(id);
        if (searchType.isEmpty()) {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.societe.notFound")), HttpStatus.NOT_FOUND);
        }
        TypeSociete typeSociete = searchType.get();
        typeSociete.setRef(typeSocieteDto.getRef());
        typeSociete.setLibelle(typeSocieteDto.getLibelle());
        TypeSociete typeSocieteSaved = typeSocieteRepository.save(typeSociete);

        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                env.getProperty("message.update.success"), typeSocieteSaved), HttpStatus.OK);    }
}
