package com.acl.formaliteenvironnementapi.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author kol on 11/09/2024
 * @project formalite-environnement-api
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "DECL_DECHETS", schema = "USERFME")
@Schema(implementation = DeclarationDechets.class)
public class DeclarationDechets implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_DECL_DECHETS")
    @SequenceGenerator(sequenceName = "S_DECL_DECHETS", allocationSize = 1, name = "S_DECL_DECHETS")
    @Column(name = "ID")
    private Long id;

}
