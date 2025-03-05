/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shared;

import com.acl.iccd.core.entities.IccdMsDecMaritimeSante;
import com.acl.iccd.core.entities.IccdMsDeclSanteQuest;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
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

/**
 *
 * @author A.Jules <ahehehinnou@gmail.com>
 * @version 1.0
 */
@Entity
@Table(name = "ICCD_MS_DECL_SANTE_REP", catalog = "", schema = "SIPEDBA")
@SequenceGenerator(name = "S_ICCD_MS_DECL_SANTE_REP",sequenceName = "S_ICCD_MS_DECL_SANTE_REP", allocationSize = 1)
public class IccdMsDeclSanteRep implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_ICCD_MS_DECL_SANTE_REP")
    @Column(name = "ID_REPONSE")
    private Long idReponse;
    @Column(name = "VAL_REPONSE")
    private String valReponse; 
    @Column(name = "TYPE_MESURE")
    private String typeMesure; 
    @Column(name = "NBRE_TOTAL_MORT")
    private int nbreTotalMort;
    @Column(name = "NBRE_MALADE")
    private int nbreMalade;
    @Column(name = "DATE_MESURE")
    @Temporal(TemporalType.DATE)
    private Date dateMesure;
    @JoinColumn(name = "ID_DEC_SANTE", referencedColumnName = "ID_DEC_SANTE")
    @ManyToOne
    private IccdMsDecMaritimeSante declaration;
    @JoinColumn(name = "ID_QUESTION", referencedColumnName = "ID_QUESTION")
    @ManyToOne
    private com.acl.iccd.core.entities.IccdMsDeclSanteQuest question;

    public IccdMsDeclSanteRep() {
    
    }

    public Long getIdReponse() {
        return idReponse;
    }

    public void setIdReponse(Long idReponse) {
        this.idReponse = idReponse;
    }

    public String getValReponse() {
        return valReponse;
    }

    public void setValReponse(String valReponse) {
        this.valReponse = valReponse;
    }

    public String getTypeMesure() {
        return typeMesure;
    }

    public void setTypeMesure(String typeMesure) {
        this.typeMesure = typeMesure;
    }

    public int getNbreTotalMort() {
        return nbreTotalMort;
    }

    public void setNbreTotalMort(int nbreTotalMort) {
        this.nbreTotalMort = nbreTotalMort;
    }

    public int getNbreMalade() {
        return nbreMalade;
    }

    public void setNbreMalade(int nbreMalade) {
        this.nbreMalade = nbreMalade;
    }

    public Date getDateMesure() {
        return dateMesure;
    }

    public void setDateMesure(Date dateMesure) {
        this.dateMesure = dateMesure;
    }

    /**
     * @return the declaration
     */
    public IccdMsDecMaritimeSante getDeclaration() {
        return declaration;
    }

    /**
     * @param declaration the declaration to set
     */
    public void setDeclaration(IccdMsDecMaritimeSante declaration) {
        this.declaration = declaration;
    }

    /**
     * @return the question
     */
    public com.acl.iccd.core.entities.IccdMsDeclSanteQuest getQuestion() {
        return question;
    }

    /**
     * @param question the question to set
     */
    public void setQuestion(IccdMsDeclSanteQuest question) {
        this.question = question;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.idReponse);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final IccdMsDeclSanteRep other = (IccdMsDeclSanteRep) obj;
        if (!Objects.equals(this.idReponse, other.idReponse)) {
            return false;
        }
        return true;
    }
    
    
    
    

}
