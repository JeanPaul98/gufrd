package com.acl.formaliteagricultureapi.services.autorisation.laisserPasser;

import com.acl.formaliteagricultureapi.dto.autorisation.NotificationConsignationDto;
import com.acl.formaliteagricultureapi.dto.formalite.UpdateFormaliteDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Zansouyé on 18/09/2024
 * @project formalite-agriculture-api
 */
@Service
public interface NotificationConsignationService {

    ResponseEntity<?> createNotificationConsignation(NotificationConsignationDto notificationConsignationDto);

    ResponseEntity<byte[]>generateLaisserPasserNotificationConsignation(UpdateFormaliteDto updateFormaliteDto);
}
