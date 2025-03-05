package com.acl.formaliteagricultureapi.models;

import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import com.acl.formaliteagricultureapi.models.enumeration.StatutAgrement;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author zansouye on 03/10/2024
 * @project formalite-agriculture-api
 */
@Entity
@Table(name = "AUTORISATION_AGREMENT", schema = "USERFMA")
@Getter
@Setter
@Schema(implementation = AutorisationAgrement.class)
public class AutorisationAgrement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_AUTORISATION_AGREMENT")
    @SequenceGenerator(sequenceName = "S_AUTORISATION_AGREMENT", allocationSize = 1, name = "S_AUTORISATION_AGREMENT")
    @Column(name = "ID")
    private Long id;

    @Column(name = "NUMERO")
    private String numero;

    @Column(name = "ACTIVITES")
    private String activite;

    @Column(name = "OBSERVATION")
    private String observation;

    @Enumerated(EnumType.STRING)
    private Etat etat;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut")
    private StatutAgrement statutAgrement;

    @Column(name = "DATE_AGREMENT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAgrement;

    @Column(name = "DATE_EXPIRATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateExpiration;

    @Column(name = "DATE_SUSPENSION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSuspension;

    @Column(name = "DATE_RETRAIT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRetirer;

    @Column(name = "DATE_RENOUVELEMENT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRenouveler;

    @Column(name = "compte_agent_renouvele")
    private String compteAgentRenouveler;

    @Column(name = "compte_agent_accepte")
    private String compteAgentAccepter;

    @Column(name = "compte_agent_retrait")
    private String compteAgentRetirer;

    @Column(name = "compte_agent_suspendu")
    private String compteAgentSuspendu;

    @Column(name = "renouvele")
    private boolean renouveler;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_AT")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_AT")
    private Date updateAt;

    @ManyToOne
    @JoinColumn(name = "ID_DMD_AUTORISATION_AGREMENT", referencedColumnName = "ID")
    private DmdAutorisationAgrement dmdAutorisationAgrement;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AutorisationAgrement agrement = (AutorisationAgrement) o;
        return Objects.equals(id, agrement.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
