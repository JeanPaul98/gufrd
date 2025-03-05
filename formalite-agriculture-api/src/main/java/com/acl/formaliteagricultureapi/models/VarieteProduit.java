package com.acl.formaliteagricultureapi.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Zansouyé on 23/08/2024
 * @project formalite-agriculture-api
 */
@Entity
@Table(name = "VARIETE_PRODUIT", schema = "USERFMA")
@Getter
@Setter
@NoArgsConstructor
@Schema(implementation = VarieteProduit.class)
public class VarieteProduit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_VARIETE_PRODUIT")
    @SequenceGenerator(sequenceName = "S_VARIETE_PRODUIT", allocationSize = 1, name = "S_VARIETE_PRODUIT")
    @Column(name = "ID")
    private Long id;

    @Column(name = "LIBELLE")
    private String libelle;

    private String ref;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VarieteProduit that = (VarieteProduit) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
