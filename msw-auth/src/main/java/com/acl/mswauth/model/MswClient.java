package com.acl.mswauth.model;

import java.io.Serial;
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
@Table(name = "clients", schema = "USERMSW")
/*
@ApiModel(value = "MswClient")
*/
public class MswClient implements Serializable {
    
	/**
	 * 
	 */
    @Serial
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_msw_clients")
    @SequenceGenerator(sequenceName = "seq_msw_clients", allocationSize = 1, name = "seq_msw_clients")
    @Column(name = "CLIENT_ID")
    private Long id;

    @Column(name = "CLIENT_NOM")
    private String nomClient;

    @Column(name = "CLIENT_COMPTE")
    private String compteClient;

    @ManyToOne
    @JoinColumn(name = "PAYS_CODE")
    private MswPays mswPays;

    @Column(name = "NIF_CLIENT")
    private String nif ;

    @Column(name = "NOM_RESPONSABLE")
    private String nomResponsable;

    @Column(name = "TEL_RESPONSABLE")
    private String telResponsable;

    @Column(name = "EMAIL_RESPONSABLE")
    private String emailResponsable;

    @Column(name = "CLIENT_ADRESSE")
    private String clientAdresse;

    @Column(name = "CLIENT_EMAIL")
    private String clientEmail;


}
