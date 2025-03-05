package com.acl.vbs.fret.services.impls;

import com.acl.vbs.fret.converters.AppConverter;
import com.acl.vbs.fret.entities.Conducteur;
import com.acl.vbs.fret.repositories.ConducteurRepository;
import com.acl.vbs.fret.requests.ConducteurRequest;
import com.acl.vbs.fret.responses.ConducteurResponse;
import com.acl.vbs.fret.responses.MSWUserResponse;
import com.acl.vbs.fret.services.AuthenticationService;
import com.acl.vbs.fret.services.ConducteurService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.acl.vbs.fret.converters.AppConverter.toConducteurResponse;

@Service
public class ConducteurServiceImpl implements ConducteurService {

    private final AuthenticationService authenticationService;
    private final ConducteurRepository conducteurRepository;

    public ConducteurServiceImpl(AuthenticationService authenticationService, ConducteurRepository conducteurRepository) {
        this.authenticationService = authenticationService;
        this.conducteurRepository = conducteurRepository;
    }

    @Override
    public ConducteurResponse create(ConducteurRequest request) {
        MSWUserResponse user = authenticationService.getAuthInfo();
        Conducteur conducteur = new Conducteur();
        conducteur.setNom(request.getNom());
        conducteur.setPrenoms(request.getPrenoms());
        conducteur.setNumeroPermis(request.getNumeroPermis());
        conducteur.setNumeroTelephone(request.getNumeroTelephone());
        conducteur.setLieuEtablissementPermis(request.getLieuEtablissementPermis());
        conducteur.setDateEtablissementPermis(request.getDateEtablissementPermis());
        conducteur.setAssociationAffiliation(request.getAssociationAffiliation());
        conducteur.setNationalite(request.getNationalite());
        conducteur.setDateNaissance(request.getDateNaissance());
        conducteur.setGenre(request.getGenre());
        conducteur.setCreatedBy(user.getFullname());
        Conducteur save = conducteurRepository.save(conducteur);
        return toConducteurResponse(save);
    }

    @Override
    public List<ConducteurResponse> list() {
        return conducteurRepository.findAll().stream().map(AppConverter::toConducteurResponse).toList();
    }
}
