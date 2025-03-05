package shared;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author kol on 5/19/21
 */

@Entity
@Table(name = "ICCD_PRODUIT", schema = "SIPEDBA")
@SequenceGenerator(name = "S_ICCD_PRODUIT", sequenceName = "S_ICCD_PRODUIT", allocationSize = 1)
@XmlRootElement
public class IccdProduit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_ICCD_PRODUIT")
    @Column(name = "ID_PRODUIT")
    private Long idProduit;

    @Size(max = 254)
    @Column(name = "LIBELLE")
    private String libelle;

    @Size(max = 254)
    @Column(name = "DESCRIPTION")
    private String description;

    @JoinColumn(name = "ID_TYPE_MARCHANDISE", referencedColumnName = "ID_TYPE_MARCHANDISE")
    @ManyToOne
    private IccdTypeMarchandise iccdTypeMarchandise;

    @JoinColumn(name = "ID_STRUCTURE", referencedColumnName = "ID_STRUCTURE")
    @ManyToOne
    private IccdStructure iccdStructure;



    public IccdProduit() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IccdProduit that = (IccdProduit) o;
        return Objects.equals(idProduit, that.idProduit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProduit);
    }

    public Long getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Long idProduit) {
        this.idProduit = idProduit;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public IccdTypeMarchandise getIccdTypeMarchandise() {
        return iccdTypeMarchandise;
    }

    public void setIccdTypeMarchandise(IccdTypeMarchandise iccdTypeMarchandise) {
        this.iccdTypeMarchandise = iccdTypeMarchandise;
    }

    public IccdStructure getIccdStructure() {
        return iccdStructure;
    }

    public void setIccdStructure(IccdStructure iccdStructure) {
        this.iccdStructure = iccdStructure;
    }

    @Override
    public String toString() {
        return "IccdProduit{" +
                "idProduit=" + idProduit +
                ", libelle='" + libelle + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
