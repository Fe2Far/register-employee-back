package com.toofar.employee.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.toofar.employee.dtos.SectorDto;
import com.toofar.employee.models.Sector;
import com.toofar.employee.services.SectorService;

@RestController
@RequestMapping(value="/sector")
@CrossOrigin(origins = "*")
public class SectorController {

	@Autowired
	private SectorService sectorService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<SectorDto>> findAll(){		
		List<Sector> lista= sectorService.findAll(); 
		List<SectorDto> listDTO = lista.stream()
				.map(obj -> new SectorDto(obj)).collect(Collectors.toList()); 
		return ResponseEntity.ok().body(listDTO);
	}

}
