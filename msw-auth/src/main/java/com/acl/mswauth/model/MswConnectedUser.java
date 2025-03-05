package com.acl.mswauth.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "connected_user", schema = "USERMSW")
public class MswConnectedUser implements Serializable {

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SEQ_MSW_CONNECTED_USER")
    @SequenceGenerator(sequenceName = "SEQ_MSW_CONNECTED_USER", allocationSize = 1, name = "SEQ_MSW_CONNECTED_USER")
    @Column(name = "ID")
    private Long id;

    @Column(name = "USER_LOGIN")
    private String userLogin;

    @Column(name = "COMPTE_CLIENT")
    private String compteClient;

    @Column(name = "SESSION_ID")
    private String sessionId;

    @Column(name = "COUNTRY_CODE")
    private String countryCode;

    @Column(name = "PORT_CODE")
    private String portCode;

    @Column(name = "FULLNAME")
    private String fullName;

    @Column(name = "DATE_CONNEXION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateConnexion;

    @Column(name = "DATE_DECONNEXION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDeconnexion;

    @Column(name = "ENTREPRISE")
    private String entreprise;

}
