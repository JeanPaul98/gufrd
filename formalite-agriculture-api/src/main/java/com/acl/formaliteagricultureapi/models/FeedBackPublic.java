package com.acl.formaliteagricultureapi.models;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author kol on 10/6/24
 * @project formalite-agriculture-api
 */
@Entity
@Table(name = "FEEDBACK_SRV_PUBLIC", schema = "USERFMA")
@Getter
@Setter
@NoArgsConstructor
@Schema(implementation = FeedBackPublic.class)
public class FeedBackPublic implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_FEEDBACK_SRV_PUBLIC")
    @SequenceGenerator(sequenceName = "S_FEEDBACK_SRV_PUBLIC", allocationSize = 1, name = "S_FEEDBACK_SRV_PUBLIC")
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


     @Temporal(TemporalType.TIMESTAMP)
     @Column(name = "CREATED_AT")
     private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_AT")
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "ID_FORMALITE")
    private Formalite formalite;


}
