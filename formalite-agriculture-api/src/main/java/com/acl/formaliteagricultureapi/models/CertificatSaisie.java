package com.acl.formaliteagricultureapi.models;

import java.io.Serializable;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CERTIFICAT_SAISIE", schema = "USERFMA")
@Getter
@Setter
@NoArgsConstructor
@Schema(implementation = CertificatSaisie.class)
public class CertificatSaisie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_CERTIFICAT_SAISIE")
    @SequenceGenerator(sequenceName = "S_CERTIFICAT_SAISIE", allocationSize = 1, name = "S_CERTIFICAT_SAISIE")
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "DATE_SAISIE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSaisie;
    
    @Column(name = "NOM_PRENOM_INSP")
    private String nomPrenomsInspecteur;
    
    @Column(name="MOTIF_SAISIE")
    private String motifSaisie;
    
    @Column(name="LIEU_SAISIE")
    private String lieuSaisie; 
    
    @Column(name="DEVENIR_DES_PRODUITS")
    private String devenirProduits; 
    
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_autorisation", referencedColumnName = "id")
    private Autorisation autorisation;
    
  
    
    
    
}
