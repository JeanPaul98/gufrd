package com.acl.mswauth.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "APPLIS", schema = "USERMSW")
/*
@ApiModel(value = "Applis")
*/
public class MswApplication implements Serializable {
    /**
	 * 
	 */
	@Serial
    private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_msw_applis")
    @SequenceGenerator(sequenceName = "seq_msw_applis", allocationSize = 1, name = "seq_msw_applis")
    @Column(name = "APPLI_ID")
    private Long id;

    @Column(name = "APPLI_NAME",unique = true)
    private String name;

    @Column(name = "APPLI_DESC")
    private String description;

    @Column(name = "APPLI_REF")
    private String reference;

    @Column(name = "APPLI_STATUS")
    private boolean status;

    @Column(name = "APPLI_ORDRE")
    private int ordre;

    @Column(name = "APPLI_LOGO")
    private String logo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "APPLI_ADD_DATE")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "APPLI_UPDATE_DATE")
    private Date updatedAt;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MswApplication that = (MswApplication) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
