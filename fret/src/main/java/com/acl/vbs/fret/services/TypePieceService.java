package com.acl.vbs.fret.services;

import com.acl.vbs.fret.requests.TypePieceRequest;
import com.acl.vbs.fret.responses.TypePieceResponse;

import java.util.List;

public interface TypePieceService {
    TypePieceResponse create(TypePieceRequest request);

    List<TypePieceResponse> list();

    Object deleteTypePiece(Long id);
}
