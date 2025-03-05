package com.acl.vbs.fret.services.impls;

import com.acl.vbs.fret.converters.AppConverter;
import com.acl.vbs.fret.entities.TypePiece;
import com.acl.vbs.fret.exceptions.TypePieceNotFoundException;
import com.acl.vbs.fret.repositories.TypePieceRepository;
import com.acl.vbs.fret.requests.TypePieceRequest;
import com.acl.vbs.fret.responses.MSWUserResponse;
import com.acl.vbs.fret.responses.TypePieceResponse;
import com.acl.vbs.fret.services.AuthenticationService;
import com.acl.vbs.fret.services.TypePieceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.acl.vbs.fret.converters.AppConverter.toTypePieceResponse;

@Service
@RequiredArgsConstructor
public class TypePieceServiceImpl implements TypePieceService {

    private final TypePieceRepository typePieceRepository;
    private final AuthenticationService authenticationService;

    @Override
    public TypePieceResponse create(TypePieceRequest request) {
        MSWUserResponse user = authenticationService.getAuthInfo();
        TypePiece typePiece = new TypePiece();

        BeanUtils.copyProperties(request, typePiece);
        typePiece.setCreatedBy(user.getFullname());
        TypePiece saved = typePieceRepository.save(typePiece);

        return toTypePieceResponse(saved);
    }

    public Object deleteTypePiece(Long id) {
         TypePiece typePiece = typePieceRepository.findById(id)
                .orElseThrow(() -> new TypePieceNotFoundException("TypePiece with id " + id + " not found"));
         typePieceRepository.delete(typePiece);
         return new ObjectMapper();
    }

    @Override
    public List<TypePieceResponse> list() {
        return typePieceRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"))
                .stream().map(AppConverter::toTypePieceResponse).toList();
    }

}
