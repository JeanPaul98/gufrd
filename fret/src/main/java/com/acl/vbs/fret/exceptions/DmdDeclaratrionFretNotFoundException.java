package com.acl.vbs.fret.exceptions;

import com.acl.vbs.fret.requests.DmdDeclarationFretRequest;

public class DmdDeclaratrionFretNotFoundException extends RuntimeException{

    public DmdDeclaratrionFretNotFoundException(String message) {
       super(message);
    }
}
