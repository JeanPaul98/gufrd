package shared;

import com.acl.iccd.core.entities.IccdCertificat;
import com.acl.iccd.core.entities.IccdProduit;
import com.acl.iccd.core.entities.IccdTypeMarchandise;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author kol on 5/14/21
 */
@Entity
@Table(name = "ICCD_MARCHANDISE", catalog = "", schema = "SIPEDBA")
@XmlRootElement
@SequenceGenerator(name = "S_ICCD_MARCHANDISE",sequenceName = "S_ICCD_MARCHANDISE", allocationSize = 1)
public class IccdMarchandise implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_ICCD_MARCHANDISE")
    @Column(name = "ID_MARCHANDISE")
    private Long idMarchandise;
    @Size(max = 254)
    @Column(name = "LIBELLE")
    private String libelle;
    @Column(name = "QUANTITE")
    private int quantite;
    @Column(name = "POIDS")
    private int poids;
    @Column(name = "NOMBRE")
    private int nombre;
    @Column(name = "PRIX")
    private double prix;

    @JoinColumn(name = "ID_CERTIFICAT", referencedColumnName = "ID_CERTIFICAT")
    @ManyToOne
    private IccdCertificat idCertificat;


    @JoinColumn(name = "ID_PRODUIT", referencedColumnName = "ID_PRODUIT")
    @ManyToOne
    private IccdProduit iccdProduit;

    public IccdMarchandise() {
    }

    public Long getIdMarchandise() {
        return idMarchandise;
    }

    public void setIdMarchandise(Long idMarchandise) {
        this.idMarchandise = idMarchandise;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public IccdCertificat getIdCertificat() {
        return idCertificat;
    }

    public void setIdCertificat(IccdCertificat idCertificat) {
        this.idCertificat = idCertificat;
    }

    public IccdTypeMarchandise getIccdTypeMarchandise() {
        return iccdTypeMarchandise;
    }

    public void setIccdTypeMarchandise(IccdTypeMarchandise iccdTypeMarchandise) {
        this.iccdTypeMarchandise = iccdTypeMarchandise;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public IccdProduit getIccdProduit() {
        return iccdProduit;
    }

    public void setIccdProduit(IccdProduit iccdProduit) {
        this.iccdProduit = iccdProduit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IccdMarchandise that = (IccdMarchandise) o;
        return Objects.equals(idMarchandise, that.idMarchandise);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMarchandise);
    }



    @Override
    public String toString() {
        return "IccdMarchandise{" +
                "idMarchandise=" + idMarchandise +
                ", libelle='" + libelle + '\'' +
                ", quantite=" + quantite +
                ", poids=" + poids +
                '}';
    }
}
