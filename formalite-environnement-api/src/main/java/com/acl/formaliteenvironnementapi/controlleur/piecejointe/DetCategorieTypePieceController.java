package com.acl.formaliteenvironnementapi.controlleur.piecejointe;





import com.acl.formaliteenvironnementapi.dto.piecejointe.DetCategorieTypeJointeDto;
import com.acl.formaliteenvironnementapi.services.piecejointe.DetCategorieTypePieceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/detcategorietypepiece")
public class DetCategorieTypePieceController {

    private final DetCategorieTypePieceService detCategorieTypePieceService;

    public DetCategorieTypePieceController(DetCategorieTypePieceService detCategorieTypePieceService) {
        this.detCategorieTypePieceService = detCategorieTypePieceService;
    }

    @CrossOrigin
    @GetMapping("list")
    @Operation(summary = "Liste la listes des types de pièces jointes et categorie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "409", description = "CONFLICT: The object is  exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listTypePiece(){
        return detCategorieTypePieceService.listDetCategorieTypePiece();
    }

    @CrossOrigin
    @PostMapping("insert")
    @Operation(summary = "Insertion des types de pièces jointes et leurs categorie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "409", description = "CONFLICT: The object is  exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> createTypePiece(@RequestBody DetCategorieTypeJointeDto detCategorieTypeJointeDto,
                                             HttpServletRequest httprequest, BindingResult result){
        return detCategorieTypePieceService.createDetCategorieTypePiece(detCategorieTypeJointeDto);
    }

    @GetMapping("check")
        @Operation(summary = "Liste les pieces jointes par formalité")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> getByCategorieTypePieceRef(@RequestParam String ref) {
        return detCategorieTypePieceService.getDetCategorieTypePieceByRef(ref);
    }

}
