package com.toofar.employee.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.toofar.employee.models.Employee;

public class EmployeeDto {
	
	private Integer id;

	@NotEmpty(message="Preenchimento Obrigatório")
	@Length(min=5,max=120, message="Valor entre 5 e 120 caracteres")
	private String name;
	
	@NotEmpty(message="Preenchimento Obrigatório")
	@Email(message="Email inválido")
	private String email;

	public EmployeeDto() {
		super();
	}

	public EmployeeDto(Employee obj) {
		this.id = obj.getId();
		this.name = obj.getName();
		this.email = obj.getEmail();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
