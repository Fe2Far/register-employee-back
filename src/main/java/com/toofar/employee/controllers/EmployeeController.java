package com.toofar.employee.controllers;

import java.net.URI;
import java.util.List;
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
import com.toofar.employee.exceptions.ObjectNotFoundException;
import com.toofar.employee.models.Employee;
import com.toofar.employee.models.Sector;
import com.toofar.employee.response.Response;
import com.toofar.employee.services.EmployeeService;
import com.toofar.employee.services.SectorService;

@RestController
@RequestMapping(value="/employee")
@CrossOrigin(origins = "*")
public class EmployeeController {

	@Autowired
	private EmployeeService service;
	
	@Autowired
	private SectorService sectorService;

	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<Response<Employee>> findById(@PathVariable Integer id) throws ObjectNotFoundException {		
		Response<Employee> response = new Response<Employee>();
		try {
			Employee obj = service.findById(id);
			response.setData(obj);
		} catch(ObjectNotFoundException e) {
			response.getErrors().add("Funcionário não encontrada para o id " + id);
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok().body(response);
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
		
		Response<Employee> response = new Response<Employee>();
		Employee obj = service.fromDTO(objDTO);
		obj.setId(id);
        
		try {	
			Sector s = sectorService.findById(objDTO.getSector().getId());
			obj.setSector(s);
			obj = service.update(obj);
		} catch(ObjectNotFoundException e) {
			response.getErrors().add("Departamento não encontrada para o id " + objDTO.getSector().getId());
			return ResponseEntity.badRequest().body(null);
		}

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
