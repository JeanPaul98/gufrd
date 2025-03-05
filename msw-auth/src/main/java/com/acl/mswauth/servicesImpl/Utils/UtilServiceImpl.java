package com.acl.mswauth.servicesImpl.Utils;

import com.acl.mswauth.service.utils.UtilService;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

/**
 * @author kol on 10/28/24
 * @project msw-auth
 */
@Service
public class UtilServiceImpl implements UtilService {

    SimpleDateFormat sfDateHeure = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    @Override
    public String generateUUID() {
        return UUID.randomUUID().toString();
    }

    @Override
    public String randomCode() {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10)); // Generates a random digit (0-9)
        }
        return sb.toString();
    }

    @Override
    public Date ajoutDate(long request) {
            LocalDateTime dateExpiration = LocalDateTime.now().plusDays(request);
            Instant instant = dateExpiration.atZone(ZoneId.systemDefault()).toInstant();
            return Date.from(instant);
    }

    @Override
    public Date ajoutMinute(long min) {
        LocalDateTime dateExpiration = LocalDateTime.now().plusMinutes(min);
        Instant instant = dateExpiration.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    @Override
    public boolean valideExpireDate(Date date) {
        LocalDateTime dateJout = LocalDateTime.now();

        return false;
    }
}
