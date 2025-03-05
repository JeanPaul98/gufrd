package com.acl.mswauth.servicesImpl;

import com.acl.mswauth.converter.MswConnectedUserConverter;
import com.acl.mswauth.dto.MswConnectedUserDto;
import com.acl.mswauth.model.MswConnectedUser;
import com.acl.mswauth.model.MswLoggedUser;
import com.acl.mswauth.playload.ApiResponseModel;
import com.acl.mswauth.repositories.MswConnectedUserRepository;
import com.acl.mswauth.repositories.MswLoggedUserRepository;
import com.acl.mswauth.service.ConnectedUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class MswConnectedUserServiceImp implements ConnectedUserService {

    private final MswConnectedUserRepository mswConnectedUserRepository;
    private final MswConnectedUserConverter mswConnectedUserConverter;
    private final Logger logger = LoggerFactory.getLogger(MswConnectedUserServiceImp.class);

    private final MswLoggedUserRepository mswLoggedUserRepository;


    public MswConnectedUserServiceImp(MswConnectedUserRepository mswConnectedUserRepository, MswConnectedUserConverter mswConnectedUserConverter, MswLoggedUserRepository mswLoggedUserRepository) {
        this.mswConnectedUserRepository = mswConnectedUserRepository;
        this.mswConnectedUserConverter = mswConnectedUserConverter;
        this.mswLoggedUserRepository = mswLoggedUserRepository;
    }

    @Override
    public MswConnectedUser save(MswConnectedUserDto user) {
        Optional<MswConnectedUser> mswConnectedUser = mswConnectedUserRepository.findByUserLogin(user.getUserLogin());
        if (mswConnectedUser.isPresent()) {

            MswLoggedUser mswLoggedUser = new MswLoggedUser(user.getUserLogin(),
                    mswConnectedUser.get().getSessionId(),user.getFullName(),
                    user.getCompteClient(), user.getCountryCode(), user.getPortCode(), user.getEntreprise());
            mswLoggedUser.setDateConnexion(new Date());
            mswLoggedUser.setCreatedAt(new Date());
            mswLoggedUserRepository.save(mswLoggedUser);
            return mswConnectedUser.get();

        } else {
            MswConnectedUser  saveUser= mswConnectedUserConverter.convertEntity(user);
            saveUser.setSessionId(generateUUID());
            saveUser.setDateConnexion(new Date());
            mswConnectedUserRepository.save(saveUser);

            MswLoggedUser mswLoggedUser = new MswLoggedUser(user.getUserLogin(),
                    saveUser.getSessionId(),user.getFullName(),
                    user.getCompteClient(), user.getCountryCode(), user.getPortCode(), user.getEntreprise());

            mswLoggedUser.setDateConnexion(new Date());
            mswLoggedUser.setCreatedAt(new Date());
            mswLoggedUserRepository.save(mswLoggedUser);

            return saveUser;
        }
    }

    @Override
    public Optional<MswConnectedUser> findByLogin(String login) {

        return mswConnectedUserRepository.findByUserLogin(login);
    }

    @Override
    public void deleteByLogin(String login) {
        Optional<MswConnectedUser> mswConnectedUser = mswConnectedUserRepository.findByUserLogin(login);
        logger.info("User {} deleted", mswConnectedUser.get().getUserLogin());

        MswLoggedUser mswLoggedUser = new MswLoggedUser(mswConnectedUser.get().getUserLogin(),
                mswConnectedUser.get().getSessionId(),mswConnectedUser.get().getFullName(),
                mswConnectedUser.get().getCompteClient(), mswConnectedUser.get().getCountryCode(),
                mswConnectedUser.get().getPortCode(), mswConnectedUser.get().getEntreprise());

        mswLoggedUser.setDateDeconnexion(new Date());
        mswLoggedUser.setCreatedAt(new Date());
        mswLoggedUserRepository.save(mswLoggedUser);

        mswConnectedUser.ifPresent(mswConnectedUserRepository::delete);
    }

    @Override
    public ResponseEntity<?> findBySession(String session) {
        Optional<MswConnectedUser> mswConnectedUser = mswConnectedUserRepository.findBySession(session);
        if (mswConnectedUser.isPresent()) {
       return      new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    "Opération réussie ",mswConnectedUser.get()), HttpStatus.OK);

        } else {
        return     new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    "Utilisateur non connecté "), HttpStatus.OK);

        }

    }

    private String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
