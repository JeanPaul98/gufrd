package com.acl.mswauth.model;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "ports", schema = "USERMSW")
/*
@ApiModel(value = "MswPorts")
*/
public class MswPort implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "PORT_CODE")
    private String code;

    @Column(name = "PORT_NOM")
    private String nom;

    @Column(name = "PORT_LOCODE")
    private String locode;

    @ManyToOne
    @JoinColumn(name = "PAYS_CODE")
    private MswPays mswPays;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MswPort mswPort = (MswPort) o;
        return Objects.equals(code, mswPort.code);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(code);
    }
}
