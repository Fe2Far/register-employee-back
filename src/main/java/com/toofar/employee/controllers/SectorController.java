package com.toofar.employee.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.toofar.employee.dtos.EmployeeDto;
import com.toofar.employee.dtos.SectorDto;
import com.toofar.employee.models.Employee;
import com.toofar.employee.models.Sector;
import com.toofar.employee.response.Response;
import com.toofar.employee.services.EmployeeService;
import com.toofar.employee.services.SectorService;

@RestController
@RequestMapping(value="/sector")
@CrossOrigin(origins = "*")
public class SectorController {

	@Autowired
	private SectorService sectorService;

	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Response<List<SectorDto>>> findAll(){		
		
		Response<List<SectorDto>> response = new Response<List<SectorDto>>();
		
		List<Sector> lista= sectorService.findAll(); 
		List<SectorDto> listDto = lista.stream()
				.map(obj -> new SectorDto(obj)).collect(Collectors.toList()); 		
		response.setData(listDto);
		
		return ResponseEntity.ok().body(response);
	}

	@RequestMapping(value="/{sectorId}/employees", method=RequestMethod.GET)
	public ResponseEntity<Response<List<EmployeeDto>>> findEmployees(@PathVariable Integer sectorId) {

		Response<List<EmployeeDto>> response = new Response<List<EmployeeDto>>();

		List<Employee> list = employeeService.findBySector(sectorId);
		List<EmployeeDto> listDto = list.stream().map(obj -> new EmployeeDto(obj)).collect(Collectors.toList());  
		
		response.setData(listDto);
		
		return ResponseEntity.ok().body(response);
	}

}
