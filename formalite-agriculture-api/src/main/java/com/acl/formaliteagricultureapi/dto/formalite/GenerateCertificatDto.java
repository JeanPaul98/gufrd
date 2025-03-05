package com.acl.formaliteagricultureapi.dto.formalite;

import com.acl.formaliteagricultureapi.dto.produit.DetCertificatProduitDto;
import com.acl.formaliteagricultureapi.models.DetCertificatProduit;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author kol on 10/4/24
 * @project formalite-agriculture-api
 */
@Getter
@Setter
@ToString
@Schema(description = "GenerateCertificatDto")
public class GenerateCertificatDto {

    private String veterinaire;
    private String paysOrigine;
    private String nomAdressExpediteur;
    private String nomAdressDestinataire;
    private double poidsTotal;
    private long nombreUnites;
    private String lieuChargement;
    private String destination;
    private String moyensTransport;
    private String conteneur;
    private String inspecteurOfficiel;
    private String natureProduit;
    private String typeCertificat;

    private List<DetCertificatProduitDto> detProduits;
}
