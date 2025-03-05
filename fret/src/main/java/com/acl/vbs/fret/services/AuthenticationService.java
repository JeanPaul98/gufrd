package com.acl.vbs.fret.services;

import com.acl.vbs.fret.responses.MSWUserResponse;

public interface AuthenticationService {

    MSWUserResponse getAuthInfo();

    MSWUserResponse getAuthChargeurInfo();
}
