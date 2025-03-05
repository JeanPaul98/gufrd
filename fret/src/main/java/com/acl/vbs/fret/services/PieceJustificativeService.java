package com.acl.vbs.fret.services;

import com.acl.vbs.fret.requests.PieceJustificativeRequest;
import com.acl.vbs.fret.responses.PieceJustificativeResponse;

import java.util.List;

public interface PieceJustificativeService {

    List<PieceJustificativeResponse> create(PieceJustificativeRequest request);

    List<PieceJustificativeResponse> listPieceJustificative(Long idDeclarationFret);
}
