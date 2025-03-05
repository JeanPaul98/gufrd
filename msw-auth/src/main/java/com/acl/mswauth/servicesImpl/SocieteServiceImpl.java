package com.acl.mswauth.servicesImpl;


import com.acl.mswauth.dto.societe.SocieteDto;
import com.acl.mswauth.model.Societe;
import com.acl.mswauth.playload.ApiResponseModel;
import com.acl.mswauth.repositories.SocieteRepository;
import com.acl.mswauth.service.SocieteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SocieteServiceImpl implements SocieteService {

    private final SocieteRepository societeRepository;

    @Autowired
    private Environment env;

    public SocieteServiceImpl(SocieteRepository societeRepository) {
        this.societeRepository = societeRepository;
    }

    @Override
    public ResponseEntity<?> listSociete(){
        List<Societe> societe = societeRepository.findAll();
        if(societe.isEmpty()){
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.list.vide"),
                    societe), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.application.sucess"),
                    societe),
                    HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> addSociete(SocieteDto societeDto) {
        Optional<Societe> optionalSociete = societeRepository.findByNif(societeDto.getNif());
        if (optionalSociete.isPresent()) {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),"Le numero d'identification fiscal de cette societe existe"), HttpStatus.NOT_FOUND);
        }else{
            Societe societe = new Societe();
            societe.setNif(societeDto.getNif());
            societe.setRaisonSociale(societeDto.getRaisonSociale());
            societe.setFormeJuridique(societeDto.getFormeJuridique());
            societe.setNumRccm(societeDto.getNumRccm());
            societe.setContact(societeDto.getContact());
            societe.setAdresse(societeDto.getAdresse());
            societe.setContact(societeDto.getContact());
            societe.setEmail(societeDto.getEmail());
            Societe societeSave = societeRepository.save(societe);
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.update.success"), societeSave), HttpStatus.OK);
        }
    }


    @Override
    public ResponseEntity<?> updateSociete(SocieteDto societeDto, long id) {
        Optional<Societe> optionalSociete = societeRepository.findById(id);
        if (optionalSociete.isEmpty()) {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.societe.notFound")), HttpStatus.NOT_FOUND);
        }
        Societe societe = optionalSociete.get();
        societe.setNif(societeDto.getNif());
        societe.setRaisonSociale(societeDto.getRaisonSociale());
        societe.setNumRccm(societeDto.getNumRccm());
        societe.setFormeJuridique(societeDto.getFormeJuridique());
        societe.setContact(societeDto.getContact());
        societe.setAdresse(societeDto.getAdresse());
        societe.setContact(societeDto.getContact());
        societe.setEmail(societeDto.getEmail());

        Societe updatedSociete = societeRepository.save(societe);
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                env.getProperty("message.update.success"), updatedSociete), HttpStatus.OK);
    }
}
