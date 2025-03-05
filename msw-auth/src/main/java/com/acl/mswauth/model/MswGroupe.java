package com.acl.mswauth.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "groupes", schema = "USERMSW")
public class MswGroupe implements Serializable {

    /**
	 * 
	 */
    @Serial
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_msw_groupes")
    @SequenceGenerator(sequenceName = "seq_msw_groupes", allocationSize = 1, name = "seq_msw_groupes")
    @Column(name = "GROUPE_ID")
    private Long id;

    @Column(name = "GROUPE_NOM")
    private String nomGroupe;

}
