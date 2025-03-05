package com.acl.formaliteagricultureapi.models;

import com.acl.formaliteagricultureapi.models.enumeration.EtatTypeAgrement;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author Zansouyé on 25/09/2024
 * @project formalite-agriculture-api
 */
@Entity
@Table(name = "AGREMENT", schema = "USERFMA")
@Getter
@Setter
@NoArgsConstructor
@Schema(implementation = AutorisationAgrement.class)
public class Agrement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_AGREMENT")
    @SequenceGenerator(sequenceName = "S_AGREMENT", allocationSize = 1, name = "S_AGREMENT")
    @Column(name = "ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "ETAT_AGREMENT")
    private EtatTypeAgrement etatTypeAgrement;

    @Column(name = "libelle")
    private String libelle;

   @Column(name = "CREATED_BY_USER")
    private String createdByUser;

    @Column(name = "UPDATE_BY_USER")
    private String updatedByUser;

    @Column(name = "MONTANT")
    private double montant;

    @Column(name = "DUREE_MOIS")
    private int duree;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_AT")
    private Date updateAt;

    @ManyToOne
    @JoinColumn(name = "ID_STRUCTURE", referencedColumnName = "id")
    private Structure structure;

    public Agrement(EtatTypeAgrement etatTypeAgrement, String libelle, String compteClient,
                    double montant, int duree, Date createdAt, Structure structure) {
        this.etatTypeAgrement = etatTypeAgrement;
        this.libelle = libelle;
      //  this.compteClient = compteClient;
        this.montant = montant;
        this.duree = duree;
        this.createdAt = createdAt;
        this.structure = structure;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agrement that = (Agrement) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
