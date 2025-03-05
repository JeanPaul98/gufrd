/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acl.vbs.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Elikplim 13/11/2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "AIRE_STOCKAGE")
@Entity
public class AireStockage implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODE_AIRE_STOCKAGE")
    private String codeAireStockage;
    @Basic(optional = false)
    @Column(name = "LIB_AIRE_STOCKAGE")
    private String libAireStockage;
    @Basic(optional = false)
    @Column(name = "AIRE_DEPOTAGE")
    private Boolean aireDepotage;
    @Basic(optional = false)
    @Column(name = "AIRE_EMPOTAGE")
    private Boolean aireEmpotage;
    @Basic(optional = false)
    @Column(name = "GESTION_DIRECTE")
    private Boolean gestionDirecte;
}
