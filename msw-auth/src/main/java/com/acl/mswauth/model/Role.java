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
@Table(name = "roles", schema = "USERMSW")
public class Role implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_msw_roles")
    @SequenceGenerator(sequenceName = "seq_msw_roles", allocationSize = 1, name = "seq_msw_roles")
    @Column(name = "ROLE_ID")
    private Long id;

    @Column(name ="ROLE_NAME",unique = true, nullable = false)
    private String name;

    @Column(name = "ROLE_DESC")
    private String roleDesc;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
