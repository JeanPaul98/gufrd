package com.acl.mswauth.servicesImpl;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.acl.mswauth.converter.StructureConverter;
import com.acl.mswauth.model.MswPays;
import com.acl.mswauth.model.MswStructure;
import com.acl.mswauth.playload.ApiResponseModel;
import com.acl.mswauth.repositories.PaysRepository;
import com.acl.mswauth.repositories.StructureRepository;
import com.acl.mswauth.request.StructureRequest;
import com.acl.mswauth.service.StructureService;

@Service
public class StructureServiceImpl implements StructureService {
	
	private final PaysRepository paysRepository;
	
	private final StructureRepository structureRepository;
	
	private final StructureConverter structureConverter;
	
	/**
	 * 
	 * @param paysRepository
	 * @param structureRepository
	 */
	public StructureServiceImpl(PaysRepository paysRepository, StructureRepository structureRepository,
			StructureConverter structureConverter) {
		super();
		this.paysRepository = paysRepository;
		this.structureRepository = structureRepository;
		this.structureConverter = structureConverter;
	}

	@Override
	public ResponseEntity<?> create(StructureRequest request) {
		
		Optional<MswPays> pays = paysRepository.findById(request.getCodePays());
		
		if(!pays.isPresent())
			 return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
	                    "Le Pays  n'existe pas  ",request.getCodePays()),HttpStatus.NOT_FOUND);
		
		     MswStructure structure = structureConverter.convertEntity(request);
		
			structure.setMswPays(pays.get());
			
			MswStructure save = structureRepository.save(structure);
			
			return new ResponseEntity<>(new ApiResponseModel(HttpStatus.CREATED.name(),
                    "Opération réussie ",save),HttpStatus.OK);
					
		
		// TODO Auto-generated method stub
		//return null;
	}
	
	@Override
	public ResponseEntity<?> assignStructureParente(Long structureId, Long structureParenteId) {
		  
		  Optional<MswStructure> structureParente = structureRepository.findById(structureParenteId);
			
		if(!structureParente.isPresent())
	       return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
		                  "La Structure parente fournie n'existe pas  ",structureParenteId),HttpStatus.NOT_FOUND);
			 
		Optional<MswStructure> structure = structureRepository.findById(structureId);
		
		   if(!structure.isPresent())
			 return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
	                    "La Structure fournie n'existe pas  ",structureId),HttpStatus.NOT_FOUND);
		   
		   MswStructure dbStructure = structure.get();
		   
		   dbStructure.setMswStructure(structureParente.get());
		   
		   MswStructure save = structureRepository.save(dbStructure);
			
			return new ResponseEntity<>(new ApiResponseModel(HttpStatus.CREATED.name(),
                   "Opération réussie ",save),HttpStatus.OK);
		   
			   
			   
		
	}
	

	@Override
	public ResponseEntity<?> update(Long structureId, StructureRequest request) {
		
		Optional<MswStructure> structure = structureRepository.findById(structureId);
		
		   if(!structure.isPresent())
			 return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
	                    "La Structure fournie n'existe pas  ",structureId),HttpStatus.NOT_FOUND);
		   
		   Optional<MswPays> pays = paysRepository.findById(request.getCodePays());
			
			if(!pays.isPresent())
				 return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
		                    "Le Pays  n'existe pas  ",request.getCodePays()),HttpStatus.NOT_FOUND);
		   
		   MswStructure dbStructure = structure.get();
		   
		   dbStructure.setAdresse(request.getAdresse());
		   
		   dbStructure.setEmail(request.getEmail());
		   
		   dbStructure.setNom(request.getNom());
		   
		   dbStructure.setMswPays(pays.get());
		   
		   dbStructure.setSiteWeb(request.getSiteWeb());
		   
		   dbStructure.setTelephone(request.getTelephone());
		   
		   MswStructure save = structureRepository.save(dbStructure);
		
		   return new ResponseEntity<>(new ApiResponseModel(HttpStatus.CREATED.name(),
                   "Opération réussie ",save),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getAllByStructureParente(Long structureParenteId) {
		return new ResponseEntity<>(new ApiResponseModel(HttpStatus.FOUND.name(),
                "Opération réussie ",structureRepository.findAllByStructureParente(structureParenteId)),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getAllByPays(String codePays) {		
		return new ResponseEntity<>(new ApiResponseModel(HttpStatus.FOUND.name(),
                "Opération réussie ",structureRepository.findAllByPays(codePays)),HttpStatus.OK);
				
	}

}
