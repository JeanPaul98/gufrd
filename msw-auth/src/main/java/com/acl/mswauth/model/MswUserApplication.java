package com.acl.mswauth.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "users_applis", schema = "USERMSW")
public class MswUserApplication implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_msw_users_applis")
    @SequenceGenerator(sequenceName = "seq_msw_users_applis", allocationSize = 1, name = "seq_msw_users_applis")
    @Column(name = "USERAPPLI_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "GROUPE_ID")
    private MswGroupe groupe;


    @ManyToOne
    @JoinColumn(name = "APPLI_ID")
    private MswApplication mswApplication;


    @ManyToOne
    @JoinColumn(name = "PORT_CODE")
    private MswPort mswPort;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "USERAPPLI_ADD_DATE")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "USERAPPLI_UPDATE_DATE")
    private Date updatedAt;

    public MswUserApplication(MswGroupe groupe, MswApplication mswApplication, MswPort mswPort) {
        this.groupe = groupe;
        this.mswApplication = mswApplication;
        this.mswPort = mswPort;
    }
}
