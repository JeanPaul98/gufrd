package com.acl.formaliteagricultureapi.dto.agrement;

import com.acl.formaliteagricultureapi.models.enumeration.StatutPaiement;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author kol on 11/15/24
 * @project formalite-agriculture-api
 */
@Getter
@Setter
public class DemandeAgrementListDto {

                    private String idAgrement;
                    private String numero;
                    private String activite;
                    private String etat;
                    private Date dateDemande;
                    private Date dateExpiration;
                    private Date dateAnnulation;
                    private Date dateRejet;
                    private Date DateAccepte;
                    private Date dateSoumise;
                    private Date dateTraiter;
                    private String compteClient;
                    private String societe;
                    private String nif;
                    private double montant;
                    private boolean renouveler;
                    private String typeAgrement;
                    private String message;
                    private StatutPaiement statutPaiement;
}
