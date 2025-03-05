package com.acl.vbs.fret.converters;

import com.acl.vbs.fret.entities.*;
import com.acl.vbs.fret.responses.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AppConverter {

    public static ChargeurResponse toChargeurResponse(Chargeur chargeur) {
        ChargeurResponse response = new ChargeurResponse();
        BeanUtils.copyProperties(chargeur, response);
        return response;
    }

    public static ParametrageResponse toParametrageResponse(Parametrage parametrage) {
        ParametrageResponse response = new ParametrageResponse();
        BeanUtils.copyProperties(parametrage, response);
        return response;
    }

    public static DmdDeclarationFretResponse toDmdDeclarationFretResponse(DmdDeclarationFret dmdDeclFret) {
        DmdDeclarationFretResponse response = new DmdDeclarationFretResponse();
        BeanUtils.copyProperties(dmdDeclFret, response);
        response.setChargeur(toChargeurResponse(dmdDeclFret.getChargeur()));
        response.setDeclarant(toDeclarantResponse(dmdDeclFret.getDeclarant()));
        response.setDestinataireMarchandise(toDestinataireMarchandiseResponse(dmdDeclFret.getDestinataireMarchandise()));
        return response;
    }

    public static DmdDeclarationFretResponse toDmdDeclarationFretResponse(DmdDeclarationFret dmdDeclFret, List<PieceJustificative> pieceJustificative) {
        DmdDeclarationFretResponse response = toDmdDeclarationFretResponse(dmdDeclFret);
        response.setPieceJustificatives(pieceJustificative.stream().map(AppConverter::toPieceJustificativeResponse).toList());
        return response;
    }

    public static GarantResponse toGarantResponse(Garant garant) {
        GarantResponse response = new GarantResponse();
        BeanUtils.copyProperties(garant, response);
        return response;
    }

    public static TransporteurResponse toTransporteurResponse(Transporteur transporteur) {
        TransporteurResponse response = new TransporteurResponse();
        BeanUtils.copyProperties(transporteur, response);
        response.setGarant(toGarantResponse(transporteur.getGarant()));
        return response;
    }

    public static DeclarantResponse toDeclarantResponse(Declarant declarant) {
        DeclarantResponse response = new DeclarantResponse();
        BeanUtils.copyProperties(declarant, response);
        return response;
    }

    public static DestinataireMarchandiseResponse toDestinataireMarchandiseResponse(DestinataireMarchandise declaration) {
        DestinataireMarchandiseResponse response = new DestinataireMarchandiseResponse();
        BeanUtils.copyProperties(declaration, response);
        return response;
    }

    public static ConducteurResponse toConducteurResponse(Conducteur conducteur) {
        ConducteurResponse response = new ConducteurResponse();
        BeanUtils.copyProperties(conducteur, response);
        return response;
    }

    public static CamionResponse toCamionResponse(Camion camion) {
        CamionResponse response = new CamionResponse();
        BeanUtils.copyProperties(camion, response);
        response.setTransporteur(toTransporteurResponse(camion.getTransporteur()));
        return response;
    }

    public static AffretementResponse toAffretementResponse(Affretement affretement) {
        AffretementResponse response = new AffretementResponse();
        BeanUtils.copyProperties(affretement, response);
        response.setCamion(toCamionResponse(affretement.getCamion()));
        response.setConducteur(toConducteurResponse(affretement.getConducteur()));
        response.setDeclarationFret(toDmdDeclarationFretResponse(affretement.getDeclarationFret()));
        return response;
    }

    public static ApprentiResponse toApprentiResponse(Apprenti apprenti) {
        ApprentiResponse response = new ApprentiResponse();
        BeanUtils.copyProperties(apprenti, response);
        response.setConducteur(toConducteurResponse(apprenti.getConducteur()));
        return response;
    }

    public static TypePieceResponse toTypePieceResponse(TypePiece typePiece) {
        TypePieceResponse response = new TypePieceResponse();
        BeanUtils.copyProperties(typePiece, response);
        return response;
    }

    public static PieceJustificativeResponse toPieceJustificativeResponse(PieceJustificative pieceJustificative) {
        PieceJustificativeResponse response = new PieceJustificativeResponse();
        BeanUtils.copyProperties(pieceJustificative, response);
        response.setTypePiece(toTypePieceResponse(pieceJustificative.getTypePiece()));
        response.setDeclarationFret(toDmdDeclarationFretResponse(pieceJustificative.getDeclarationFret()));
        return response;
    }
}
