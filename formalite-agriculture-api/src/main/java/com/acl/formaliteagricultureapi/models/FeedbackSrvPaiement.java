package com.acl.formaliteagricultureapi.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author kol on 10/7/24
 * @project formalite-agriculture-api
 */
@Entity
@Table(name = "FEEDBACK_SRV_PAIEMENT", schema = "USERFMA")
@Getter
@Setter
@NoArgsConstructor
@Schema(implementation = FeedbackSrvPaiement.class)
public class FeedbackSrvPaiement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_FEEDBACK_SRV_PAIEMENT")
    @SequenceGenerator(sequenceName = "S_FEEDBACK_SRV_PAIEMENT", allocationSize = 1, name = "S_FEEDBACK_SRV_PAIEMENT")
    @Column(name = "ID")
    private Long id;

    @Column(name = "RECORD")
    private String record;

    @Column(name = "PROCESS")
    private String process;

    @Column(name = "STEP")
    private String step;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "FB_ORDER")
    private long order;

    @Column(name = "FEEDBACKTASKID")
    private String feedbackTaskId;


    @Column(name = "TRANSACTION_ID")
    private String transactionId;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_AT")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_AT")
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "ID_FORMALITE")
    private Formalite formalite;


}
