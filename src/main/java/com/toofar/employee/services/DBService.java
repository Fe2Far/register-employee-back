package com.toofar.employee.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toofar.employee.models.Employee;
import com.toofar.employee.models.Sector;
import com.toofar.employee.repositories.EmployeeRepository;
import com.toofar.employee.repositories.SectorRepository;

@Service
public class DBService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private SectorRepository sectorRepository;

	public void instatiateTestDatabase() {

		Sector s1 = new Sector();
		s1.setName("Gestao de Pessoas");
		Sector s2 = new Sector();
		s2.setName("Tecnologia");

		this.sectorRepository.saveAll(Arrays.asList(s1,s2));
		
		Employee e1 = new Employee();
		e1.setName("Felipe");
		e1.setEmail("felipe@2far.com.br");
		e1.setSector(s2);

		Employee e2 = new Employee();
		e2.setName("Joao");
		e2.setEmail("joao@luizalabs.com.br");
		e2.setSector(s1);

		this.employeeRepository.saveAll(Arrays.asList(e1,e2));
	}

}
