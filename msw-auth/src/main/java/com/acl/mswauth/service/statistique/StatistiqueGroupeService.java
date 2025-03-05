package com.acl.mswauth.service.statistique;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 10/23/24
 * @project msw-auth
 */
@Service
public interface StatistiqueGroupeService {
    ResponseEntity<?> getListeStatistiqueGroupe();
}
