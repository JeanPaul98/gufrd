package com.acl.iccd.core.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author kol on 4/26/21
 */
@Entity
@Table(name = "TYPE_PIECE_JOINTE",schema = "SIPEDBA")
@SequenceGenerator(name = "S_TYPE_PIECE_JOINTE",sequenceName = "S_TYPE_PIECE_JOINTE", allocationSize = 1)
public class TypePieceJointe implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_TYPE_PIECE_JOINTE")
    @Column(name = "ID_TYPE_PIECE_JOINTE")
    private Long idTypePieceJointe;
    @Size(max = 254)
    @Column(name = "LIBELLE", nullable = false)
    private String libelle;

    @Size(max = 254)
    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "OBLIGATOIRE")
    private boolean obligatoire;

    @Column(name = "AUTRE")
    private boolean autre;

    @JoinColumn(name = "ID_TYPE_INSPECT_PHYTO", referencedColumnName = "ID_TYPE_INSPECTPHYTO")
    @ManyToOne
    private IccdTypeInspPhyto idTypeInspectPhyto;

    @JoinColumn(name = "ID_TYPE_CERTIFICAT", referencedColumnName = "ID_TYPE_CERTIFICAT")
    @ManyToOne
    private IccdTypeCertificat idtypecertificat;

    public TypePieceJointe() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getIdTypePieceJointe() {
        return idTypePieceJointe;
    }

    public void setIdTypePieceJointe(Long idTypePieceJointe) {
        this.idTypePieceJointe = idTypePieceJointe;
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

    public boolean isObligatoire() {
        return obligatoire;
    }

    public void setObligatoire(boolean obligatoire) {
        this.obligatoire = obligatoire;
    }

    public boolean isAutre() {
        return autre;
    }

    public void setAutre(boolean autre) {
        this.autre = autre;
    }

    public IccdTypeInspPhyto getIdTypeInspectPhyto() {
        return idTypeInspectPhyto;
    }

    public void setIdTypeInspectPhyto(IccdTypeInspPhyto idTypeInspectPhyto) {
        this.idTypeInspectPhyto = idTypeInspectPhyto;
    }

    public IccdTypeCertificat getIdtypecertificat() {
        return idtypecertificat;
    }

    public void setIdtypecertificat(IccdTypeCertificat idtypecertificat) {
        this.idtypecertificat = idtypecertificat;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypePieceJointe that = (TypePieceJointe) o;
        return Objects.equals(idTypePieceJointe, that.idTypePieceJointe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTypePieceJointe);
    }

    @Override
    public String toString() {
        return "TypePieceJointe{" +
                "idTypePieceJointe=" + idTypePieceJointe +
                '}';
    }
}
