/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

import com.acl.iccd.core.entities.IccdAutorisation;
import com.acl.iccd.core.entities.IccdProduit;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Matthias
 */
@Entity
@Table(name = "ICCD_DET_AUTORISATION")
@SequenceGenerator(name = "S_ICCD_DET_AUTORISATION", sequenceName = "S_ICCD_DET_AUTORISATION", allocationSize = 1)
@XmlRootElement
public class IccdDetAutorisation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_ICCD_DET_AUTORISATION")
    @Column(name = "ID_DET_AUTORISATION")
    private Long idDetAutorisation;

    @JoinColumn(name = "ID_AUTORISATION", referencedColumnName = "ID_AUTORISATION")
    @ManyToOne
    private IccdAutorisation autorisation;

    @Size(max = 254)
    @Column(name = "DESIGNATION")
    private String product;

    @Column(name = "QUANTITE")
    private int quantity;

    @JoinColumn(name = "ID_PRODUIT", referencedColumnName = "ID_PRODUIT")
    @ManyToOne
    private com.acl.iccd.core.entities.IccdProduit iccdProduit;

    public IccdDetAutorisation() {
    }

    public IccdDetAutorisation(Long idDetAutorisation) {
        this.idDetAutorisation = idDetAutorisation;
    }

    public Long getIdDetAutorisation() {
        return idDetAutorisation;
    }

    public void setIdDetAutorisation(Long idDetAutorisation) {
        this.idDetAutorisation = idDetAutorisation;
    }

    public IccdAutorisation getAutorisation() {
        return autorisation;
    }

    public void setAutorisation(IccdAutorisation autorisation) {
        this.autorisation = autorisation;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public com.acl.iccd.core.entities.IccdProduit getIccdProduit() {
        return iccdProduit;
    }

    public void setIccdProduit(IccdProduit iccdProduit) {
        this.iccdProduit = iccdProduit;
    }
    
    
}
