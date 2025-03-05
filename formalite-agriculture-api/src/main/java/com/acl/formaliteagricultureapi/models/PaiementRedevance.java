package com.acl.formaliteagricultureapi.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "PAIEMENT_REDEVANCE", schema = "USERFMA")
@Getter
@Setter
@NoArgsConstructor
@Schema(implementation = PaiementRedevance.class)
public class PaiementRedevance implements Serializable {
	
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_PAIEMENT_REDEVANCE")
    @SequenceGenerator(sequenceName = "S_PAIEMENT_REDEVANCE", allocationSize = 1, name = "S_PAIEMENT_REDEVANCE")
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "MONTANT_PAYE")    
    private double montantPaye;
    
    @Size(max = 254)
    @Column(name = "REF_TRANSACTION")
    private String refTransaction;
    
    @Column(name = "DATE_PAIEMENT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datePaiement;
    
    @Column(name = "DATE_ENREG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEnreg;
    
    @JoinColumn(name = "ID_FORMALITE", referencedColumnName = "ID")
    @ManyToOne
    private Formalite formalite;
    
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaiementRedevance that = (PaiementRedevance) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
