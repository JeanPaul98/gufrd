package com.acl.formaliteenvironnementapi.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author kol on 11/09/2024
 * @project formalite-environnement-api
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "AUTORISATION", schema = "USERFME")
@Schema(implementation = Autorisation.class)
public class Autorisation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_AUTORISATION_ENV")
    @SequenceGenerator(sequenceName = "S_AUTORISATION_ENV", allocationSize = 1, name = "S_AUTORISATION_ENV")
    @Column(name = "ID")
    private Long id;


    @ManyToOne
    @JoinColumn(name = "ID_TYPE_AUTORISATION", referencedColumnName = "id")
    private TypeAutorisation typeAutorisation;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;

}
