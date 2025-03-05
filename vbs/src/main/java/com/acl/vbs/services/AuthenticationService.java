package com.acl.vbs.services;

import com.acl.vbs.responses.MSWHttpClientResponse;
import com.acl.vbs.responses.MSWUserResponse;

public interface AuthenticationService {

    MSWUserResponse getAuthInfo();
}
