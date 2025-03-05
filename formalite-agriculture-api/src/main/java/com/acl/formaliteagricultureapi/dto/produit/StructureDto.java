package com.acl.formaliteagricultureapi.dto.produit;

import com.acl.formaliteagricultureapi.models.enumeration.TypeStructure;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * DTO for {@link com.acl.formaliteagricultureapi.models.Structure}
 */
@Getter
@Setter
public class StructureDto implements Serializable {
    private Long id;
    private String code;
    private String libelle;
    private TypeStructure typeStructure;
    private long produitId;
}