package com.acl.formaliteagricultureapi.serviceImpl.veterinaire.cuirspeaux;

import com.acl.formaliteagricultureapi.dto.exports.veterinaire.certificat.cuirepeaux.AutorisationExpCuirePeauxDto;
import com.acl.formaliteagricultureapi.dto.exports.veterinaire.certificat.cuirepeaux.AutorisationExpCuirsPeauxListDto;
import com.acl.formaliteagricultureapi.dto.piecejointe.PieceJointeFormaliteDto;
import com.acl.formaliteagricultureapi.dto.produit.DetCertificatProduitDto;
import com.acl.formaliteagricultureapi.models.*;
import com.acl.formaliteagricultureapi.models.enumeration.Chaine;
import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import com.acl.formaliteagricultureapi.models.enumeration.StatutPaiement;
import com.acl.formaliteagricultureapi.playload.ApiResponseModel;
import com.acl.formaliteagricultureapi.repository.*;
import com.acl.formaliteagricultureapi.services.utils.UtilServices;
import com.acl.formaliteagricultureapi.services.veterinaire.cuirepeaux.AutorisationExpCuirPeauxCdService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author kol on 04/09/2024
 * @project formalite-agriculture-api
 */
@Service
public class AutorisationExpCuirPeauxCdServiceImpl implements AutorisationExpCuirPeauxCdService {

   private final FormaliteRepository formaliteRepository;

   private final CertificatRepository certificatRepository;

   private final StructureRepository structureRepository;

    private final AutorisationAgrementRepository agrementRepository;

    private final DetCertificatProduitRepository detCertificatProduitRepository;

   private final TypeCertificatRepository typeCertificatRepository;

   private final ProduitRepository produitRepository;

   private final PieceJointeRepository pieceJointeRepository;

   private final TypePieceJointeRepository typePieceJointeRepository;

    @Autowired
    private UtilServices utilServices;

   @Autowired
   private Environment env;

    public AutorisationExpCuirPeauxCdServiceImpl(FormaliteRepository formaliteRepository, CertificatRepository certificatRepository, StructureRepository structureRepository, AutorisationAgrementRepository agrementRepository, DetCertificatProduitRepository detCertificatProduitRepository, TypeCertificatRepository typeCertificatRepository, ProduitRepository produitRepository, PieceJointeRepository pieceJointeRepository, TypePieceJointeRepository typePieceJointeRepository) {
        this.formaliteRepository = formaliteRepository;
        this.certificatRepository = certificatRepository;
        this.structureRepository = structureRepository;
        this.agrementRepository = agrementRepository;
        this.detCertificatProduitRepository = detCertificatProduitRepository;
        this.typeCertificatRepository = typeCertificatRepository;
        this.produitRepository = produitRepository;
        this.pieceJointeRepository = pieceJointeRepository;
        this.typePieceJointeRepository = typePieceJointeRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<?> create(AutorisationExpCuirePeauxDto request) {

        for (DetCertificatProduitDto detProduit : request.getDetCertificatProduitDtos()) {
            Optional<Produit> optionalProduit = produitRepository.findByCode(
                    detProduit.getProduit().getCode());
            if (!optionalProduit.isPresent()) {
                return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                        env.getProperty("message.notfound")), HttpStatus.OK);
            }
        }
        //Save certificat
        Certificat certificat  = saveCertificat(request.getExpediteur(), request.getDestinataire(),
                request.getMoyenTransport(), request.getIdentification(),
                request.getLieuExpedition(), request.getTraitement());

        //Save detailCertificatProduit
        saveDetCertificatProduit(request.getDetCertificatProduitDtos(), certificat);

        return saveFormalite(certificat,request.getNumeroAgrement(), request.getCompteClient(),
                request.getPieceJointeFormaliteDtos(),request.getNif(),request.getNumeroDossier());
    }

    private Certificat saveCertificat(String expediteur, String destinataire, String moyenTransport ,
                                      String identification, String lieuExpedition, String traitement) {

    Certificat certificat = new Certificat(expediteur, destinataire, moyenTransport, identification,lieuExpedition
                                        ,traitement);
        Optional<TypeCertificat> optionalTypeCertificat = typeCertificatRepository.
                findByRef(env.getProperty("message.type.certificat.ref.veterinaire.cuirspeaux"));
        if (!optionalTypeCertificat.isPresent()) {
            throw new IllegalArgumentException(env.getProperty("message.exception.reference"));
        }
        certificat.setTypeCertificat(optionalTypeCertificat.get());
        certificat.setCreatedAt(new Date());
        return certificatRepository.save(certificat);
    }

    public void saveDetCertificatProduit(List<DetCertificatProduitDto> detProduits,
                                           Certificat certificat) {
        detProduits.forEach(data -> {
            Optional<Produit> produit = produitRepository.findByCode(data.getProduit().getCode());
            DetCertificatProduit detCertificatProduit = new DetCertificatProduit(produit.get(),
                    certificat, data.getQuantite(), data.getPoidsTotal(), data.getConditionnement(),
                    data.getRace(),data.getNatureProduit(), data.getEspece(), data.getNombre(),
                    data.getFournisseur(), data.getConteneur(), data.getOrigine(), data.getFournisseur(),
                    data.getNombreColis());
            detCertificatProduitRepository.save(detCertificatProduit);
        });

    }

    public ResponseEntity<?> saveFormalite(Certificat certificat,String numeroAgrement, String compteClient,
                                           List<PieceJointeFormaliteDto> pieceJointeFormaliteDtos,String nif,String numeroDossier) {

        Optional<AutorisationAgrement> agrement = agrementRepository.findByNumero(numeroAgrement);

        if(agrement.isPresent()){
            Formalite formalite = new Formalite(compteClient,agrement.get(),Etat.NON_SOUMIS,StatutPaiement.IMPAYER,
                    Chaine.Export);
            formalite.setNumGenere(utilServices.generateNumDemande(env.getProperty("message.code.genere.numero.certificat")));
//            formalite.setNumGenere("AUTCERT" + System.currentTimeMillis());
            formalite.setCertificat(certificat);
            Optional<Structure> optionalStructure = structureRepository.findByCode(env.getProperty("message.structure.code"));
            if (!optionalStructure.isPresent()) {
                throw new IllegalArgumentException(env.getProperty("message.exception.reference"));
            }
            formalite.setStructure(optionalStructure.get());
            formalite.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            formalite.setDateDmd(new Timestamp(System.currentTimeMillis()));
            formalite.setNif(nif);
            formalite.setNumeroDossier(numeroDossier);
            Formalite saveFormalite = formaliteRepository.save(formalite);
            savePiecejointe(pieceJointeFormaliteDtos, saveFormalite);

            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.succes"),saveFormalite.getId()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.not.found.entity"),numeroAgrement), HttpStatus.OK);
        }


    }

    @Override
    public ResponseEntity<?> validerDemande(AutorisationExpCuirsPeauxListDto request) throws Exception {
        return null;
    }

    public void savePiecejointe(List<PieceJointeFormaliteDto> detPiecesJoinets, Formalite formalite) {
        detPiecesJoinets.forEach(data -> {
            PieceJointe piecejointe = new PieceJointe();
            Optional<TypePieceJointe> typePieceJointe = typePieceJointeRepository.findById(data.getIdTypePieceJointe());
            if(typePieceJointe.isPresent()) {
                piecejointe.setTypePieceJointe(typePieceJointe.get());
                piecejointe.setFormalite(formalite);
                piecejointe.setUrlPj(data.getUrlPj());
                piecejointe.setNomOriginePieceJointe(typePieceJointe.get().getLibelle());
                piecejointe.setNomGenerePieceJointe(data.getNomOriginePieceJointe());
                pieceJointeRepository.save(piecejointe);
            } else  {
                throw new IllegalArgumentException(env.getProperty("message.exception.reference"));
            }

        });
    }
}
