package com.acl.vbs.fret.services.impls;

import com.acl.vbs.fret.converters.AppConverter;
import com.acl.vbs.fret.entities.DmdDeclarationFret;
import com.acl.vbs.fret.entities.PieceJustificative;
import com.acl.vbs.fret.entities.TypePiece;
import com.acl.vbs.fret.exceptions.DmdDeclaratrionFretNotFoundException;
import com.acl.vbs.fret.exceptions.TypePieceNotFoundException;
import com.acl.vbs.fret.exceptions.UnauthorizedAccessException;
import com.acl.vbs.fret.repositories.DmdDeclarationFretRepository;
import com.acl.vbs.fret.repositories.PieceJustificativeRepository;
import com.acl.vbs.fret.repositories.TypePieceRepository;
import com.acl.vbs.fret.requests.PieceJustificativeRequest;
import com.acl.vbs.fret.requests.PieceJustificativeRequest.PieceRequest;
import com.acl.vbs.fret.responses.MSWUserResponse;
import com.acl.vbs.fret.responses.PieceJustificativeResponse;
import com.acl.vbs.fret.services.AuthenticationService;
import com.acl.vbs.fret.services.PieceJustificativeService;
import com.acl.vbs.fret.services.UploadService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;


@Slf4j
@Service
@RequiredArgsConstructor
public class PieceJustificativeServiceImpl implements PieceJustificativeService {

    private final UploadService uploadService;
    private final TypePieceRepository typePieceRepository;
    private final PieceJustificativeRepository repository;
    private final AuthenticationService authenticationService;
    private final DmdDeclarationFretRepository dmdDeclarationFretRepository;

    @Override
    public List<PieceJustificativeResponse> create(PieceJustificativeRequest request) {
        // Fetch the authenticated user info
        MSWUserResponse user = authenticationService.getAuthInfo();

        // Check user group for authorization
        if (!"TRANSITAIRES".equals(user.getGroupe())) {
            throw new UnauthorizedAccessException("Accès non autorisé");
        }

        List<PieceRequest> pieceRequests = convertStringToList(request.getFilenameTypePieceJointe());

        // Ensure the number of files matches the number of pieces
        if (request.getFile().length != pieceRequests.size()) {
            throw new IllegalArgumentException("Le nombre de fichiers ne correspond pas au nombre de pièces justificatives");
        }

        // Process each piece and map to entities
        List<PieceJustificative> pieceJustificatives = IntStream.range(0, pieceRequests.size())
                .mapToObj(index -> {
                    PieceRequest pieceRequest = pieceRequests.get(index);

                    // Create and populate the PieceJustificative entity
                    PieceJustificative pieceJustificative = new PieceJustificative();

                    // Fetch related DmdDeclarationFret
                    DmdDeclarationFret declarationFret = getDeclarationFret(request.getDeclarationFret());
                    pieceJustificative.setDeclarationFret(declarationFret);

                    // Fetch related TypePiece
                    TypePiece typePiece = getTypePiece(pieceRequest.getIdTypePieceJointe());
                    pieceJustificative.setTypePiece(typePiece);

                    // Copy other properties from request
                    BeanUtils.copyProperties(request, pieceJustificative);

                    // Save the file locally
                    String uploadedFilePath = uploadService.saveLocalFile(
                            request.getFile()[index],
                            "piece_" + UUID.randomUUID(),
                            "pieces-justificatives/"
                    );
                    pieceJustificative.setFichier(uploadedFilePath);

                    // Set metadata
                    pieceJustificative.setLibelle(pieceRequest.getFilename());
                    pieceJustificative.setCreatedBy(user.getFullname());

                    return pieceJustificative;
                })
                .toList();

        // Save all entities and convert to responses
        List<PieceJustificative> savedEntities = repository.saveAll(pieceJustificatives);
        return savedEntities.stream().map(AppConverter::toPieceJustificativeResponse).toList();
    }

    @Override
    public List<PieceJustificativeResponse> listPieceJustificative(Long idDeclarationFret) {
        return repository.findAllByDeclarationFretId(idDeclarationFret).stream()
                .map(AppConverter::toPieceJustificativeResponse).toList();
    }

    private TypePiece getTypePiece(Long id) {
        return typePieceRepository.findById(id)
                .orElseThrow(() -> new TypePieceNotFoundException("TypePiece with id " + id + " not found"));
    }

    private DmdDeclarationFret getDeclarationFret(Long id) {
        return dmdDeclarationFretRepository.findById(id)
                .orElseThrow(() -> new DmdDeclaratrionFretNotFoundException("DeclarationFret with id " + id + " not found"));
    }

    public List<PieceRequest> convertStringToList(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Convertir le JSON en List<PieceRequest>
            return objectMapper.readValue(jsonString, new TypeReference<>() {
            });
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la conversion du JSON en liste : " + e.getMessage(), e);
        }
    }
}

