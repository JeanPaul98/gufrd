/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acl.vbs.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @author Elikplim 18//11/2024
 */
@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "CONTENEUR", schema = "SIPEDBA")
public class Conteneur implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NUM_CONTENEUR")
    private String numConteneur;
    @Basic(optional = false)
    @Column(name = "INTERDIT")
    private Boolean interdit;
    @Basic(optional = false)
    @Column(name = "LIBRE")
    private Boolean libre;
    @Basic(optional = false)
    @Column(name = "VIDE")
    private Boolean vide;
    @JsonIgnore
    @OneToMany(mappedBy = "numConteneur")
    private List<LigneMse> ligneMseList;
    @JoinColumn(name = "CODE_TYPE_CONTENEUR", referencedColumnName = "CODE_TYPE_CONTENEUR")
    @ManyToOne(optional = false)
    private TypeConteneur codeTypeConteneur;
}
