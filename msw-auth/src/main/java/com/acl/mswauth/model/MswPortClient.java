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
@Table(name = "ports_clients", schema = "USERMSW")
/*
@ApiModel(value = "MswPortsAppli")
*/
public class MswPortClient implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_msw_ports_clients")
    @SequenceGenerator(sequenceName = "seq_msw_ports_clients", allocationSize = 1, name = "seq_msw_ports_clients")
    @Column(name = "PORTCLIENT_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PORT_CODE")
    private MswPort mswPort;

    @ManyToOne
    @JoinColumn(name = "CLIENT_ID")
    private MswClient mswClient;

    @Column(name = "PORT_CODE_CLIENT")
    private String codePortClient;
    
    @ManyToOne
    @JoinColumn(name = "STRUCTURE_ID")
    private MswStructure mswStructure;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MswPortClient that = (MswPortClient) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
