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
@Table(name = "SOCIETE", schema = "USERFMA")
@Getter
@Setter
@NoArgsConstructor
@Schema(implementation = Societe.class)
public class Societe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_SOCIETE")
    @SequenceGenerator(sequenceName = "S_SOCIETE", allocationSize = 1, name = "S_SOCIETE")
    @Column(name = "ID")
    private Long id;

    @Column(name = "RAISON_SOCIALE")
    private String raisonSociale;

    @Column(name = "NUMERO_ENREG")
    private String numeroEnreg;

    @Column(name = "NIF")
    private String nif;

    @Column(name = "ADRESSE")
    private String adresse;

    @Column(name = "FORME_JURIDIQUE")
    private String formeJuridique;

    @Column(name = "NUM_RCCM")
    private String numRccm;
    
    @Column(name = "CONTACT")
    private String contact;
    
    @Column(name = "EMAIL")
    private String email;
    
    @JoinColumn(name = "ID_TYPE_SOCIETE", referencedColumnName = "ID")
    @ManyToOne
    private TypeSociete typeSociete;

   /* @OneToMany
    @Fetch(FetchMode.SELECT)
    private Set<AutorisationAgrement> agrements;*/
    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Societe that = (Societe) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
