package com.toofar.employee.dtos;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.toofar.employee.models.Sector;

public class SectorDto {

	private Integer id;

	@NotEmpty(message="Preenchimento Obrigatório")
	@Length(min=5,max=120, message="Valor entre 5 e 120 caracteres")
	private String name;

	public SectorDto() {
		super();
	}

	public SectorDto(Integer id,String name) {
		super();
		this.id = id;
		this.name = name;
	}



	public SectorDto(Sector obj) {
		this.id = obj.getId();
		this.name = obj.getName();
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

}
