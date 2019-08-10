package com.toofar.employee.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toofar.employee.models.Employee;
import com.toofar.employee.repositories.EmployeeRepository;

@Service
public class DBService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	public void instatiateTestDatabase() {

		Employee e1 = new Employee();
		e1.setName("Felipe");
		e1.setEmail("felipe@2far.com.br");
		
		Employee e2 = new Employee();
		e2.setName("Joao");
		e2.setEmail("joao@luizalabs.com.br");

		this.employeeRepository.saveAll(Arrays.asList(e1,e2));
	}

}
