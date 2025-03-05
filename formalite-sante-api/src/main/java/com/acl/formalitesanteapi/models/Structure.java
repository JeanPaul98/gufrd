/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acl.formalitesanteapi.models;



import com.acl.formalitesanteapi.models.enumeration.TypeStructure;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 *
 * @author Olivier
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "STRUCTURE", schema = "USERFMS")
@Schema(implementation = Structure.class)
public class Structure implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_STRUCTURE_SANTE")
    @SequenceGenerator(sequenceName = "S_STRUCTURE_SANTE", allocationSize = 1, name = "S_STRUCTURE_SANTE")
    @Column(name = "ID")
    private Long id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "LIBELLE")
    private String libelle; 

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE_STRUCTURE")
    private TypeStructure typeStructure;


}
