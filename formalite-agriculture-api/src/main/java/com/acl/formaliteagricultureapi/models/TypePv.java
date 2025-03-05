package com.acl.formaliteagricultureapi.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Olivier
 */

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "type_pv", schema = "userfma")
@Schema(implementation = TypePv.class)
public class TypePv implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "s_type_pv")
    @SequenceGenerator(sequenceName = "s_type_pv", allocationSize = 1, name = "s_type_pv")
    @Column(name = "ID")
    private Long id;

    private String libelle;

    private String ref;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypePv typePv = (TypePv) o;
        return Objects.equals(id, typePv.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
