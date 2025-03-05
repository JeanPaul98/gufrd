package com.acl.formaliteagricultureapi.services.httpClient;

import org.apache.http.client.HttpClient;
import org.springframework.stereotype.Service;



/**
 * @author kol on 05/09/2024
 * @project formalite-agriculture-api
 */
@Service
public interface HttpClientService {

    HttpClient httpClient();
}
