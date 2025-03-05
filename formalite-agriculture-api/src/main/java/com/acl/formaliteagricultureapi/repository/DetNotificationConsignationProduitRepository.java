package com.acl.formaliteagricultureapi.repository;

import com.acl.formaliteagricultureapi.models.DetNotificationConsignationProduit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Zansouyé on 18/09/2024
 * @project formalite-agriculture-api
 */
@Repository
public interface DetNotificationConsignationProduitRepository extends JpaRepository<DetNotificationConsignationProduit, Long> {
}
