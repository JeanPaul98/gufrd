package com.acl.mswauth.service.statistique;

import com.acl.mswauth.dto.formalite.FormaliteStatistiqueDto;
import com.acl.mswauth.interfaces.FormaliteInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author kol on 10/21/24
 * @project msw-auth
 */
@Service
public interface StatistiqueFormaliteService {

    ResponseEntity<?> getFormaliteTraiterPhyto() throws IOException;

    List<FormaliteStatistiqueDto> getFormaliteStatistique() throws IOException;

    ResponseEntity<?> getFormalitePageable(int page, int size);
}
