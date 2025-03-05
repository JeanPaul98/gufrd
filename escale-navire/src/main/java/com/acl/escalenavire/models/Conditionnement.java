package com.acl.escalenavire.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "CONDITIONNEMENT")
public class Conditionnement {
    @Id
    @Size(max = 5)
    @Column(name = "CODE_CONDITION", nullable = false, length = 5)
    private String codeCondition;

    @Size(max = 60)
    @NotNull
    @Column(name = "LIB_CONDITION", nullable = false, length = 60)
    private String libCondition;

}