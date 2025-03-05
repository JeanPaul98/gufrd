package com.acl.escalenavire.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "CONCESSIONNAIRE")
public class Concessionnaire {
    @Id
    @Column(name = "ID_CLIENT", nullable = false)
    private Long id;

    @Size(max = 15)
    @Column(name = "CODE_CONCESSIONNAIRE", length = 15)
    private String codeConcessionnaire;

}