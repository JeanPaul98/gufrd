package com.acl.vbs.fret.requests;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

@Data
public class PieceJustificativeRequest implements Serializable {

    @NotNull(message = "L'identifiant de la déclaration de fret est obligatoire")
    private Long declarationFret;

    @NotNull(message = "La liste des pièces est obligatoire")
    @NotBlank(message = "La liste des pièces ne peut pas etre vide")
    private String filenameTypePieceJointe;

    @NotNull(message = "Les fichiers sont obligatoires")
    @NotEmpty(message = "La liste des fichiers ne peut pas être vide")
    private MultipartFile[] file;

    @Data
    public static class PieceRequest implements Serializable {
        @NotNull(message = "Le nom du fichier est obligatoire")
        private String filename;

        @NotNull(message = "L'identifiant du type de pièce jointe est obligatoire")
        private Long idTypePieceJointe;
    }
}

