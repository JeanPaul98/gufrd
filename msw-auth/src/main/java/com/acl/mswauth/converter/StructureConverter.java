package com.acl.mswauth.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.acl.mswauth.model.MswStructure;
import com.acl.mswauth.request.StructureRequest;

@Service
public class StructureConverter {
	
	
	/**
	 * 
	 * @param entity
	 * @return
	 */
	 public StructureRequest convertDto(MswStructure entity) {
		 ModelMapper modelMapper = new ModelMapper();
		 StructureRequest dto = modelMapper.map(entity, StructureRequest.class);  
		 return  dto;
	 }
	 
	 /**
	  * 
	  * @param request
	  * @return
	  */
	 public MswStructure convertEntity(StructureRequest request) {
		 ModelMapper modelMapper = new ModelMapper();
		 MswStructure entity = modelMapper.map(request, MswStructure.class);
		 //entity.set
		 return  entity;
	 }

}
