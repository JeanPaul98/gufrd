package com.acl.formaliteagricultureapi.serviceImpl.veterinaire.cire;

import com.acl.formaliteagricultureapi.converter.PiecejointeConverter;
import com.acl.formaliteagricultureapi.converter.ProduitConverter;
import com.acl.formaliteagricultureapi.dto.exports.veterinaire.certificat.cire.AutorisationExpCireListDto;
import com.acl.formaliteagricultureapi.dto.piecejointe.PieceJointeDto;
import com.acl.formaliteagricultureapi.dto.produit.DetCertificatProduitDto;
import com.acl.formaliteagricultureapi.dto.produit.ProduitDto;
import com.acl.formaliteagricultureapi.dto.statistique.StatistiqueDemandeDto;
import com.acl.formaliteagricultureapi.models.AutorisationAgrement;
import com.acl.formaliteagricultureapi.models.DetCertificatProduit;
import com.acl.formaliteagricultureapi.models.DmdAutorisationAgrement;
import com.acl.formaliteagricultureapi.models.PieceJointe;
import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import com.acl.formaliteagricultureapi.models.enumeration.StatutPaiement;
import com.acl.formaliteagricultureapi.playload.ApiResponseModel;
import com.acl.formaliteagricultureapi.repository.*;
import com.acl.formaliteagricultureapi.requete.AutorisationCertificatInterface;
import com.acl.formaliteagricultureapi.services.veterinaire.cire.AutorisationExpCireManyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author kol on 10/09/2024
 * @project formalite-agriculture-api
 */
@Service
public class AutorisationExpCireManyServiceImpl implements AutorisationExpCireManyService {

    private final FormaliteRepository formaliteRepository;

    private final DetCertificatProduitRepository detCertificatProduitRepository;

    private final DemandeAutorisationAgrementRepository demandeAutorisationAgrementRepository;

    private final AutorisationAgrementRepository autorisationAgrementRepository;

    private final PieceJointeRepository pieceJointeRepository;

    private final ProduitConverter produitConverter;


    Logger logger = LoggerFactory.getLogger(AutorisationExpCireManyServiceImpl.class);

    @Autowired
    PiecejointeConverter piecejointeConverter;

    @Autowired
    private Environment env;

    public AutorisationExpCireManyServiceImpl(FormaliteRepository formaliteRepository, DetCertificatProduitRepository detCertificatProduitRepository, DemandeAutorisationAgrementRepository demandeAutorisationAgrementRepository, AutorisationAgrementRepository autorisationAgrementRepository, PieceJointeRepository pieceJointeRepository, ProduitConverter produitConverter) {
        this.formaliteRepository = formaliteRepository;
        this.detCertificatProduitRepository = detCertificatProduitRepository;
        this.demandeAutorisationAgrementRepository = demandeAutorisationAgrementRepository;
        this.autorisationAgrementRepository = autorisationAgrementRepository;
        this.pieceJointeRepository = pieceJointeRepository;
        this.produitConverter = produitConverter;
    }

    @Override
    public ResponseEntity<?> listeAutorisation(Etat etat, String ref) {

        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.succes"),
                getListDemande(etat,ref)), HttpStatus.OK);
    }


    private List<AutorisationExpCireListDto> getListDemande(Etat etat, String ref) {
        //Liste enlevement Non soumise
        List<AutorisationCertificatInterface> cerficatClientList =
                formaliteRepository.findCerficatClientList(etat.getLabel(), ref);

        List<AutorisationExpCireListDto> autorisationEnlevementClientListDtoList = new ArrayList<>();

        //Parcours la liste De détail Produit
        //Crée le constructeur de la liste
        cerficatClientList.forEach(data -> {
            List<DetCertificatProduitDto> detCertificatProduitDtos = new ArrayList<>();

            logger.info("Id certificat , {}", data.getIdCertificat());
            List<DetCertificatProduit> detCertificatProduitList = detCertificatProduitRepository.
                    findByCertificatId(data.getIdCertificat());

            detCertificatProduitList.forEach(produitDetail -> {
                ProduitDto produitDto = produitConverter.convertDtoToEntity(produitDetail.getProduit());
                DetCertificatProduitDto dtoProduitDetail = new DetCertificatProduitDto(produitDto,
                        produitDetail.getQuantite(), produitDetail.getNombre(),
                        produitDetail.getNatureProduit(),produitDetail.getNombreColis(), produitDetail.getConditionnement(),
                        produitDetail.getPaysOrigineProvenance(), produitDetail.getRace(),
                        produitDetail.getEspece(),produitDetail.getFournisseur(), produitDetail.getSexe(),
                        produitDetail.getConteneur(), produitDetail.getPoidsTotal());
                detCertificatProduitDtos.add(dtoProduitDetail);

            });

            AutorisationExpCireListDto clientListDto = new AutorisationExpCireListDto(
                    data.getIdFormalite(),
                    data.getDestinataire(),
                    data.getExpediteur(),
                    data.getCompteClient(),
                    data.getEtat(),
                    data.getChaine(),
                    data.getIdCertificat(),
                    data.getNumGenerer(),
                    data.getDateDemande(),
                    data.getDateAccepte(),
                    data.getLieuExpedition(),
                    data.getDateSoumise(),
                    data.getDateTraitement(),
                    detCertificatProduitDtos,
                    data.getMoyenTransport(),
                    data.getLieuDechargement(),
                    data.getAdresseDestinataire(),
                    data.getAdresseExpediteur(),
                    data.getLieuExpedition()
            );

            if(data.getIdAgrement()!=null) {
                Optional<AutorisationAgrement> agrement = autorisationAgrementRepository.findById(data.getIdAgrement());
                clientListDto.setNumeroAgrement(agrement.get().getNumero());
            }



            clientListDto.setDateRejet(data.getDateRejet());
            clientListDto.setMotifRejet(data.getMotifRejet());
            clientListDto.setNumeroAgrementTransit(data.getNumeroAgrementTransit());

            List<PieceJointe> pieceJointeList = pieceJointeRepository.findByFormalite(data.getIdFormalite());


            List<PieceJointeDto> pieceJointeDtoList = new ArrayList<>();
            pieceJointeList.forEach(pj-> {
                PieceJointeDto pieceJointeDto = piecejointeConverter.convertDtoToEntity(pj);
                pieceJointeDto.setTypePieceJointe(pj.getTypePieceJointe().getLibelle());
                pieceJointeDto.setNomOriginePieceJointe(pj.getNomOriginePieceJointe());
                pieceJointeDtoList.add(pieceJointeDto);
            });

            if(clientListDto.getMontantRedevance()>0) {
                clientListDto.setStatutPaiement(StatutPaiement.PAYE.name());
            }else {
                clientListDto.setStatutPaiement(StatutPaiement.RIEN_A_PAYER.name());
            }
            clientListDto.setMontantRedevance(data.getMontantRedevance());
            clientListDto.setPieceJointeList(pieceJointeDtoList);
            clientListDto.setNif(data.getNif());
            clientListDto.setNumeroDossier(data.getNumeroDossier());
            autorisationEnlevementClientListDtoList.add(clientListDto);

        });

        return autorisationEnlevementClientListDtoList;
    }

    @Override
    public ResponseEntity<?> listStatDemande() {

        List<AutorisationExpCireListDto> nonsoumis = getListDemande(Etat.NON_SOUMIS, env.getProperty("message.type.certificat.ref.veterinaire.cire"));
        List<AutorisationExpCireListDto> soumis = getListDemande(Etat.SOUMIS, env.getProperty("message.type.certificat.ref.veterinaire.cire"));
        List<AutorisationExpCireListDto> traiter = getListDemande(Etat.TRAITER, env.getProperty("message.type.certificat.ref.veterinaire.cire"));
        List<AutorisationExpCireListDto> accepter = getListDemande(Etat.ACCEPTER, env.getProperty("message.type.certificat.ref.veterinaire.cire"));
        List<AutorisationExpCireListDto> rejeter = getListDemande(Etat.REJETER, env.getProperty("message.type.certificat.ref.veterinaire.cire"));

        StatistiqueDemandeDto statistiqueDemandeDto = new StatistiqueDemandeDto (
                nonsoumis.size(),soumis.size(), traiter.size(),
                accepter.size(),rejeter.size());

        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.succes"),
                statistiqueDemandeDto), HttpStatus.OK);
    }



}
