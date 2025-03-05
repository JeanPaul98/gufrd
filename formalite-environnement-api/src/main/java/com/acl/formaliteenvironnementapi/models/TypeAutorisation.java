/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acl.formaliteenvironnementapi.models;

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
@Table(name = "TYPE_AUTORISATION", schema = "USERFME")
@Getter
@Setter
@NoArgsConstructor
@Schema(implementation = TypeAutorisation.class)
public class TypeAutorisation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_TYPE_AUTORISATION")
    @SequenceGenerator(sequenceName = "S_TYPE_AUTORISATION", allocationSize = 1, name = "S_TYPE_AUTORISATION")
    @Column(name = "ID")
    private Long id;

    @Column(name = "LIBELLE")
    private String libelle;

    private String ref;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeAutorisation that = (TypeAutorisation) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
