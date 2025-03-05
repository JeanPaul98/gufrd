package shared;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author kol on 5/14/21
 */
@Entity
@Table(name = "ICCD_TYPE_MARCHANDISE", catalog = "", schema = "SIPEDBA")
@XmlRootElement
@SequenceGenerator(name = "S_ICCD_TYPE_MARCHANDISE",sequenceName = "S_ICCD_TYPE_MARCHANDISE", allocationSize = 1)
public class IccdTypeMarchandise implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_ICCD_TYPE_MARCHANDISE")
    @Column(name = "ID_TYPE_MARCHANDISE")
    private Long idTypeMarchandise;
    @Size(max = 254)
    @Column(name = "LIBELLE")
    private String libelle;


    public IccdTypeMarchandise() {
    }

    public Long getIdTypeMarchandise() {
        return idTypeMarchandise;
    }

    public void setIdTypeMarchandise(Long idTypeMarchandise) {
        this.idTypeMarchandise = idTypeMarchandise;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IccdTypeMarchandise that = (IccdTypeMarchandise) o;
        return Objects.equals(idTypeMarchandise, that.idTypeMarchandise);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTypeMarchandise);
    }

    @Override
    public String toString() {
        return "IccdTypeMarchandise{" +
                "idTypeMarchandise=" + idTypeMarchandise +
                ", libelle='" + libelle + '\'' +
                '}';
    }
}
