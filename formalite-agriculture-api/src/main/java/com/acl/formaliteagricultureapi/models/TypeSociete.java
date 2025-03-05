package com.acl.formaliteagricultureapi.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Jules
 */
@Entity
@Table(name = "TYPE_SOCIETE", schema = "USERFMA")
@Getter
@Setter
@NoArgsConstructor
@Schema(implementation = TypeSociete.class)
public class TypeSociete implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_TYPE_SOCIETE")
    @SequenceGenerator(sequenceName = "S_TYPE_SOCIETE", allocationSize = 1, name = "S_TYPE_SOCIETE")
    @Column(name = "ID")
    private Long id;

    @Column(name = "LIBELLE")
    private String libelle;

    private String ref;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeSociete that = (TypeSociete) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
