package com.acl.formalitesanteapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author kol on 08/09/2024
 * @project formalite-sante-api
 */
@Entity
@Table(name = "TYPE_INSPECTION",schema = "USERFMS")
@Getter
@Setter
@NoArgsConstructor
public class TypeInspection implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_TYPE_INSPECTION_SANTE")
    @SequenceGenerator(sequenceName = "S_TYPE_INSPECTION_SANTE",
            allocationSize = 1, name = "S_TYPE_INSPECTION_SANTE")
    @Column(name = "ID")
    private Long id;

    @Column(name = "LIBELLE")
    private String libelle;

    private String ref;

}
