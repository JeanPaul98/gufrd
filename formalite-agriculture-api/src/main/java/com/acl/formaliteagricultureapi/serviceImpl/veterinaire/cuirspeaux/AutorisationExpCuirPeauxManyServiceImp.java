package com.acl.formaliteagricultureapi.serviceImpl.veterinaire.cuirspeaux;

import com.acl.formaliteagricultureapi.converter.PiecejointeConverter;
import com.acl.formaliteagricultureapi.converter.ProduitConverter;
import com.acl.formaliteagricultureapi.dto.exports.veterinaire.certificat.cuirepeaux.AutorisationExpCuirsPeauxListDto;
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
import com.acl.formaliteagricultureapi.serviceImpl.autorisation.enlevement.AutorisationEnlevementClientManyServiceImpl;
import com.acl.formaliteagricultureapi.services.veterinaire.cuirepeaux.AutorisationExpCuirPeauxManyService;
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
 * @author kol on 04/09/2024
 * @project formalite-agriculture-api
 */
@Service
public class AutorisationExpCuirPeauxManyServiceImp implements AutorisationExpCuirPeauxManyService {

    private final Logger logger= LoggerFactory.getLogger(AutorisationEnlevementClientManyServiceImpl.class);

    private final FormaliteRepository formaliteRepository;

    private final DetCertificatProduitRepository detCertificatProduitRepository;

    private final DemandeAutorisationAgrementRepository demandeAutorisationAgrementRepository;

    private final AutorisationAgrementRepository autorisationAgrementRepository;

    private final ProduitConverter produitConverter;

    private final PieceJointeRepository pieceJointeRepository;

    @Autowired
    PiecejointeConverter piecejointeConverter;

    @Autowired
    private Environment env;

    private final ProduitRepository produitRepository;

    public AutorisationExpCuirPeauxManyServiceImp(FormaliteRepository formaliteRepository,
                                                  DetCertificatProduitRepository detCertificatProduitRepository, DemandeAutorisationAgrementRepository demandeAutorisationAgrementRepository, AutorisationAgrementRepository autorisationAgrementRepository, ProduitConverter produitConverter, PieceJointeRepository pieceJointeRepository,
                                                  ProduitRepository produitRepository) {
        this.formaliteRepository = formaliteRepository;
        this.detCertificatProduitRepository = detCertificatProduitRepository;
        this.demandeAutorisationAgrementRepository = demandeAutorisationAgrementRepository;
        this.autorisationAgrementRepository = autorisationAgrementRepository;
        this.produitConverter = produitConverter;
        this.pieceJointeRepository = pieceJointeRepository;
        this.produitRepository = produitRepository;
    }

    @Override
    public ResponseEntity<?> listAutorisation(Etat etat, String ref) {

        if (!getListDemande(etat, ref).isEmpty()) {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.succes"),
                    getListDemande(etat,ref)), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.list.vide"),
                    getListDemande(etat,ref)), HttpStatus.OK);
        }

    }


    private List<AutorisationExpCuirsPeauxListDto> getListDemande(Etat etat, String ref) {
        //Liste enlevement Non soumise
        List<AutorisationCertificatInterface> autorisationCertificatInterfaces =
                formaliteRepository.findCerficatClientList(etat.getLabel(), ref);
        List<DetCertificatProduitDto> detCertificatProduits = new ArrayList<>();
        List<AutorisationExpCuirsPeauxListDto> certificatDtoList = new ArrayList<>();
        //Parcours la liste De détail Produit
        //Crée le constructeur de la liste
        autorisationCertificatInterfaces.forEach(data -> {

            List<DetCertificatProduit> detAutorisationProduits = detCertificatProduitRepository.
                    findByCertificatId(data.getIdCertificat());

            detAutorisationProduits.forEach(produitDetail -> {
                ProduitDto produitDto = produitConverter.convertDtoToEntity(produitDetail.getProduit());
                DetCertificatProduitDto dtoProduitDetail = new DetCertificatProduitDto(produitDto,
                        produitDetail.getQuantite(), produitDetail.getNombre(),
                        produitDetail.getNatureProduit(),produitDetail.getNombreColis(), produitDetail.getConditionnement(),
                        produitDetail.getPaysOrigineProvenance(), produitDetail.getRace(),
                        produitDetail.getEspece(),produitDetail.getFournisseur(), produitDetail.getSexe(),
                        produitDetail.getConteneur(), produitDetail.getPoidsTotal());
                detCertificatProduits.add(dtoProduitDetail);
            });

            AutorisationExpCuirsPeauxListDto clientListDto = new AutorisationExpCuirsPeauxListDto(data.getIdFormalite(),
                    data.getIdCertificat(), data.getCompteClient(), data.getExpediteur(),
                    data.getDestinataire(), data.getIdentification(),
                    data.getChaine(), data.getDateDemande(),data.getTypeReference(),
                    data.getNumGenerer(), data.getDateRejet(),data.getDateAccepte(),
                    data.getDateTraitement(),data.getDateSoumise(),data.getMoyenTransport(),
                    data.getTraitement(),detCertificatProduits,
                    data.getMontantRedevance(), data.getLieuExpedition(),
                    data.getEtat());

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
            clientListDto.setNif(data.getNif());
            clientListDto.setNumeroDossier(data.getNumeroDossier());
            clientListDto.setPieceJointeList(pieceJointeDtoList);

            certificatDtoList.add(clientListDto);
        });

        return certificatDtoList;
    }



    @Override
    public ResponseEntity<?> listStatDemande() {
        List<AutorisationExpCuirsPeauxListDto> nonsoumis = getListDemande(Etat.NON_SOUMIS, env.getProperty("message.type.certificat.ref.veterinaire.cuirspeaux"));
        List<AutorisationExpCuirsPeauxListDto> soumis = getListDemande(Etat.SOUMIS, env.getProperty("message.type.certificat.ref.veterinaire.cuirspeaux"));
        List<AutorisationExpCuirsPeauxListDto> traiter = getListDemande(Etat.TRAITER, env.getProperty("message.type.certificat.ref.veterinaire.cuirspeaux"));
        List<AutorisationExpCuirsPeauxListDto> accepter = getListDemande(Etat.ACCEPTER, env.getProperty("message.type.certificat.ref.veterinaire.cuirspeaux"));
        List<AutorisationExpCuirsPeauxListDto> rejeter = getListDemande(Etat.REJETER, env.getProperty("message.type.certificat.ref.veterinaire.cuirspeaux"));
        StatistiqueDemandeDto statistiqueDemandeDto = new StatistiqueDemandeDto(nonsoumis.size(),soumis.size(), traiter.size(),
                accepter.size(),rejeter.size());
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.succes"),
                statistiqueDemandeDto), HttpStatus.OK);
    }
}
