/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acl.iccd.core.entities;

import javax.persistence.*;
import java.io.InputStream;
import java.io.Serializable;

/**
 *
 * @author MJ
 */
@Entity
@Table(name = "PIECE_JOINTE",schema = "SIPEDBA")
@SequenceGenerator(name = "S_PIECE_JOINTE",sequenceName = "S_PIECE_JOINTE", allocationSize = 1)
public class PieceJointe implements Serializable{    
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_PIECE_JOINTE")    
    @Column(name = "ID_PIECE_JOINTE")
    private Long idPieceJointe;
    @Basic(optional = false)
    @Column(name = "NOM_GENERE_PIECE_JOINTE")
    private String nomGenerePieceJointe;
    @Column(name = "NOM_ORIGINE_PIECE_JOINTE")
    private String nomOriginePieceJointe;
    @Basic(optional = false)
    @Column(name = "URL_PJ")
    private String urlPj;
    @JoinColumn(name = "ID_RECLAMATION", referencedColumnName = "ID_RECLAMATION")
    @ManyToOne
    private Reclamation reclamation;    
    @JoinColumn(name = "ID_EXONERATION", referencedColumnName = "ID_EXONERATION")
    @ManyToOne
    private IccdExoneration exoneration;
    
    @JoinColumn(name = "ID_FORMALITE", referencedColumnName = "ID_FORMALITE")
    @ManyToOne
    private IccdFormaliteMinisterielle formaliteMinisterielle;
    @JoinColumn(name = "ID_TYPE_PIECE_JOINTE", referencedColumnName = "ID_TYPE_PIECE_JOINTE")
    @ManyToOne
    private IccdTypePieceJointe iccdTypePieceJointe;

    @Transient
    private InputStream inputStream;
    @Transient
    private Long taille;
    @Transient
    private String type;
    

    public PieceJointe() {
    }

    public PieceJointe(String nomGenerePieceJointe, String nomOriginePieceJointe) {
        this.nomGenerePieceJointe = nomGenerePieceJointe;
        this.nomOriginePieceJointe = nomOriginePieceJointe;
    }

    



    /**
     * @return the nomGenerePieceJointe
     */
    public String getNomGenerePieceJointe() {
        return nomGenerePieceJointe;
    }

    /**
     * @param nomGenerePieceJointe the nomGenerePieceJointe to set
     */
    public void setNomGenerePieceJointe(String nomGenerePieceJointe) {
        this.nomGenerePieceJointe = nomGenerePieceJointe;
    }

    /**
     * @return the nomOriginePieceJointe
     */
    public String getNomOriginePieceJointe() {
        return nomOriginePieceJointe;
    }

    /**
     * @param nomOriginePieceJointe the nomOriginePieceJointe to set
     */
    public void setNomOriginePieceJointe(String nomOriginePieceJointe) {
        this.nomOriginePieceJointe = nomOriginePieceJointe;
    }

    /**
    * @return the exoneration
    */
    public IccdExoneration getExoneration() {
        return exoneration;
    }

    /**
     * @param exoneration the exoneration to set
     */
    public void setExoneration(IccdExoneration exoneration) {
        this.exoneration = exoneration;
    }
    
    /**
     * @return the reclamation
     */
    public Reclamation getReclamation() {
        return reclamation;
    }

    /**
     * @param reclamation the reclamation to set
     */
    public void setReclamation(Reclamation reclamation) {
        this.reclamation = reclamation;
    }

    /**
     * @return the inputStream
     */
    public InputStream getInputStream() {
        return inputStream;
    }

    /**
     * @param inputStream the inputStream to set
     */
    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    /**
     * @return the taille
     */
    public Long getTaille() {
        return taille;
    }

    /**
     * @param taille the taille to set
     */
    public void setTaille(Long taille) {
        this.taille = taille;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the urlPj
     */
    public String getUrlPj() {
        return urlPj;
    }

    /**
     * @param urlPj the urlPj to set
     */
    public void setUrlPj(String urlPj) {
        this.urlPj = urlPj;
    }

    /**
     * @return the formalite ministerielle
     */
    public IccdFormaliteMinisterielle getFormaliteMinisterielle() {
        return formaliteMinisterielle;
    }

    /**
     * @param formaliteMinisterielle the formalite ministerielle to set
     */
    public void setFormaliteMinisterielle(IccdFormaliteMinisterielle formaliteMinisterielle) {
        this.formaliteMinisterielle = formaliteMinisterielle;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPieceJointe != null ? idPieceJointe.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PieceJointe)) {
            return false;
        }
        PieceJointe other = (PieceJointe) object;
        if ((this.idPieceJointe == null && other.idPieceJointe != null) || (this.idPieceJointe != null && !this.idPieceJointe.equals(other.idPieceJointe))) {
            return false;
        }
        return true;
    }


    
}
