package com.acl.mswauth.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "structures", schema = "USERMSW")
public class MswStructure implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_msw_structures")
    @SequenceGenerator(sequenceName = "seq_msw_structures", allocationSize = 1, name = "seq_msw_structures")
    @Column(name = "STRUCTURE_ID")
    private Long id;

    @Column(name = "STRUCTURE_NOM")
    private String nom;

    @Column(name = "STRUCTURE_ADRESSE")
    private String adresse;
    
    @Column(name = "STRUCTURE_EMAIL")
    private String email;
    
    @Column(name = "STRUCTURE_SITE_WEB")
    private String siteWeb;
    
    @Column(name = "STRUCTURE_TELEPHONE")
    private String telephone;

    @ManyToOne
    @JoinColumn(name = "PAYS_CODE")
    private MswPays mswPays;
    
    @ManyToOne
    @JoinColumn(name = "STRUCTURE_PARENTE_ID",referencedColumnName = "STRUCTURE_ID")
    private MswStructure mswStructure;

}
