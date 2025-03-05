/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shared;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author A.Jules <ahehehinnou@gmail.com>
 * @version 1.0
 */
@Entity
@Table(name = "ICCD_MS_DECL_SANTE_REP", catalog = "", schema = "SIPEDBA")
public class IccdMsDeclSanteQuest implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id    
    @Column(name = "ID_QUESTION")
    private Long idQuestion;
    @Column(name = "LIBELLE")
    private String libelle;
    @Column(name = "LANGUE")
    private String langue;

    public IccdMsDeclSanteQuest() {
    }

    public Long getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(Long idQuestion) {
        this.idQuestion = idQuestion;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.idQuestion);
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
        final IccdMsDeclSanteQuest other = (IccdMsDeclSanteQuest) obj;
        if (!Objects.equals(this.idQuestion, other.idQuestion)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "IccdMsDeclSanteQuest{" + "idQuestion=" + idQuestion + ", libelle=" + libelle + ", langue=" + langue + '}';
    }
    
    
    
    

}
