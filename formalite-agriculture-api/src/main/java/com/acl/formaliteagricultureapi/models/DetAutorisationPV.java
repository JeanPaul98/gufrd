package com.acl.formaliteagricultureapi.models;

import java.io.Serializable;
import java.util.Date;

import com.acl.formaliteagricultureapi.models.enumeration.Chaine;
import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import com.acl.formaliteagricultureapi.models.enumeration.NatureAutorisation;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Objects;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "DET_AUTORISATION_PV", schema = "USERFMA")
@Schema(implementation = DetAutorisationPV.class)
public class DetAutorisationPV implements Serializable {
	
	private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_DET_AUTORISATION_PV")
    @SequenceGenerator(sequenceName = "S_DET_AUTORISATION_PV", allocationSize = 1, name = "S_DET_AUTORISATION_PV")
    @Column(name = "ID")
    private Long id;
	
    @Size(max = 254)
    @Column(name = "IMPORTATEUR")
    private String importateur;
           
    @Size(max = 254)
    @Column(name = "NUM_SG_DE")
    private String numSgde;
    
    @Size(max = 254)
    @Column(name = "NUMERO_ENREGISTREMENT")
    private String numeroEnregistrement;    
       
    @Size(max = 254)
    @Column(name = "NOM_SIGNATAIRE")
    private String nomSignataire;
    
    @Size(max = 254)
    @Column(name = "PRENOM_SIGNATAIRE")
    private String prenomSignataire;
    
    @Column(name = "DATE_ENREGISTREMENT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEnregistrement;
    
    @Column(name = "DATE_OPERATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOperation;
    
    @Enumerated(EnumType.STRING)
	private NatureAutorisation natureAutorisation;
    
    @ManyToOne
    @JoinColumn(name = "ID_AUTORISATION", referencedColumnName = "ID")
    private Autorisation autorisation;
	
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetAutorisationPV detAutorisationPV = (DetAutorisationPV) o;
        return Objects.equals(id, detAutorisationPV.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
