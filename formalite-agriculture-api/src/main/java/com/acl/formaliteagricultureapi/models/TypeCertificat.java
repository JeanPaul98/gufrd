package com.acl.formaliteagricultureapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author kol on 04/09/2024
 * @project formalite-agriculture-api
 */
@Entity
@Table(name = "TYPE_CERTIFICAT",schema = "USERFMA")
@Getter
@Setter
@NoArgsConstructor
public class TypeCertificat implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_TYPE_CERTIFICAT")
    @SequenceGenerator(sequenceName = "S_TYPE_CERTIFICAT", allocationSize = 1, name = "S_TYPE_CERTIFICAT")
    @Column(name = "ID")
    private Long id;

    @Column(name = "LIBELLE")
    private String libelle;

    private String ref;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeCertificat that = (TypeCertificat) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
