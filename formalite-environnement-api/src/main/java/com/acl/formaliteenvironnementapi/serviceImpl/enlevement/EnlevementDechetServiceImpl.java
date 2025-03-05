package com.acl.formaliteenvironnementapi.serviceImpl.enlevement;

import com.acl.formaliteenvironnementapi.dto.enlevement.EnlevementDechetDto;
import com.acl.formaliteenvironnementapi.models.Autorisation;
import com.acl.formaliteenvironnementapi.models.Formalite;
import com.acl.formaliteenvironnementapi.models.Structure;
import com.acl.formaliteenvironnementapi.models.TypeAutorisation;
import com.acl.formaliteenvironnementapi.models.enumeration.Chaine;
import com.acl.formaliteenvironnementapi.models.enumeration.Etat;
import com.acl.formaliteenvironnementapi.playload.ApiResponseModel;
import com.acl.formaliteenvironnementapi.repository.AutorisationRepository;
import com.acl.formaliteenvironnementapi.repository.FormaliteRepository;
import com.acl.formaliteenvironnementapi.repository.StructureRepository;
import com.acl.formaliteenvironnementapi.repository.TypeAutorisationRepository;
import com.acl.formaliteenvironnementapi.services.enlevement.EnlevementDechetService;
import com.acl.formaliteenvironnementapi.services.utils.UtilServices;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

/**
 * @author kol on 12/09/2024
 * @project formalite-environnement-api
 */
@Service
public class EnlevementDechetServiceImpl implements EnlevementDechetService {

    private final FormaliteRepository formaliteRepository;

    private final StructureRepository structureRepository;

    private final AutorisationRepository autorisationRepository;

    private final TypeAutorisationRepository typeAutorisationRepository;

    @Autowired
    private UtilServices utilServices;

    @Autowired
    private Environment env;

    public EnlevementDechetServiceImpl(FormaliteRepository formaliteRepository, StructureRepository structureRepository, AutorisationRepository autorisationRepository, TypeAutorisationRepository typeAutorisationRepository) {
        this.formaliteRepository = formaliteRepository;
        this.structureRepository = structureRepository;
        this.autorisationRepository = autorisationRepository;
        this.typeAutorisationRepository = typeAutorisationRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<?> create(EnlevementDechetDto request) {
        Autorisation autorisation = saveAutorisation();

        return saveFormalite(request.getAtp(), request.getCompteClient(), request.getImo(), request.getNomNavire(),
                request.getAffreteur(), autorisation);
    }

    private Autorisation saveAutorisation() {

        Autorisation autorisation = new Autorisation();

        Optional<TypeAutorisation> typeAutorisation = typeAutorisationRepository.
                findByRef(env.getProperty("message.type.autorisation.ref.enlevement"));

        if (typeAutorisation.isPresent()) {
            autorisation.setTypeAutorisation(typeAutorisation.get());
        }
        else  {
            throw new IllegalArgumentException(env.getProperty("message.exception.reference"));
        }

        autorisation.setCreatedAt(new Date());
        return    autorisationRepository.save(autorisation);
    }


    private ResponseEntity<?> saveFormalite(String atp, String compteClient, String imo, String nomNavire,
                                            String affreteur,
                                            Autorisation autorisation) {

        Formalite formalite = new Formalite(atp,nomNavire,imo,affreteur, compteClient);
        formalite.setChaine(Chaine.Import);

        formalite.setNumGenere(utilServices.generateNumDemande(env.getProperty("message.numero.generer.env.enlevement")));

        formalite.setAutorisation(autorisation);

        Optional<Structure> optionalStructure = structureRepository.findByCode(env.getProperty("message.structure.code.env"));
        if (!optionalStructure.isPresent()) {
            throw new IllegalArgumentException(env.getProperty("message.exception.reference"));
        }

        formalite.setStructure(optionalStructure.get());
        formalite.setEtat(Etat.NON_SOUMIS);
        formalite.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        formalite.setDateDmd(new Timestamp(System.currentTimeMillis()));
        Formalite saveFormalite = formaliteRepository.save(formalite);

        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                env.getProperty("message.succes"), saveFormalite.getId()), HttpStatus.OK);

    }
}
