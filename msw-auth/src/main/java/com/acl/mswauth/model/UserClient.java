package com.acl.mswauth.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @author kol on 10/15/24
 * @project msw-auth
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "USERS_CLIENTS", schema = "USERMSW")
public class UserClient implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SEQ_MSW_USERS_CLIENTS")
    @SequenceGenerator(sequenceName = "SEQ_MSW_USERS_CLIENTS", allocationSize = 1, name = "SEQ_MSW_USERS_CLIENTS")
    @Column(name = "ID")
    private Long id;

    @Column(name = "USER_FONCTION")
    private String fonction;

    @Column(name = "USER_MAIL")
    private String mail;

    @Column(name = "USER_MOBILE")
    private String mobile;

    @Column(name = "CREATED_BY_USER")
    private String createdByUser;

    @Column(name = "UPDATED_BY_USER")
    private String updatedByUser;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_AT")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_AT")
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "CLIENT_ID")
    private MswClient mswClient;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;



}
