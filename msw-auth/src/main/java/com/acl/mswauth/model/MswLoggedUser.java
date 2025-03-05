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
@Table(name = "MSW_LOGGED_USER", schema = "USERMSW")
public class MswLoggedUser implements Serializable {

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SEQ_MSW_LOGGED_USER")
    @SequenceGenerator(sequenceName = "SEQ_MSW_LOGGED_USER", allocationSize = 1, name = "SEQ_MSW_LOGGED_USER")
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

    @Column(name = "ENTREPRISE")
    private String entreprise;

    @Column(name = "DATE_CONNEXION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateConnexion;

    @Column(name = "DATE_DECONNEXION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDeconnexion;

    @Column(name = "UPDATE_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;

    @Column(name = "CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;


    public MswLoggedUser(String userLogin, String sessionId, String fullName,
                         String compteClient, String countryCode, String portCode, String entreprise) {
            this.userLogin = userLogin;
            this.sessionId = sessionId;
            this.fullName = fullName;
            this.compteClient = compteClient;
            this.countryCode = countryCode;
            this.portCode = portCode;
            this.entreprise = entreprise;
    }
}
