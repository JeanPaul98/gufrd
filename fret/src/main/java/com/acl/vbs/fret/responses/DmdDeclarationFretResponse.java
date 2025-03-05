package com.acl.vbs.fret.responses;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class DmdDeclarationFretResponse implements Serializable {
    private Long id;
    private DeclarantResponse declarant;
    private ChargeurResponse chargeur;
    private DestinataireMarchandiseResponse destinataireMarchandise;
    private List<PieceJustificativeResponse> pieceJustificatives;
    private String sensTrafic;
    private String modeTransport;
    private String origineMarchandise;
    private String blNumeroConnaissement;
    private String portEmbarquement;
    private String nomNavire;
    private String nationaliteNavire;
    private Long nombreCamionsSouhaite;
    private Long prixTransportSouhaiteTonne;
    private boolean affichagePrixRecepisse;
    private String paysProvenance;
    private String villeProvenance;
    private String paysDestination;
    private String villeDestination;
    private String createdBy;
    private Boolean statut;
    private Date datePayement;
    private Date createdAt;
    private String updateBy;
    private Date updateAt;
}