package com.acl.vbs.services.impls;


import com.acl.vbs.entities.*;
import com.acl.vbs.exceptions.*;
import com.acl.vbs.projections.BonEntreeCamionProjection;
import com.acl.vbs.projections.VaProjection;
import com.acl.vbs.repositories.*;
import com.acl.vbs.requests.BonEntreeCamionRequest;
import com.acl.vbs.requests.BonEntreeCamionUpdatePoidsRequest;
import com.acl.vbs.responses.*;
import com.acl.vbs.services.AuthenticationService;
import com.acl.vbs.services.BonEntreeCamionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.acl.vbs.utils.AppUtils.generateRandomString;

@Slf4j
@Service
@RequiredArgsConstructor
public class BonEntreeCamionServiceImpl implements BonEntreeCamionService {

//    SimpleDateFormat sfDateHeure = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    // Déclaration des repositories nécessaires pour interagir avec la base de données
    private final BonEntreeCamionRepository repository;
    private final CamionRepository camionRepository;
    //    private final AireStockageRepository aireStockageRepository;
    private final SiteRepository siteRepository;
    private final SensTraficRepository sensTraficRepository;
    private final LigneMseRepository ligneMseRepository;
    private final BecLigneMseRepository becLigneMseRepository;
    private final AuthenticationService authenticationService;
    private final TransitaireRepository transitaireRepository;

    @Override
    public List<DeclarationResponse> listCamionsAttendus(String sensTrafic) {// Récupération des informations de l'utilisateur connecté
        MSWUserResponse user = authenticationService.getAuthInfo();

        return repository.listCamionsAttendus(user.getCompteClient(), sensTrafic)
                .stream().map(this::toDeclarationResponse).toList();
    }

    @Override
    public Page<DeclarationResponse> listCamionsAttendus(String sensTrafic, int page, int size) {
        // Récupération des informations de l'utilisateur connecté
        MSWUserResponse user = authenticationService.getAuthInfo();

        // Calcul des limites pour la pagination
        int start = page * size + 1;
        int end = (page + 1) * size;

        List<DeclarationResponse> bonEntreeCamionResponses = repository.listCamionsAttendus(user.getCompteClient(), sensTrafic, start, end)
                .stream().map(this::toDeclarationResponse).toList();

        long totalElements = repository.countPesageBon(user.getCompteClient(), sensTrafic);

        return new PageImpl<>(bonEntreeCamionResponses, PageRequest.of(page, size), totalElements);
    }

    @Override
    public List<BonEntreeCamionResponse> listDeclarationCamion() {
        // Récupération des informations de l'utilisateur connecté
        MSWUserResponse user = authenticationService.getAuthInfo();

        return repository.findAllByTransitaireClientCompteClient(user.getCompteClient())
                .stream().map(this::toBonEntreeCamionResponse).collect(Collectors.toList());
    }

    @Override
    public Page<BonEntreeCamionResponse> listDeclarationCamion(int size, int page) {
        // Récupération des informations de l'utilisateur connecté
        MSWUserResponse user = authenticationService.getAuthInfo();

        // Calcul des limites pour la pagination
        int start = page * size + 1;
        int end = (page + 1) * size;

        // Récupération des données paginées à partir du repository
        List<BonEntreeCamionResponse> bonEntreeCamionResponses = repository.findAllByTransitaireClientCompteClient(user.getCompteClient(), start, end)
                .stream().map(this::toBonEntreeCamionResponse).collect(Collectors.toList());

        long totalElements = repository.countByCompteClient(user.getCompteClient());

        // Mapping des données paginées vers BonEntreeCamionResponse tout en conservant les métadonnées
        return new PageImpl<>(bonEntreeCamionResponses, PageRequest.of(page, size), totalElements);
    }

    // Méthode principale pour créer une déclaration de bon d'entrée camion
    @Override
    public BonEntreeCamionResponse creationDeclaration(BonEntreeCamionRequest request) {
        // Création d'un objet vide pour la déclaration
        BonEntreeCamion declaration = new BonEntreeCamion();
        MSWUserResponse user = authenticationService.getAuthInfo();

        if (!Objects.equals(user.getGroupe(), "TRANSITAIRES")) {
            throw new UnauthorizedAccessException("Accès non autorisé");
        }

        // Récupération des entités nécessaires à partir des identifiants fournis dans la requête
        Site site = getSite(request.getIdSitePesage());
        Camion camion = getCamion(request.getImmatriculation());
        SensTrafic sensTrafic = getSensTrafic(request.getCodeSensTrafic());
        Transitaire transitaire = getTransitaire(user.getCompteClient());
//        AireStockage aireStockage = getAireStockage(request.getLieuChargement());

        // Validation : vérifier si le sens de trafic est valide
        if (sensTrafic == null) {
            throw new IllegalArgumentException("Sens de trafic introuvable pour le code fourni");
        }

        // Gestion des cas "Export" et "Import" via un switch
        BonEntreeCamion saved = switch (sensTrafic.getCodeSensTrafic()) {
            case "EXP" -> handleExportDeclaration(declaration, request, site, camion, sensTrafic, transitaire, user);
            case "IMP" -> handleImportDeclaration(declaration, request, site, camion, sensTrafic, transitaire, user);
            default ->
                    throw new UnsupportedOperationException("Code de sens de trafic non pris en charge : " + sensTrafic.getCodeSensTrafic());
        };

        // Conversion de l'entité sauvegardée en objet de réponse
        return toBonEntreeCamionResponse(saved);
    }

    @Override
    public BonEntreeCamionResponse updatePoidsVide(String numBonEntreeCamion, BonEntreeCamionUpdatePoidsRequest request) {
        MSWUserResponse user = authenticationService.getAuthInfo();
        if (!Objects.equals(user.getGroupe(), "CONCESSIONNAIRE")) {
            throw new UnauthorizedAccessException("Accès non autorisé");
        }
        BonEntreeCamion declaration = repository.findByNumBonEntreeCamion(numBonEntreeCamion)
                .orElseThrow(() -> new BonEntreeCamionNotFoundException("Bon d'entrée camion introuvable"));
        if (request.getPoidsVide() != null) {
            declaration.setPoidsVide(request.getPoidsVide());
        }
        if (request.getPoidsCharge() != null) {
            declaration.setPoidsCharge(request.getPoidsCharge());
        }
        if (request.getObservationPesage() != null && !request.getObservationPesage().isBlank()) {
            declaration.setObservationPesage(request.getObservationPesage());
        }
        declaration.setUpdateByUser(user.getFullname());
        BonEntreeCamion saved = repository.save(declaration);
        return toBonEntreeCamionResponse(saved);
    }

    @Override
    public DetailBonEntreeCamionResponse listDetailBonEntreeCamion(String numBonEntre) {
        DetailBonEntreeCamionResponse response = new DetailBonEntreeCamionResponse();
        BonEntreeCamion bec = repository.findByNumBonEntreeCamion(numBonEntre)
                .orElseThrow(() -> new BonEntreeCamionNotFoundException("Bon d'entrée camion introuvable"));

        List<LigneMarchandiseResponse> ligneMseListe = becLigneMseRepository.findAllByBonEntreeCamion(bec).stream()
                .map(BecLigneMse::getLigneMse).map(this::toLigneMarchandiseResponse).toList();
        BeanUtils.copyProperties(bec, response);
        response.setLigneMseList(ligneMseListe);
        return response;
    }

    @Override
    public BonEntreeCamionRedevenceResponse getRedevance(String numBonEntreeCamion) {
        BonEntreeCamionRedevenceResponse map = new BonEntreeCamionRedevenceResponse();
        BonEntreeCamion bec = repository.findByNumBonEntreeCamion(numBonEntreeCamion)
                .orElseThrow(() -> new BonEntreeCamionNotFoundException("Bon d'entrée camion introuvable"));

        BigDecimal poidsMin = BigDecimal.ZERO; // Utilisation de BigDecimal pour la cohérence des calculs

        // Vérification de la validité des données
        if (bec.getSitePesage() != null && bec.getPoidsCharge() != null && bec.getPoidsVide() != null) {
            // Calcul du poids net
            BigDecimal result = bec.getPoidsCharge().subtract(bec.getPoidsVide());

            // Comparaison du résultat avec 25 et calcul de poidsMin
            BigDecimal seuil = new BigDecimal(25);

            if (result.compareTo(seuil) > 0) {
                // Si le poids net est supérieur à 25, calcul de la redevance
                BigDecimal redevance = new BigDecimal(bec.getSitePesage().getRedevance());
                poidsMin = result.multiply(redevance);
            } else {
                // Si le poids net est inférieur ou égal à 25, fixer poidsMin à 5650
                poidsMin = new BigDecimal(5650);
            }
        }

        map.setRedevance(poidsMin);
        // Retourner la redevance encapsulée dans un objet de réponse
        return map;
    }

    private DetailBonEntreeCamionResponse toLigneMseResponse(VaProjection ligneMseProjection) {
        DetailBonEntreeCamionResponse response = new DetailBonEntreeCamionResponse();
        BeanUtils.copyProperties(ligneMseProjection, response);
        return response;
    }

    private Transitaire getTransitaire(String compteClient) {
        return transitaireRepository.findByClientCompteClient(compteClient)
                .orElseThrow(() -> new TransitaireNotFoundException("Transitaire introuvable"));
    }

    // Méthode pour gérer une déclaration d'importation
    private BonEntreeCamion handleImportDeclaration(BonEntreeCamion declaration, BonEntreeCamionRequest request, Site site, Camion camion, SensTrafic sensTrafic, Transitaire transitaire, MSWUserResponse user) {

        // Validation : s'assurer que la liste des lignes MSE n'est pas vide si requise
        if (request.getTransporteMse() && request.getIdLigneMseList().isEmpty()) {
            throw new BecLigneMseNotEmptyException("Liste de bec ligne mse vide");
        }

        // Sauvegarde de la déclaration dans la base de données
        BonEntreeCamion saved = saveDeclaration(declaration, request, site, camion, sensTrafic, transitaire, user);

        // Si des lignes MSE doivent être transportées, les associer au bon d'entrée
        if (request.getTransporteMse()) {
            List<LigneMse> ligneMseList = request.getIdLigneMseList().stream().map(this::getLigneMse).toList();
            saveBecLigneMseEntries(saved, ligneMseList);
        }

        return saved;
    }

    // Méthode pour gérer une déclaration d'exportation
    private BonEntreeCamion handleExportDeclaration(BonEntreeCamion declaration, BonEntreeCamionRequest request, Site site, Camion camion, SensTrafic sensTrafic, Transitaire transitaire, MSWUserResponse user) {
        return saveDeclaration(declaration, request, site, camion, sensTrafic, transitaire, user);
    }

    // Méthode pour associer et sauvegarder des lignes MSE à un bon d'entrée camion
    private void saveBecLigneMseEntries(BonEntreeCamion bonEntreeCamion, List<LigneMse> ligneMseList) {
        ligneMseList.forEach(ligneMse -> {
            BecLigneMsePK becLigneMsePK = new BecLigneMsePK(bonEntreeCamion.getNumBonEntreeCamion(), ligneMse.getIdLigneMse());
            BecLigneMse becLigneMse = new BecLigneMse();
            becLigneMse.setBecLigneMsePK(becLigneMsePK);
            becLigneMse.setBonEntreeCamion(bonEntreeCamion);
            becLigneMse.setLigneMse(ligneMse);
            becLigneMseRepository.save(becLigneMse);
        });
    }

    // Méthode pour sauvegarder une déclaration de bon d'entrée camion
    private BonEntreeCamion saveDeclaration(BonEntreeCamion declaration, BonEntreeCamionRequest request, Site site, Camion camion, SensTrafic sensTrafic, Transitaire transitaire, MSWUserResponse user) {
        declaration.setNumBonEntreeCamion(LocalDateTime.now().getYear() + "-" + LocalDateTime.now().getMonthValue() + "-" + generateRandomString(6));
        declaration.setSitePesage(site);
        declaration.setImmatriculation(camion);
        declaration.setTransitaire(transitaire);
        declaration.setCodeSensTrafic(sensTrafic);
        declaration.setCodePays(camion.getCodePays());
        declaration.setChargeur(request.getChargeur());
        declaration.setRemorque(request.getRemorque());
        declaration.setPoidsVide(camion.getPoidsVide());
        declaration.setCreatedByUser(user.getFullname());
        declaration.setCompteClient(user.getCompteClient());
        declaration.setNomConducteur(camion.getNomConducteur());
        declaration.setTransporteMse(request.getTransporteMse());
        declaration.setBonChargement(request.getBonChargement());
        declaration.setCodeTransporteur(camion.getCodeTransporteur());
        declaration.setPoidsChargeEnleve(request.getPoidsChargeEnleve());
        return repository.save(declaration);
    }

    // Méthodes utilitaires pour récupérer les entités à partir de leurs identifiants
    private LigneMse getLigneMse(Long id) {
        return ligneMseRepository.findByIdLigneMse(id)
                .orElseThrow(() -> new LigneMseNotFoundException("Ligne MSE introuvable"));
    }

    private Camion getCamion(String immatriculation) {
        return camionRepository.findByImmatriculation(immatriculation)
                .orElseThrow(() -> new CamionNotFoundException("Camion introuvable"));
    }

    private Site getSite(Long idSite) {
        return siteRepository.findByIdSite(idSite)
                .orElseThrow(() -> new SiteNotFoundException("Site introuvable"));
    }

    private SensTrafic getSensTrafic(String codeSensTrafic) {
        return sensTraficRepository.findByCodeSensTrafic(codeSensTrafic)
                .orElseThrow(() -> new SensTraficNotFoundException("Sens de trafic introuvable"));
    }

    // Méthode pour convertir une entité en réponse destinée à l'API
    private BonEntreeCamionResponse toBonEntreeCamionResponse(BonEntreeCamion bonEntreeCamion) {
        BonEntreeCamionResponse response = new BonEntreeCamionResponse();
        BeanUtils.copyProperties(bonEntreeCamion, response);
        return response;
    }

    private DeclarationResponse toDeclarationResponse(BonEntreeCamionProjection projection) {
        DeclarationResponse response = new DeclarationResponse();
        BeanUtils.copyProperties(projection, response);
        return response;
    }

    private LigneMarchandiseResponse toLigneMarchandiseResponse(LigneMse ligneMse) {
        LigneMarchandiseResponse response = new LigneMarchandiseResponse();
        BeanUtils.copyProperties(ligneMse, response);
        return response;
    }
}

//{
//  "immatriculation": "TG-5057-AV",
//  "codeSensTrafic": "IMP",
//  "remorque": "string",
//  "idSitePesage": 59,
//  "transporteMse": true,
//  "idLigneMseList": [
//    77679, 77678
//  ]
//}