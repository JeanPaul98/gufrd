/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

import com.acl.iccd.core.entities.IccdAutorisation;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Matthias
 */

@Entity
@Table(name = "ICCD_AUTORISATION_PARAMETRE", catalog = "", schema = "SIPEDBA")
@SequenceGenerator(name = "S_ICCD_AUTORISATION_PARAMETRE",sequenceName = "S_ICCD_AUTORISATION_PARAMETRE", allocationSize = 1)
public class IccdAutorisationParametres implements Serializable  {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_ICCD_AUTORISATION_PARAMETRE")
    @Column(name = "ID_AUTORISATION_PARAMETRE")
    private Long idAutorisationParametre;
    
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
    @Column(name = "TYPE_AUTORISATION")
    private String typeAutorisation;
    
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
    
    @ManyToOne
    @JoinColumn(name = "ID_AUTORISATION", referencedColumnName = "ID_AUTORISATION")
    private IccdAutorisation iccdAutorisation;
    
    public IccdAutorisationParametres() {
    }

    public IccdAutorisationParametres(Long idAutorisationParametres) {
        this.idAutorisationParametre = idAutorisationParametres;
    }

    public Long getIdAutorisationParametre() {
        return idAutorisationParametre;
    }

    public void setIdAutorisationParametre(Long idAutorisationParametre) {
        this.idAutorisationParametre = idAutorisationParametre;
    }

    public String getNumSgde() {
        return numSgde;
    }

    public void setNumSgde(String numSgde) {
        this.numSgde = numSgde;
    }

    public String getNumeroEnregistrement() {
        return numeroEnregistrement;
    }

    public void setNumeroEnregistrement(String numeroEnregistrement) {
        this.numeroEnregistrement = numeroEnregistrement;
    }

    public String getNomSignataire() {
        return nomSignataire;
    }

    public void setNomSignataire(String nomSignataire) {
        this.nomSignataire = nomSignataire;
    }

    public String getPrenomSignataire() {
        return prenomSignataire;
    }

    public void setPrenomSignataire(String prenomSignataire) {
        this.prenomSignataire = prenomSignataire;
    }

    public IccdAutorisation getIccdAutorisation() {
        return iccdAutorisation;
    }

    public void setIccdAutorisation(IccdAutorisation iccdAutorisation) {
        this.iccdAutorisation = iccdAutorisation;
    }

    public String getImportateur() {
        return importateur;
    }

    public void setImportateur(String importateur) {
        this.importateur = importateur;
    }

    public Date getDateEnregistrement() {
        return dateEnregistrement;
    }

    public void setDateEnregistrement(Date dateEnregistrement) {
        this.dateEnregistrement = dateEnregistrement;
    }

    public Date getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(Date dateOperation) {
        this.dateOperation = dateOperation;
    }

    public String getTypeAutorisation() {
        return typeAutorisation;
    }

    public void setTypeAutorisation(String typeAutorisation) {
        this.typeAutorisation = typeAutorisation;
    }
}
