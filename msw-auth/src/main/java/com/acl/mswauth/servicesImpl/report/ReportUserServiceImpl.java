package com.acl.mswauth.servicesImpl.report;

import com.acl.mswauth.controller.ClientController;
import com.acl.mswauth.dto.DetailUserDto;
import com.acl.mswauth.dto.formalite.FormaliteStatReportDto;
import com.acl.mswauth.dto.user.UserGroupeDto;
import com.acl.mswauth.dto.user.UserReportDto;
import com.acl.mswauth.dto.formalite.FormaliteReportDto;
import com.acl.mswauth.dto.formalite.FormaliteStatistiqueDto;
import com.acl.mswauth.interfaces.StatistiqueUserGroupeInterface;
import com.acl.mswauth.interfaces.UserInterface;
import com.acl.mswauth.report.ReportService;
import com.acl.mswauth.repositories.UserRepository;
import com.acl.mswauth.service.report.ReportUserService;
import com.acl.mswauth.service.statistique.StatistiqueFormaliteService;
import fr.opensagres.xdocreport.core.io.IOUtils;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author kol on 10/15/24
 * @project msw-auth
 */
@Service
@Slf4j
public class ReportUserServiceImpl implements ReportUserService {

    private final UserRepository userRepository;

    private final StatistiqueFormaliteService statistiqueFormaliteService;

    private final ReportService reportService;

    SimpleDateFormat sf = new SimpleDateFormat("EEEE dd MMMM yyyy");
    SimpleDateFormat sfDate = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sfDateHeure = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public ReportUserServiceImpl(UserRepository userRepository, StatistiqueFormaliteService statistiqueFormaliteService, ReportService reportService) {
        this.userRepository = userRepository;
        this.statistiqueFormaliteService = statistiqueFormaliteService;
        this.reportService = reportService;
    }

    @Override
    public ResponseEntity<byte[]> getUserReport() {
        HttpHeaders headers = new HttpHeaders();
        InputStream in;
        try {
            List<UserInterface> utilisateurs =  userRepository.findAllListUsersActive();
            List<UserInterface> users = utilisateurs.stream().
                    filter(m-> !Objects.equals(m.getGroupe(), "AUTRES")).toList();

            List<DetailUserDto> detailUserDtos = new ArrayList<>();
            users.forEach(data-> {
                DetailUserDto detailUserDto = new DetailUserDto(data.getNomEntreprise(), data.getEmail(),
                        data.getFonction(), data.getTelephone(), data.getLogin(), data.getGroupe());

                detailUserDto.setStatus("OK");
                detailUserDto.setPassword("OK");
                detailUserDtos.add(detailUserDto);
            });

            List<UserInterface> userEntreprises = userRepository.findListEntreprise();

            UserGroupeDto userGroupeDto = listUserByGroupe().get(0);


            UserReportDto userReportDto = new UserReportDto();
            userReportDto.setTotal(users.size());
            userReportDto.setDateFin(new Date());
            userReportDto.setEntreprise(userEntreprises.size());
            userReportDto.setLigneMaritime(userGroupeDto.getLigneMaritime());
            userReportDto.setChargeur(userGroupeDto.getChargeur());
            userReportDto.setConsignataire(userGroupeDto.getConsignataire());
            userReportDto.setTransitaire(userGroupeDto.getTransitaire());
            userReportDto.setOperateurTerminaux(userGroupeDto.getOperateurTerminaux());

            userReportDto.setDetailUsers(detailUserDtos);

            in = this.reportService.exportUser(userReportDto, "PDF");
            byte[] media = IOUtils.toByteArray(in);
            headers.setCacheControl(CacheControl.noCache().getHeaderValue());
            headers.setContentType(MediaType.APPLICATION_PDF);
            ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);

            return responseEntity;
        } catch (IOException | JRException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<UserGroupeDto> listUserByGroupe() {
        List<StatistiqueUserGroupeInterface> data = userRepository.findListStatisqueGroupe();
        UserGroupeDto userGroupeDto = new UserGroupeDto();
        List<UserGroupeDto> userGroupeDtos = new ArrayList<>();
        data.forEach(data1 -> {

            if(data1.getNomGroupe().equalsIgnoreCase("LIGNES MARITIMES")) {
                 userGroupeDto.setLigneMaritime(data1.getNombre());
            }else if(data1.getNomGroupe().equalsIgnoreCase("CHARGEURS")) {
                userGroupeDto.setChargeur(data1.getNombre());
            } else if(data1.getNomGroupe().equalsIgnoreCase("TRANSITAIRES")) {
                userGroupeDto.setTransitaire(data1.getNombre());
            }else if(data1.getNomGroupe().equalsIgnoreCase("CONSIGNATAIRES")) {
                userGroupeDto.setConsignataire(data1.getNombre());
            } else if(data1.getNomGroupe().equalsIgnoreCase("OPERATEURS DE TERMINAUX")) {
                userGroupeDto.setOperateurTerminaux(data1.getNombre());
            }

            userGroupeDtos.add(userGroupeDto);
        });
        log.info("Donner groupe {}",userGroupeDtos);
        return userGroupeDtos;
    }


    @Override
    public ResponseEntity<byte[]> getFormalitePhytoReport() {
        HttpHeaders headers = new HttpHeaders();
        InputStream in;
        try {


            List<FormaliteStatistiqueDto> formaliteStatistiqueDtos =  statistiqueFormaliteService.getFormaliteStatistique();

            List<FormaliteStatReportDto> formaliteStatistiqueDtoList = new ArrayList<>();

            FormaliteReportDto formaliteReportDto = new FormaliteReportDto();
            formaliteReportDto.setDateFin(new Date());

            formaliteReportDto.setDeclarant(10+"");
            formaliteStatistiqueDtos.forEach(dto -> {
                FormaliteStatReportDto dto1 = new FormaliteStatReportDto();
                dto1.setAffreteur(dto.getAffreteur());
                dto1.setSociete(dto.getAffreteur());
                dto1.setTypeFormalite(dto.getTypeFormalite());
                dto1.setNavire(dto.getNavire());
                dto1.setDuree(String.valueOf(dto.getDuree()));
                dto1.setNavire(dto.getNavire());
                dto1.setStructure(dto.getStructure());
                dto1.setDateTraitement(sfDateHeure.format(dto.getDateTraitement()));
                dto1.setDateDeclaration(sfDateHeure.format(dto.getDateDeclaration()));
                dto1.setEntreprise(dto.getEntreprise());
                formaliteStatistiqueDtoList.add(dto1);
            });

            formaliteReportDto.setFormaliteStatistiqueDtos(formaliteStatistiqueDtoList);
            formaliteReportDto.setTotal(String.valueOf(formaliteStatistiqueDtos.size()));

            in = this.reportService.exportFormalitePhytoTraiter(formaliteReportDto, "PDF");

            byte[] media = IOUtils.toByteArray(in);
            headers.setCacheControl(CacheControl.noCache().getHeaderValue());
            headers.setContentType(MediaType.APPLICATION_PDF);
            ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);

            return responseEntity;
        } catch (IOException | JRException e) {
            e.printStackTrace();
            return null;
        }
    }
}
