package com.acl.formaliteenvironnementapi.serviceImpl.utils;


import com.acl.formaliteenvironnementapi.services.utils.UtilServices;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author kol on 12/09/2024
 * @project formalite-agriculture-api
 */
@Service
public class UtilServiceImpl implements UtilServices {

    @Override
    public String generateNumDemande(String ref) {

        SimpleDateFormat sdfdd = new SimpleDateFormat("ydHmmss");
        Date now = new Date();
        String strDate = sdfdd.format(now);
        long format = Long.valueOf(strDate);

        return format +ref;
}






}
