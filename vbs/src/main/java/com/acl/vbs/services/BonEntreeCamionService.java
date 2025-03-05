/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acl.vbs.services;

import com.acl.vbs.requests.BonEntreeCamionRequest;
import com.acl.vbs.requests.BonEntreeCamionUpdatePoidsRequest;
import com.acl.vbs.responses.BonEntreeCamionRedevenceResponse;
import com.acl.vbs.responses.BonEntreeCamionResponse;
import com.acl.vbs.responses.DeclarationResponse;
import com.acl.vbs.responses.DetailBonEntreeCamionResponse;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author Elikplim 14/11/2024
 */
public interface BonEntreeCamionService {

    List<BonEntreeCamionResponse> listDeclarationCamion();

    Page<BonEntreeCamionResponse> listDeclarationCamion(int size, int page);

    List<DeclarationResponse> listCamionsAttendus(String sensTrafic);

    Page<DeclarationResponse> listCamionsAttendus(String sensTrafic, int page, int size);

    BonEntreeCamionResponse creationDeclaration(BonEntreeCamionRequest request);

    BonEntreeCamionResponse updatePoidsVide(String numBonEntreeCamion, BonEntreeCamionUpdatePoidsRequest request);

    DetailBonEntreeCamionResponse listDetailBonEntreeCamion(String numBonEntre);

    BonEntreeCamionRedevenceResponse getRedevance(String numBonEntreeCamion);
}
