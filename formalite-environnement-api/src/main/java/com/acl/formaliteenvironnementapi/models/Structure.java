/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acl.formaliteenvironnementapi.models;



import com.acl.formaliteenvironnementapi.models.enumeration.TypeStructure;
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
@Table(name = "STRUCTURE", schema = "USERFME")
@Schema(implementation = Structure.class)
public class Structure implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_STRUCTURE_ENV")
    @SequenceGenerator(sequenceName = "S_STRUCTURE_ENV", allocationSize = 1, name = "S_STRUCTURE_ENV")
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
