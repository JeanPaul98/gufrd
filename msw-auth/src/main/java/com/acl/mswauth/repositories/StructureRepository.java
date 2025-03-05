package com.acl.mswauth.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.acl.mswauth.model.MswStructure;

public interface StructureRepository extends JpaRepository<MswStructure, Long> {
	
	
	@Query(nativeQuery = true, value ="SELECT T1.* FROM  USERMSW.STRUCTURES T1 WHERE T1.STRUCTURE_PARENTE_ID IS NULL AND T1.PAYS_CODE = ? ")
	List<MswStructure> findAllByPays(String codepays);
	
	@Query(nativeQuery = true, value ="SELECT T1.* FROM  USERMSW.STRUCTURES T1 WHERE T1.STRUCTURE_PARENTE_ID = ? ")
	List<MswStructure> findAllByStructureParente(Long structureParenteId);

}
