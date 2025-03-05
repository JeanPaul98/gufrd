package com.acl.formalitesanteapi.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "CERTIFICAT", schema = "USERFMS")
@Schema(implementation = Certificat.class)
public class Certificat implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_CERTIFICAT_SANTE")
    @SequenceGenerator(sequenceName = "S_CERTIFICAT_SANTE", allocationSize = 1, name = "S_CERTIFICAT_SANTE")
    @Column(name = "ID")
    private Long id;

    @Column(name = "NUMERO_ENREGISTREMENT")
    private String numeroEnregistrement;


    @ManyToOne
    @JoinColumn(name = "ID_TYPE_CERTIFICAT")
    private TypeCertificat typeCertificat;

    @OneToOne(mappedBy = "certificat")
    private Formalite formalite;


    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;


    public Certificat(String nuemroCerficatRenouveler) {
        this.numeroEnregistrement = nuemroCerficatRenouveler;
    }

}
