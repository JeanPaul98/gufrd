package com.acl.formaliteagricultureapi.serviceImpl.veterinaire.animauxvivant;

import com.acl.formaliteagricultureapi.converter.PiecejointeConverter;
import com.acl.formaliteagricultureapi.converter.ProduitConverter;
import com.acl.formaliteagricultureapi.dto.exports.veterinaire.certificat.animauxvivant.AutorisationExpAnimauxVivantListDto;
import com.acl.formaliteagricultureapi.dto.piecejointe.PieceJointeDto;
import com.acl.formaliteagricultureapi.dto.produit.DetCertificatProduitDto;
import com.acl.formaliteagricultureapi.dto.produit.ProduitDto;
import com.acl.formaliteagricultureapi.dto.statistique.StatistiqueDemandeDto;
import com.acl.formaliteagricultureapi.models.AutorisationAgrement;
import com.acl.formaliteagricultureapi.models.DetCertificatProduit;
import com.acl.formaliteagricultureapi.models.PieceJointe;
import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import com.acl.formaliteagricultureapi.models.enumeration.StatutPaiement;
import com.acl.formaliteagricultureapi.playload.ApiResponseModel;
import com.acl.formaliteagricultureapi.repository.*;
import com.acl.formaliteagricultureapi.requete.AutorisationCertificatInterface;
import com.acl.formaliteagricultureapi.services.veterinaire.animauxvivant.AutorisationExpAnimauxVivantManyService;
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
public class AutorisationExpAnimauxManyServiceImp implements AutorisationExpAnimauxVivantManyService {


    private final Logger logger= LoggerFactory.getLogger(AutorisationExpAnimauxManyServiceImp.class);

    private final FormaliteRepository formaliteRepository;

    private final AutorisationAgrementRepository autorisationAgrementRepository;


    private final DetCertificatProduitRepository detCertificatProduitRepository;

    private final ProduitConverter produitConverter;

    private final PieceJointeRepository pieceJointeRepository;


    @Autowired
    private Environment env;

    @Autowired
    private PiecejointeConverter piecejointeConverter;

    public AutorisationExpAnimauxManyServiceImp(FormaliteRepository formaliteRepository, AutorisationAgrementRepository autorisationAgrementRepository,
                                                DetCertificatProduitRepository detCertificatProduitRepository, ProduitConverter produitConverter, ProduitRepository produitRepository, PieceJointeRepository pieceJointeRepository) {
        this.formaliteRepository = formaliteRepository;
        this.autorisationAgrementRepository = autorisationAgrementRepository;
        this.detCertificatProduitRepository = detCertificatProduitRepository;
        this.produitConverter = produitConverter;
        this.pieceJointeRepository = pieceJointeRepository;
    }

    private List<AutorisationExpAnimauxVivantListDto> getListDemande(Etat etat, String ref) {
        //Liste enlevement Non soumise
        List<AutorisationCertificatInterface> autorisationCertificatInterfaces =
                formaliteRepository.findCerficatClientList(etat.getLabel(), ref);


        List<AutorisationExpAnimauxVivantListDto> certificatDtoList = new ArrayList<>();
        //Parcours la liste De détail Produit
        //Crée le constructeur de la liste
        autorisationCertificatInterfaces.forEach(data -> {
            List<DetCertificatProduitDto> detCertificatProduits = new ArrayList<>();


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

            AutorisationExpAnimauxVivantListDto clientListDto = new AutorisationExpAnimauxVivantListDto(data.getIdFormalite(),
                    data.getIdCertificat(), data.getCompteClient(), data.getExpediteur(),
                    data.getDestinataire(), data.getIdentification(),
                    data.getChaine(), data.getDateDemande()
                    , data.getTypeReference(), data.getNumGenerer(), data.getDateRejet(),
                    data.getDateAccepte(), data.getDateTraitement(), data.getDateSoumise(),
                    data.getMoyenTransport(), data.getTraitement(),
                    detCertificatProduits,data.getAdresseExpediteur(), data.getPaysExpediteur(),data.getPaysOrigine(),
                    data.getLieuOrigine(),data.getPaysDestination(), data.getLieuChargement(),
                    data.getPosteFrontalier(),data.getDateDepart());
            clientListDto.setEtat(data.getEtat());

            if(data.getIdAgrement()!=null) {
                Optional<AutorisationAgrement> agrement = autorisationAgrementRepository.findById(data.getIdAgrement());
                clientListDto.setNumeroAgrement(agrement.get().getNumero());
            }

            if(clientListDto.getMontantRedevance()>0) {
                clientListDto.setStatutPaiement(StatutPaiement.PAYE.name());
            }else {
                clientListDto.setStatutPaiement(StatutPaiement.RIEN_A_PAYER.name());
            }

            clientListDto.setDateRejet(data.getDateRejet());
            clientListDto.setMotifRejet(data.getMotifRejet());
            clientListDto.setMontantRedevance(data.getMontantRedevance());
            clientListDto.setCompteClient(data.getCompteClient());

            List<PieceJointe> pieceJointeList = pieceJointeRepository.findByFormalite(data.getIdFormalite());
            List<PieceJointeDto> pieceJointeDtoList = new ArrayList<>();
            pieceJointeList.forEach(pj-> {
                PieceJointeDto pieceJointeDto = piecejointeConverter.convertDtoToEntity(pj);
                pieceJointeDto.setTypePieceJointe(pj.getTypePieceJointe().getLibelle());
                pieceJointeDto.setNomOriginePieceJointe(pj.getNomOriginePieceJointe());
                pieceJointeDtoList.add(pieceJointeDto);
            });
            clientListDto.setNif(data.getNif());
            clientListDto.setNumeroDossier(data.getNumeroDossier());
            clientListDto.setPieceJointeDtoList(pieceJointeDtoList);

            certificatDtoList.add(clientListDto);
        });
        return certificatDtoList;
    }
    @Override
    public ResponseEntity<?> listAutorisation(Etat etat, String ref) {

        if(!getListDemande(etat,ref).isEmpty()) {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.succes"),
                    getListDemande(etat,ref)), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.list.vide"),
                    getListDemande(etat,ref)), HttpStatus.OK);
        }


    }

    @Override
    public ResponseEntity<?> listStatDemande() {
        List<AutorisationExpAnimauxVivantListDto> nonsoumis = getListDemande(Etat.NON_SOUMIS, env.getProperty("message.type.certificat.ref.veterinaire.animauxvivant"));
        List<AutorisationExpAnimauxVivantListDto> soumis = getListDemande(Etat.SOUMIS, env.getProperty("message.type.certificat.ref.veterinaire.animauxvivant"));
        List<AutorisationExpAnimauxVivantListDto> traiter = getListDemande(Etat.TRAITER, env.getProperty("message.type.certificat.ref.veterinaire.animauxvivant"));
        List<AutorisationExpAnimauxVivantListDto> accepter = getListDemande(Etat.ACCEPTER, env.getProperty("message.type.certificat.ref.veterinaire.animauxvivant"));
        List<AutorisationExpAnimauxVivantListDto> rejeter = getListDemande(Etat.REJETER, env.getProperty("message.type.certificat.ref.veterinaire.animauxvivant"));
        StatistiqueDemandeDto statistiqueDemandeDto = new StatistiqueDemandeDto(nonsoumis.size(),soumis.size(), traiter.size(),
                accepter.size(),rejeter.size());
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.succes"),
                statistiqueDemandeDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> listAutorisationByCompte(Etat etat, String ref, String compte) {
        return null;
    }
}
