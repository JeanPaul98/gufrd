/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acl.vbs.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * @author DEV
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CLIENT", schema = "SIPEDBA")
public class Client implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_CLIENT")
    private Long idClient;
    @Basic(optional = false)
    @Column(name = "COMPTE_CLIENT")
    private String compteClient;
    @Basic(optional = false)
    @Column(name = "RAISON_SOCIAL_CLIENT")
    private String raisonSocialClient;
    @Basic(optional = false)
    @Column(name = "NIF_CLIENT")
    private String nifClient;
    @Basic(optional = false)
    @Column(name = "NRC_CLIENT")
    private String nrcClient;
    @Column(name = "ADRESSE_CLIENT")
    private String adresseClient;
    @Basic(optional = false)
    @Column(name = "TEL_CLIENT")
    private String telClient;
    @Column(name = "FAX_CLIENT")
    private String faxClient;
    @Column(name = "BP_CLIENT")
    private String bpClient;
    @Column(name = "EMAIL_CLIENT")
    private String emailClient;
    @Column(name = "NOM_RESPONSABLE")
    private String nomResponsable;
    @Column(name = "NOM_INTERLOCUTEUR")
    private String nomInterlocuteur;
    @Column(name = "TEL_INTERLOCUTEUR")
    private String telInterlocuteur;
    @Basic(optional = false)
    @Column(name = "CLIENT_EXONERE")
    private short clientExonere;
    @Column(name = "ECHEANCE_CLIENT")
    private BigInteger echeanceClient;
    @Column(name = "CIVILITE")
    private String civilite;
    @Column(name = "TITRE")
    private String titre;
    @Basic(optional = false)
    @Column(name = "TYPE_CLIENT")
    private String typeClient;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "MNT_CAUTION")
    private BigDecimal mntCaution;
    @Column(name = "DATE_ECHEANCE_CAUTION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEcheanceCaution;
    @Basic(optional = false)
    @Column(name = "BLOQUE")
    private short bloque;
    @Basic(optional = false)
    @Column(name = "ALIAS_CLIENT")
    private String aliasClient;
    @Basic(optional = false)
    @Column(name = "ACTIF")
    private short actif;
    @Basic(optional = false)
    @Column(name = "DATE_CREATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;
    @Column(name = "LOGIN")
    private String login;
    @JsonIgnore
    @Column(name = "PASSWORD")
    private String password;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "client")
    private Consignataire consignataire;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "client")
    private Transitaire transitaire;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "client")
    private Concessionnaire concessionnaire;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "client")
    private AutreClient autreClient;
    @JsonIgnore
    @JoinColumn(name = "CODE_FORME_JURIDIQUE", referencedColumnName = "CODE_FORME_JURIDIQUE")
    @ManyToOne(optional = false)
    private FormeJuridique formeJuridique;

}
