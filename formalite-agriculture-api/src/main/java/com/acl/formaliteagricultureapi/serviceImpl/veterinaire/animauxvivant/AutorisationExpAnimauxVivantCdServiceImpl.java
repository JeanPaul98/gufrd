package com.acl.formaliteagricultureapi.serviceImpl.veterinaire.animauxvivant;

import com.acl.formaliteagricultureapi.dto.exports.veterinaire.certificat.animauxvivant.AutorisationExpAnimauxVivantDto;
import com.acl.formaliteagricultureapi.dto.exports.veterinaire.certificat.animauxvivant.AutorisationExpAnimauxVivantListDto;
import com.acl.formaliteagricultureapi.dto.piecejointe.PieceJointeFormaliteDto;
import com.acl.formaliteagricultureapi.dto.produit.DetCertificatProduitDto;
import com.acl.formaliteagricultureapi.models.*;
import com.acl.formaliteagricultureapi.models.enumeration.Chaine;
import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import com.acl.formaliteagricultureapi.playload.ApiResponseModel;
import com.acl.formaliteagricultureapi.repository.*;
import com.acl.formaliteagricultureapi.services.veterinaire.animauxvivant.AutorisationExpAnimauxVivantCdService;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
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
public class AutorisationExpAnimauxVivantCdServiceImpl implements
        AutorisationExpAnimauxVivantCdService {

    private final FormaliteRepository formaliteRepository;

    private final CertificatRepository certificatRepository;

    private final StructureRepository structureRepository;

    private final DetCertificatProduitRepository detCertificatProduitRepository;

    private final TypeCertificatRepository typeCertificatRepository;

    private final ProduitRepository produitRepository;

    private final AutorisationAgrementRepository agrementRepository;

    private final PieceJointeRepository pieceJointeRepository;

    private final TypePieceJointeRepository typePieceJointeRepository;

    @Autowired
    private Environment env;

    public AutorisationExpAnimauxVivantCdServiceImpl(FormaliteRepository formaliteRepository, CertificatRepository certificatRepository, StructureRepository structureRepository, DetCertificatProduitRepository detCertificatProduitRepository, TypeCertificatRepository typeCertificatRepository, ProduitRepository produitRepository, AutorisationAgrementRepository agrementRepository, PieceJointeRepository pieceJointeRepository, TypePieceJointeRepository typePieceJointeRepository) {
        this.formaliteRepository = formaliteRepository;
        this.certificatRepository = certificatRepository;
        this.structureRepository = structureRepository;
        this.detCertificatProduitRepository = detCertificatProduitRepository;
        this.typeCertificatRepository = typeCertificatRepository;
        this.produitRepository = produitRepository;
        this.agrementRepository = agrementRepository;
        this.pieceJointeRepository = pieceJointeRepository;
        this.typePieceJointeRepository = typePieceJointeRepository;
    }


    @Override
    @Transactional
    public ResponseEntity<?> create(AutorisationExpAnimauxVivantDto request) {

        for (DetCertificatProduitDto detProduit : request.getDetCertificatProduitDtos()) {
            Optional<Produit> optionalProduit = produitRepository.findByCode(
                    detProduit.getProduit().getCode());
            if (!optionalProduit.isPresent()) {
                return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                        env.getProperty("message.notfound")), HttpStatus.OK);
            }
        }
        //Save certificat
        Certificat certificat  = saveCertificat(request.getMoyenTransport(), request.getPaysExpediteur(), request.getExpediteur(),
                request.getPaysOrigine(),request.getLieuOrigine(), request.getPaysDestination(),
                request.getNomDestinataire(),request.getLieuChargement(), request.getDateDepart(), request.getPosteFrontalier());

        //Save detailCertificatProduit
        saveDetCertificatProduit(request.getDetCertificatProduitDtos(), certificat);

        return saveFormalite(certificat, request.getCompteClient(), request.getNumeroAgrement(), request.getNif(),request.getNumeroDossier(),
                request.getPieceJointeFormaliteDtos());

    }


    private Certificat saveCertificat(String moyenTransport, String paysExpediteur, String expediteur ,
                                      String paysOrigine, String lieuOrigine, String paysDestination,
                                      String nomDestinataire, String lieuChargement, Date dateDepart, String posteFrontalier ) {

        Certificat certificat = new Certificat(moyenTransport, paysExpediteur, expediteur, paysOrigine,
                lieuOrigine, paysDestination, nomDestinataire, lieuChargement, dateDepart, posteFrontalier
        );
        Optional<TypeCertificat> optionalTypeCertificat = typeCertificatRepository.
                findByRef(env.getProperty("message.type.certificat.ref.veterinaire.animauxvivant"));
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
                    data.getFournisseur(), data.getConteneur(), data.getOrigine(),data.getFournisseur(),
                    data.getNombreColis());
            detCertificatProduitRepository.save(detCertificatProduit);
        });

    }

    public ResponseEntity<?> saveFormalite(Certificat certificat, String compteClient, String numeroAgrement, String nif,String numeroDossier,
                                           List<PieceJointeFormaliteDto> pieceJointeFormaliteDtos) {

        Optional<AutorisationAgrement> agrement = agrementRepository.findByNumero(numeroAgrement);

        if (agrement.isPresent()) {

            Formalite formalite = new Formalite();

            formalite.setChaine(Chaine.Export);
            formalite.setNumGenere("AUTANIMAUX" + System.currentTimeMillis());
            formalite.setCertificat(certificat);
            Optional<Structure> optionalStructure = structureRepository.findByCode(env.getProperty("message.structure.code"));
            if (!optionalStructure.isPresent()) {
                throw new IllegalArgumentException(env.getProperty("message.exception.reference"));
            }

            formalite.setAutorisationAgrement(agrement.get());
            formalite.setStructure(optionalStructure.get());
            formalite.setEtat(Etat.NON_SOUMIS);
            formalite.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            formalite.setDateDmd(new Timestamp(System.currentTimeMillis()));
            formalite.setCompteClient(compteClient);
            formalite.setNif(nif);
            formalite.setNumeroDossier(numeroDossier);
            Formalite saveFormalite = formaliteRepository.save(formalite);

            //sauvegarde de la piece jointe
            savePiecejointe(pieceJointeFormaliteDtos, saveFormalite);

            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.succes"),saveFormalite.getId()), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.not.found.entity"),numeroAgrement), HttpStatus.OK);
        }




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
