package com.acl.mswauth.model;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "groupes_clients", schema = "USERMSW")
/*
@ApiModel(value = "MswGroupeClient")
*/
public class MswGroupeClient implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_msw_groupes_clients")
    @SequenceGenerator(sequenceName = "seq_msw_groupes_clients", allocationSize = 1, name = "seq_msw_groupes_clients")
    @Column(name = "GROUPECLIENT_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "GROUPE_ID")
    private MswGroupe mswGroupe;

    @ManyToOne
    @JoinColumn(name = "CLIENT_ID")
    private MswClient mswClient;
    
    @ManyToOne
    @JoinColumn(name = "STRUCTURE_ID")
    private MswStructure mswStructre;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MswGroupeClient that = (MswGroupeClient) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
