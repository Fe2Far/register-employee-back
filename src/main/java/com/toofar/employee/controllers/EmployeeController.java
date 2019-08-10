package com.toofar.employee.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.toofar.employee.dtos.EmployeeDto;
import com.toofar.employee.exceptions.DataIntegrityException;
import com.toofar.employee.models.Employee;
import com.toofar.employee.services.EmployeeService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value="/employee")
@CrossOrigin(origins = "*")
public class EmployeeController {

	@Autowired
	private EmployeeService service;

	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<Employee> findById(@PathVariable Integer id) throws ObjectNotFoundException {		
		Employee obj = service.find(id); 
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<EmployeeDto>> findAll(){		
		List<Employee> lista= service.findAll(); 
		List<EmployeeDto> listDTO = lista.stream()
				.map(obj -> new EmployeeDto(obj)).collect(Collectors.toList()); 
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody EmployeeDto objDTO) {
		Employee obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody EmployeeDto objDTO,@PathVariable Integer id) {
		Employee obj = service.fromDTO(objDTO);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable Integer id) throws ObjectNotFoundException {		

		try {
			service.delete(id);	
		}
		catch(DataIntegrityViolationException e) {
			 throw new DataIntegrityException("Não é possível excluir um registro que possui relacionamentos.");
		}

		return ResponseEntity.noContent().build();
	}
	
	

}
