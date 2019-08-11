package com.toofar.employee.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toofar.employee.exceptions.ObjectNotFoundException;
import com.toofar.employee.models.Sector;
import com.toofar.employee.repositories.SectorRepository;

@Service
public class SectorService {

	@Autowired
	private SectorRepository sectorRepository;
	
	public Sector findById(Integer id)  {
		Optional<Sector> obj = sectorRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id : " + id + ", Tipo : " + Sector.class.getName()));
	}

	public List<Sector> findAll() {
		return sectorRepository.findAll();
	}

}
