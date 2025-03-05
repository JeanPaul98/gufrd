package com.acl.formaliteagricultureapi.serviceImpl.utils;

import com.acl.formaliteagricultureapi.repository.DemandeAutorisationAgrementRepository;
import com.acl.formaliteagricultureapi.services.utils.UtilServices;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.Year;
import java.util.UUID;


/**
 * @author kol on 12/09/2024
 * @project formalite-agriculture-api
 */
@Service
public class UtilServiceImpl implements UtilServices {

    private  final DemandeAutorisationAgrementRepository demandeAutorisationAgrementRepository;

    public UtilServiceImpl(DemandeAutorisationAgrementRepository demandeAutorisationAgrementRepository) {
        this.demandeAutorisationAgrementRepository = demandeAutorisationAgrementRepository;
    }

    @Override
            public String generateNumDemande(String ref) {

                SimpleDateFormat sdfdd = new SimpleDateFormat("Hmmss");
                Date now = new Date();
                String strDate = sdfdd.format(now);
                long format = Long.valueOf(strDate);

                return format +ref;
        }

        public String genererNumeroDemande(String valeurPersonnalisee) {
           long count = demandeAutorisationAgrementRepository.count();

            long numeroDemande = count + 1;
            int currentYear = Year.now().getValue();
            return String.format("%03d/%s/%d", numeroDemande, valeurPersonnalisee, currentYear);
        }

    @Override
    public String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
