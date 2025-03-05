package com.acl.mswauth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "users", schema = "USERMSW")
public class User implements Serializable {
    
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_msw_users")
    @SequenceGenerator(sequenceName = "seq_msw_users", allocationSize = 1, name = "seq_msw_users")
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "USER_LOGIN",unique = true)
    private String login;

    @Column(name = "USER_MAIL",unique = true)
    private String email;

    @Column(name = "USER_FULLNAME")
    private String fullname;

    @JsonIgnore
    @Column(name = "USER_PASSWORD")
    private String password;

    @Column(name = "USER_STATUS")
    private boolean status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "USER_ADD_DATE")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "USER_UPDATE_DATE")
    private Date updatedAt;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "USER_DATE_INSCRIPTION")
    private Date dateInscription;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "USER_DATE_EXP_PASSWORD")
    private Date dateExpPassword;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "USER_DATE_ACTIVATION")
    private Date dateActivation;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "USER_DATE_CHANG_PWD")
    private Date dateChangPwd;
    
    @Column(name = "USER_FONCTION")
    private String fonction;
    
    @Column(name = "USER_MOBILE")
    private String mobile;
    
    @ManyToOne
    @JoinColumn(name = "CLIENT_ID")
    private MswClient mswClient;
    
    @ManyToOne
    @JoinColumn(name = "STRUCTURE_ID")
    private MswStructure mswStructure;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", schema = "USERMSW",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User USER = (User) o;
        return Objects.equals(id, USER.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
