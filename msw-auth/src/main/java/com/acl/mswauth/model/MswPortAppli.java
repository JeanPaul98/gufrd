package com.acl.mswauth.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "ports_applis", schema = "USERMSW")
/*
@ApiModel(value = "MswPortsAppli")
*/
public class MswPortAppli  implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_msw_ports_applis")
    @SequenceGenerator(sequenceName = "seq_msw_ports_applis", allocationSize = 1, name = "seq_msw_ports_applis")
    @Column(name = "PORTAPPLI_ID")
    private Long id;

    @Column(name = "PORTAPPLI_URL")
    private String url;


    @ManyToOne
    @JoinColumn(name = "PORT_CODE")
    private MswPort mswPort;

    @ManyToOne
    @JoinColumn(name = "APPLI_ID")
    private MswApplication mswApplication;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PORTAPPLI_ADD_DATE")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PORTAPPLI_UPDATE_DATE")
    private Date updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MswPortAppli that = (MswPortAppli) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
