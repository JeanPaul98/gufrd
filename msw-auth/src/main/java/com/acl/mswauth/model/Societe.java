package com.acl.mswauth.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author kol on 11/5/24
 * @project msw-auth
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "SOCIETES", schema = "USERMSW")
public class Societe implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_societe")
    @SequenceGenerator(sequenceName = "seq_societe", allocationSize = 1, name = "seq_societe")
    @Column(name = "ID")
    private Long id;

    @Column(name = "RAISON_SOCIALE")
    private String raisonSociale;

    @Column(name = "FORME_JURIDIQUE")
    private String formeJuridique;

    @Column(name = "NUM_RCCM")
    private String numRccm;

    @Column(name = "NIF")
    private String nif;

    @Column(name = "ADRESSE")
    private String adresse;

    @Column(name = "CONTACT")
    private String contact;

    @Column(name = "EMAIL")
    private String email;
}
