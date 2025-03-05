package com.acl.mswauth.servicesImpl.groupe;

import com.acl.mswauth.converter.GroupeConverter;
import com.acl.mswauth.dto.ClientGroupeDto;
import com.acl.mswauth.dto.GroupeDtoUpdate;
import com.acl.mswauth.model.MswClient;
import com.acl.mswauth.model.MswGroupe;
import com.acl.mswauth.model.MswGroupeClient;
import com.acl.mswauth.model.MswStructure;
import com.acl.mswauth.playload.ApiResponseModel;
import com.acl.mswauth.repositories.ClientRepository;
import com.acl.mswauth.repositories.GroupeClientRepository;
import com.acl.mswauth.repositories.GroupeRepository;
import com.acl.mswauth.repositories.StructureRepository;
import com.acl.mswauth.request.GroupeClientRequest;
import com.acl.mswauth.dto.GroupeDto;
import com.acl.mswauth.service.groupe.GroupeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GroupeServiceImp implements GroupeService {

    private final Logger logger = LoggerFactory.getLogger(GroupeServiceImp.class);

    @Autowired
    private  Environment env;

    private final GroupeRepository groupeRepository;
private final GroupeConverter  groupeConverter;
private final ClientRepository clientRepository;
private final GroupeClientRepository groupeClientRepository;
private final StructureRepository structureRepository;

    public GroupeServiceImp(GroupeRepository groupeRepository, GroupeConverter groupeConverter, ClientRepository clientRepository, GroupeClientRepository groupeClientRepository, StructureRepository structureRepository) {
        this.groupeRepository = groupeRepository;
        this.groupeConverter = groupeConverter;
        this.clientRepository = clientRepository;
        this.groupeClientRepository = groupeClientRepository;
        this.structureRepository = structureRepository;
    }

    @Override
    public ResponseEntity<?> create(GroupeDto groupeDto) {
        Optional<MswGroupe> mswGroupe = groupeRepository.findByNomGroupe(groupeDto.getNomGroupe());
        if (mswGroupe.isPresent()) {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    "Le Groupe existe déja  ", groupeDto.getNomGroupe()),HttpStatus.NOT_FOUND);
        } else {
            MswGroupe groupe = groupeConverter.convertEntity(groupeDto);
            MswGroupe save = groupeRepository.save(groupe);
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.CREATED.name(),
                    "Opération réussi ",save),HttpStatus.OK);
        }

    }

    public int racine(int a, int b){
        final int count = 0;
        int result = 0;
        if (a<b){
            result = count + 1;
        }
        return result;
    }

    /**
     * 
     * @param groupeDto
     * @return
     */
    @Override
    public ResponseEntity<?> update(GroupeDtoUpdate groupeDto) {
        Optional<MswGroupe> mswGroupe = groupeRepository.findById(groupeDto.getId());
        if (mswGroupe.isPresent()) {
            mswGroupe.get().setNomGroupe(groupeDto.getNomGroupe());
            MswGroupe groupe = groupeRepository.save(mswGroupe.get());
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.CREATED.name(),
               env.getProperty("message.application.sucess")     ,groupe),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NO_CONTENT.name(),
                    env.getProperty("message.application.notfound")),HttpStatus.OK);
        }
    }


    /**
     * Ajouter un client dans un groupe
     * @param request
     * @return
     */

    @Override
    public ResponseEntity<?> addClientGroupe(GroupeClientRequest request) {

        Optional<MswClient> mswClient = clientRepository.findByCompteClient(request.getCodeClient());

        Optional<MswGroupe> mswGroupe = groupeRepository.findByNomGroupe(request.getGroupeClient());

        Optional<MswStructure> mswStructure = structureRepository.findById(request.getStructureId());

        MswGroupeClient mswGroupeClient = new MswGroupeClient();

        mswClient.ifPresent(mswGroupeClient::setMswClient);
        mswGroupe.ifPresent(mswGroupeClient::setMswGroupe);
        mswStructure.ifPresent(mswGroupeClient::setMswStructre);

        MswGroupeClient save = groupeClientRepository.save(mswGroupeClient);

        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.CREATED.name(),
                "Opération réussi ",save),HttpStatus.OK);

    }

    /**
     *
     * @param id
     * @return
     */
	@Override
	public ResponseEntity<?> getAllGroupesByClientId(Long id) {
		return new ResponseEntity<>(new ApiResponseModel(HttpStatus.CREATED.name(),
                "Opération réussie ",groupeRepository.findAllByClientId(id)),HttpStatus.OK);
	}

    /**
     *
     * @param compteClient
     * @return
     */
	@Override
	public ResponseEntity<?> getAllGroupesByClientCompte(String compteClient) {
		return new ResponseEntity<>(new ApiResponseModel(HttpStatus.CREATED.name(),
                "Opération réussie ",groupeRepository.findAllByClientCompte(compteClient)),HttpStatus.OK);
	}


    /**
     *
     * @param request
     * @return
     */
    @Override
    public ResponseEntity<?> addGroupesClient(ClientGroupeDto request) {
        Optional<MswClient> mswClient = clientRepository.findByCompteClient(request.getCompteClient());

            if (mswClient.isPresent()) {
                request.getGroupeDtos().forEach(data-> {
                    Optional<MswGroupe> groupe = groupeRepository.findByNomGroupe(data.getNomGroupe());
                    if(groupe.isPresent()) {
                        MswGroupeClient mswGroupeClient = new MswGroupeClient();
                        mswGroupeClient.setMswClient(mswClient.get());
                        mswGroupeClient.setMswGroupe(groupe.get());
                        groupeClientRepository.save(mswGroupeClient);
                    }
                });
                return new ResponseEntity<>(new ApiResponseModel(HttpStatus.CREATED.name(),
                        "Opération réussi ", request.getCompteClient() ),HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                        "Le client n'existe pas  ", mswClient),HttpStatus.OK);
            }

    }
}
