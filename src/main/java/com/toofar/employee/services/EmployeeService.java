package com.toofar.employee.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.toofar.employee.dtos.EmployeeDto;
import com.toofar.employee.exceptions.ObjectNotFoundException;
import com.toofar.employee.models.Employee;
import com.toofar.employee.models.Sector;
import com.toofar.employee.repositories.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee findById(Integer id)  {
		Optional<Employee> obj = employeeRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id : " + id + ", Tipo : " + Employee.class.getName()));
	}
	
	public Employee findByEmail(String email) {
		Optional<Employee> obj = employeeRepository.findByEmail(email);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Email : " + email + ", Tipo : " + Employee.class.getName()));
	}
	
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Transactional
	public Employee insert(Employee obj) {
		obj.setId(null);
		obj = employeeRepository.save(obj);
		return obj;
	}

	public Employee update(Employee obj) {
		Employee newObj = findById(obj.getId());
		updateObject(newObj,obj);
		return employeeRepository.save(newObj);
	}

	public void updateObject(Employee newObj,Employee obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
		newObj.setSector(obj.getSector());
	}

	public void delete(Integer id) {
		findById(id);
		employeeRepository.deleteById(id);
	}

	public Employee fromDTO(EmployeeDto objDTO) {
		return new Employee(objDTO.getId(), objDTO.getName(), objDTO.getEmail(), 
				new Sector(objDTO.getSector().getId(),objDTO.getSector().getName())); 
	}

}
