package com.acl.formaliteagricultureapi.serviceImpl.httpclient;

import com.acl.formaliteagricultureapi.services.httpClient.HttpClientService;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.stereotype.Service;


import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/**
 * @author kol on 05/09/2024
 * @project formalite-agriculture-api
 */
@Service
public class HttpClientServiceImpl implements HttpClientService {

    @Override
    public HttpClient httpClient() {
        try {
            return  HttpClients
                    .custom()
                    .setSSLContext(new SSLContextBuilder().loadTrustMaterial(null,
                            TrustAllStrategy.INSTANCE).build())
                    .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                    .build();
        } catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException e) {
            throw new RuntimeException(e);
        }
    }
}
