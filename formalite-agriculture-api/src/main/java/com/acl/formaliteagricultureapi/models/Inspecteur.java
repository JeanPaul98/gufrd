package com.acl.formaliteagricultureapi.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Zansouyé
 */
@Entity
@Table(name = "INSPECTEUR", schema = "USERFMA")
@Getter
@Setter
@NoArgsConstructor
@Schema(implementation = Inspecteur.class)
public class Inspecteur implements Serializable {
	
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_INSPECTEUR")
    @SequenceGenerator(sequenceName = "S_INSPECTEUR", allocationSize = 1, name = "S_INSPECTEUR")
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOM_INSP")
    private String nomInspecteur;

    @Column(name = "PRENOMS_INSP")
    private String prenomsInspecteur;

    @Column(name = "TELEPHONE")
    private String numeroTelephone;

    @Column(name = "FONCTION")
    private String fonction;

    @NotNull
    @Column(name = "CODE_STRUCTURE")
    private String codeStructure;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inspecteur that = (Inspecteur) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
