package com.acl.mswauth.model;


import java.io.Serializable;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "pays", schema = "USERMSW")
/*
@ApiModel(value = "MswPays")
*/
public class MswPays implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
    @Column(name = "PAYS_CODE", unique = true)
    private String code;


    @Column(name = "PAYS_NOM", unique = true)
    private String nom;
}
