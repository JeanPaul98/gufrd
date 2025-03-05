package com.acl.vbs.fret.services.impls;

import com.acl.vbs.fret.converters.AppConverter;
import com.acl.vbs.fret.entities.*;
import com.acl.vbs.fret.exceptions.ChargeurNotFoundException;
import com.acl.vbs.fret.exceptions.DeclarantNotFoundException;
import com.acl.vbs.fret.exceptions.DestinataireMarchandiseNotFoundException;
import com.acl.vbs.fret.exceptions.UnauthorizedAccessException;
import com.acl.vbs.fret.repositories.*;
import com.acl.vbs.fret.requests.DmdDeclarationFretRequest;
import com.acl.vbs.fret.responses.DmdDeclarationFretResponse;
import com.acl.vbs.fret.responses.MSWUserResponse;
import com.acl.vbs.fret.services.AuthenticationService;
import com.acl.vbs.fret.services.DmdDeclarationFretService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.acl.vbs.fret.converters.AppConverter.toDmdDeclarationFretResponse;

@Service
@RequiredArgsConstructor
public class DmdDeclarationFretServiceImpl implements DmdDeclarationFretService {

    private final ChargeurRepository chargeurRepository;
    private final DeclarantRepository declarantRepository;
    private final AuthenticationService authenticationService;
    private final DmdDeclarationFretRepository dmdDeclarationFretRepository;
    private final PieceJustificativeRepository pieceJustificativeRepository;
    private final DestinataireMarchandiseRepository destinataireMarchandiseRepository;

    @Override
    public DmdDeclarationFretResponse create(DmdDeclarationFretRequest request) {
        MSWUserResponse user = authenticationService.getAuthInfo();
        DmdDeclarationFret declarationFret = new DmdDeclarationFret();

        if (!Objects.equals(user.getGroupe(), "TRANSITAIRES")) {
            throw new UnauthorizedAccessException("Accès non autorisé");
        }

        Declarant declarant = getDeclarant(user.getCompteClient());
        declarationFret.setDeclarant(declarant);

        Chargeur conducteur = getChargeur(request.getChargeur());
        declarationFret.setChargeur(conducteur);

        DestinataireMarchandise destinataireMarchandise = getDestinataireMarchandise(request.getDestinataireMarchandise());
        declarationFret.setDestinataireMarchandise(destinataireMarchandise);

        BeanUtils.copyProperties(request, declarationFret);
        declarationFret.setCreatedBy(user.getCompteClient());
        DmdDeclarationFret save = dmdDeclarationFretRepository.save(declarationFret);

        return toDmdDeclarationFretResponse(save);
    }

    @Override
    public List<DmdDeclarationFretResponse> getAllByDeclarant() {
        MSWUserResponse user = authenticationService.getAuthInfo();
        return dmdDeclarationFretRepository.findAllByDeclarantCompteClient(user.getCompteClient())
                .stream().map(AppConverter::toDmdDeclarationFretResponse).toList();
    }

    @Override
    public List<DmdDeclarationFretResponse> getAllByChargeur() {
        MSWUserResponse user = authenticationService.getAuthInfo();
        return dmdDeclarationFretRepository.findAllByChargeurCompteClient(user.getCompteClient())
                .stream().map(AppConverter::toDmdDeclarationFretResponse).toList();
    }

    @Override
    public DmdDeclarationFretResponse getById(Long idDeclarationFret) {
        DmdDeclarationFret dmdDeclarationFret = dmdDeclarationFretRepository.findById(idDeclarationFret)
                .orElseThrow(() -> new DeclarantNotFoundException("DmdDeclarationFret with ID " + idDeclarationFret + " not found"));
        List<PieceJustificative> pieceJustificatives = pieceJustificativeRepository.findAllByDeclarationFretId(idDeclarationFret);
        return toDmdDeclarationFretResponse(dmdDeclarationFret, pieceJustificatives);
    }

    private Declarant getDeclarant(String compteClient) {
        return declarantRepository.findByCompteClient(compteClient)
                .orElseThrow(() -> new DeclarantNotFoundException("Declarant with compte " + compteClient + " not found"));
    }

    private DestinataireMarchandise getDestinataireMarchandise(Long id) {
        return destinataireMarchandiseRepository.findById(id)
                .orElseThrow(() -> new DestinataireMarchandiseNotFoundException("DestinataireMarchandise with ID " + id + " not found"));
    }

    private Chargeur getChargeur(String compteClient) {
        return chargeurRepository.findByCompteClient(compteClient)
                .orElseThrow(() -> new ChargeurNotFoundException("Chargeur with compte " + compteClient + " not found"));
    }
}