/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

import com.acl.iccd.core.entities.IccdFormaliteMinisterielle;
import com.acl.iccd.core.entities.Navire;
import com.acl.iccd.core.entities.Port;
import com.acl.iccd.core.entities.Quai;

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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pythagoras
 */
@Entity
@Table(name = "ICCD_ME_DECL_DECHETS", catalog = "", schema = "SIPEDBA")
@XmlRootElement
@SequenceGenerator(name = "S_ICCD_ME_DECL_DECHETS",sequenceName = "S_ICCD_ME_DECL_DECHETS", allocationSize = 1)
public class IccdMeDecDechets implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Column(name = "ID_DECL")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_ICCD_ME_DECL_DECHETS")
    private Long idDecl;
    
    @JoinColumn(name = "ID_NAVIRE", referencedColumnName = "ID_NAVIRE")
    @ManyToOne
    private Navire navire;
    
    @Size(max = 254)
    @Column(name = "NUM_ATP")
    private String numAtp;
    
    @Size(max = 254)
    @Column(name = "NUM_GENERE")
    private String numGenere;   
    
    @Size(max = 254)
    @Column(name = "CONSIGNATAIRE")
    private String consignataire;
    
    @JoinColumn(name = "CODE_DERNIER_PORT", referencedColumnName = "CODE_PORT")
    @ManyToOne
    private Port codeDernierPort;
    
    @JoinColumn(name = "CODE_PROCHAIN_PORT", referencedColumnName = "CODE_PORT")
    @ManyToOne
    private Port codeProchainPort;
    
    @Column(name = "DEPART")
    @Temporal(TemporalType.TIMESTAMP)
    private Date depart;   
    
    @JoinColumn(name = "CODE_QUAI", referencedColumnName = "CODE_QUAI")
    @ManyToOne
    private Quai quai;
    
    @Column(name = "DERNIER_DECHARGMT_SLUDE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dernierDechargmntSlude;
    
    @Column(name = "DERNIER_DECHARGMT_GARBAGE")
     @Temporal(TemporalType.TIMESTAMP)
    private Date dernierDechargmntGarbage;
        
    @Column(name = "EXPIRATION_CERTIFICAT_IOPP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationCertificatIopp;
    
    @Column(name = "ATTENTE_LIVRAISON")
    private int attenteLivraison;
    
    @Size(max = 254)
    @Column(name = "TRANSPORTEUR_AVITAILLEUR")
    private String transporteuravitailleur;
    
    @JoinColumn(name = "ID_FORMALITE", referencedColumnName = "ID_FORMALITE")
    @ManyToOne
    private IccdFormaliteMinisterielle formalite;

    public IccdMeDecDechets() {
    }

    public Long getIdDecl() {
        return idDecl;
    }

    public void setIdDecl(Long idDecl) {
        this.idDecl = idDecl;
    }

    public Navire getNavire() {
        return navire;
    }

    public void setNavire(Navire navire) {
        this.navire = navire;
    }

    public String getNumAtp() {
        return numAtp;
    }

    public void setNumAtp(String numAtp) {
        this.numAtp = numAtp;
    }

    public String getConsignataire() {
        return consignataire;
    }

    public void setConsignataire(String consignataire) {
        this.consignataire = consignataire;
    }

    
    public Port getCodeDernierPort() {
        return codeDernierPort;
    }

    public void setCodeDernierPort(Port codeDernierPort) {
        this.codeDernierPort = codeDernierPort;
    }

    public Port getCodeProchainPort() {
        return codeProchainPort;
    }

    public void setCodeProchainPort(Port codeProchainPort) {
        this.codeProchainPort = codeProchainPort;
    }

    public Date getDepart() {
        return depart;
    }

    public void setDepart(Date depart) {
        this.depart = depart;
    }

    public Quai getQuai() {
        return quai;
    }

    public void setQuai(Quai quai) {
        this.quai = quai;
    }

    public String getNumGenere() {
        return numGenere;
    }

    public void setNumGenere(String numGenere) {
        this.numGenere = numGenere;
    }

    public Date getDernierDechargmntSlude() {
        return dernierDechargmntSlude;
    }

    public void setDernierDechargmntSlude(Date dernierDechargmntSlude) {
        this.dernierDechargmntSlude = dernierDechargmntSlude;
    }

    public Date getDernierDechargmntGarbage() {
        return dernierDechargmntGarbage;
    }

    public void setDernierDechargmntGarbage(Date dernierDechargmntGarbage) {
        this.dernierDechargmntGarbage = dernierDechargmntGarbage;
    }
    
    public Date getExpirationCertificatIopp() {
        return expirationCertificatIopp;
    }

    public void setExpirationCertificatIopp(Date expirationCertificatIopp) {
        this.expirationCertificatIopp = expirationCertificatIopp;
    }

    public int getAttenteLivraison() {
        return attenteLivraison;
    }

    public void setAttenteLivraison(int attenteLivraison) {
        this.attenteLivraison = attenteLivraison;
    }

    public String getTransporteuravitailleur() {
        return transporteuravitailleur;
    }

    public void setTransporteuravitailleur(String transporteuravitailleur) {
        this.transporteuravitailleur = transporteuravitailleur;
    }

    public IccdFormaliteMinisterielle getFormalite() {
        return formalite;
    }

    public void setFormalite(IccdFormaliteMinisterielle formalite) {
        this.formalite = formalite;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.idDecl);
        hash = 83 * hash + Objects.hashCode(this.formalite);
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
        final IccdMeDecDechets other = (IccdMeDecDechets) obj;
        if (!Objects.equals(this.idDecl, other.idDecl)) {
            return false;
        }
        if (!Objects.equals(this.formalite, other.formalite)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "IccdMeDmdDecDechets{" + "idDecl=" + idDecl + ", navire=" + navire + ", numAtp=" + numAtp + ", depart=" + depart + ", formalite=" + formalite + '}';
    }
    
}
