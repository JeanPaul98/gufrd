package com.acl.mswauth.model;

/**
 * @author kol on 10/28/24
 * @project msw-auth
 */

import com.acl.mswauth.model.enumeration.Statut;
import com.acl.mswauth.model.enumeration.TypeDemande;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "DMD_CERTIFICATION", schema = "USERMSW")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class DemandeCertification implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SEQ_DMD_CERTIFICATION")
    @SequenceGenerator(sequenceName = "SEQ_DMD_CERTIFICATION", allocationSize = 1, name = "SEQ_DMD_CERTIFICATION")
    @Column(name = "ID")
    private Long id;

    @Column(name = "ROW_UUID")
    private String rowUuid;

    @Column(name = "NIF")
    private String nif;

    @Column(name = "RAISON_SOCIALE")
    private String raisonSocial;

    @Column(name = "MAIL_NOTIFICATION")
    private String email;

    @Column(name = "CODE_A_VERIFIER")
    private String code;

    @Column(name = "CREATED_BY_USER")
    private String createdByUser;

    @Column(name = "STATUT")
    @Enumerated(EnumType.STRING)
    private Statut statut;

    @Column(name = "TYPE_DMD")
    @Enumerated(EnumType.STRING)
    private TypeDemande typeDemande;

    @Column(name = "UPDATED_BY_USER")
    private String updatedByUser;

    @Column(name = "UPDATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(name = "CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "DELAI_EXPIRATION_CODE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date delaiExpirationCode;


    public DemandeCertification(String rowUuid, String nif, String email1, String raisonSocial) {
        this.rowUuid = rowUuid;
        this.nif = nif;
        this.email = email1;
        this.raisonSocial = raisonSocial;
    }
}
