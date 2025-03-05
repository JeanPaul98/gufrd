/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acl.formaliteagricultureapi.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;


/**
 *
 * @author Olivier
 */
@Entity
@Table(name = "TYPE_INSPPHYTO", schema = "USERFMA")
@Getter
@Setter
@NoArgsConstructor
@Schema(implementation = TypeInspPhyto.class)
public class TypeInspPhyto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_TYPE_INSPPHYTO")
    @SequenceGenerator(sequenceName = "S_TYPE_INSPPHYTO", allocationSize = 1, name = "S_TYPE_INSPPHYTO")
    @Column(name = "ID")
    private Long id;

    @Column(name = "LIBELLE")
    private String libelle;

    @Column(name = "REF")
    private String ref;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeInspPhyto that = (TypeInspPhyto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
