package com.acl.mswauth.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "formalite_appli", schema = "USERMSW")
public class MswFormaliteApplication implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SEQ_MSW_FORMALITE_APPLI")
    @SequenceGenerator(sequenceName = "SEQ_MSW_FORMALITE_APPLI", allocationSize = 1, name = "SEQ_MSW_FORMALITE_APPLI")
    @Column(name = "ID")
    private Long id;

    @Column(name = "FORMALITE_NOM",unique = true)
    private String name;

    @Column(name = "FORMALITE_DESCRIPTION")
    private String description;

    @Column(name = "FORMALITE_URL")
    private String url;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FORMALITE_ADD_DATE")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FORMALITE_UPDATE_DATE")
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "APPLI_ID")
    private MswApplication mswApplication;

    public MswFormaliteApplication(String name, String description, String url,
                                   MswApplication mswApplication) {

        this.name = name;
        this.description = description;
        this.url = url;
        this.mswApplication = mswApplication;
    }
}
