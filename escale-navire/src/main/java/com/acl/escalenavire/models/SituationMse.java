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
@Table(name = "SITUATION_MSE")
public class SituationMse {
    @Id
    @Size(max = 15)
    @Column(name = "CODE_SITU_MSE", nullable = false, length = 15)
    private String codeSituMse;

    @Size(max = 100)
    @NotNull
    @Column(name = "LIB_SITU_MSE", nullable = false, length = 100)
    private String libSituMse;

}