package com.acl.formalitesanteapi.dto.statistique;

import lombok.Getter;
import lombok.Setter;

/**
 * @author kol on 26/08/2024
 * @project formalite-agriculture-api
 */
@Getter
@Setter
public class StatistiqueDemandeDto {

    private int nonSoumise;

    private int soumise;

    private int traiter;

    private int accepter;

    private int rejeter;

    public StatistiqueDemandeDto(int nonSoumise, int soumise, int traiter, int accepter, int rejeter) {
        this.nonSoumise = nonSoumise;
        this.soumise = soumise;
        this.traiter = traiter;
        this.accepter = accepter;
        this.rejeter = rejeter;
    }
}
