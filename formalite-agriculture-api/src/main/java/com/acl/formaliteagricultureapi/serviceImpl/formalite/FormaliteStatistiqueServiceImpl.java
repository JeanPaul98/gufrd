package com.acl.formaliteagricultureapi.serviceImpl.formalite;

import com.acl.formaliteagricultureapi.dto.formalite.FormaliteStatistiqueDto;
import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import com.acl.formaliteagricultureapi.playload.ApiResponseMessage;
import com.acl.formaliteagricultureapi.playload.ApiResponseModel;
import com.acl.formaliteagricultureapi.repository.FormaliteRepository;
import com.acl.formaliteagricultureapi.requete.FormaliteStatistiqueInterface;
import com.acl.formaliteagricultureapi.services.formalite.FormaliteStatistiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author kol on 10/21/24
 * @project formalite-agriculture-api
 */
@Service
public class FormaliteStatistiqueServiceImpl implements FormaliteStatistiqueService {

private final  FormaliteRepository formaliteRepository;

@Autowired
private Environment env;

    public FormaliteStatistiqueServiceImpl(FormaliteRepository formaliteRepository) {
        this.formaliteRepository = formaliteRepository;
    }

    @Override
    public ResponseEntity<?> getAllFormaliteStatistiquePhyto() {
        List<FormaliteStatistiqueInterface> listDemandesPhyto = formaliteRepository.findAllStatistiqueByPhyto(Etat.TRAITER.name());
        List<FormaliteStatistiqueDto> statistiqueDtos = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

        listDemandesPhyto.forEach(data -> {
            FormaliteStatistiqueDto dto = new FormaliteStatistiqueDto(data.getEntreprise(), data.getNomStructure(),
                    data.getNavire(), data.getAffreteur(), data.getDateDeclaration(), data.getNomDemandeur(),
                    data.getDateTraitement(), data.getTypeInspection());

                LocalDateTime localDateDemande = LocalDateTime.of(data.getDateDeclaration().getYear(),
                        data.getDateDeclaration().getMonth(), data.getDateDeclaration().getDay(),data.getDateDeclaration().getHours(),
                        data.getDateDeclaration().getMinutes(), data.getDateDeclaration().getSeconds());
            LocalDateTime localDateTraitement = LocalDateTime.of(data.getDateTraitement().getYear(), data.getDateTraitement().getMonth(),
                    data.getDateTraitement().getDay(), data.getDateTraitement().getHours(), data.getDateTraitement().getMinutes(),
                    data.getDateTraitement().getSeconds());

                Duration duration = Duration.between(localDateDemande, localDateTraitement);
               long hours = duration.toHoursPart();
               dto.setDuree(hours);
               statistiqueDtos.add(dto);
        });

        if (!listDemandesPhyto.isEmpty()) {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.succes"),
                    statistiqueDtos), HttpStatus.OK);
        }else  {
        return     new ResponseEntity<>(new ApiResponseMessage(HttpStatus.OK.name(),
                    env.getProperty("message.list.vide") ), HttpStatus.OK);
        }
    }
}
