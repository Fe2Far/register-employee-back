package com.toofar.employee.controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.toofar.employee.models.Employee;
import com.toofar.employee.services.EmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class EmployeeControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private EmployeeService employeeService;

	private static final String FIND_EMPLOYEE_BY_ID_URL = "/employee/";
	private static final Integer ID = Integer.valueOf(1);
	private static final String NAME = "Employee Test";
	private static final String EMAIL = "email@test.com";
	
	@Test
	public void testFindValidEmployee() throws Exception {

		BDDMockito.given(this.employeeService.find(ID)).willReturn(this.getEmployeeData());

		mvc.perform(MockMvcRequestBuilders.get(FIND_EMPLOYEE_BY_ID_URL + ID)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(ID))
				.andExpect(jsonPath("$.name", equalTo(NAME)))
			    .andExpect(jsonPath("$.email", equalTo(EMAIL)));
	}

	private Employee getEmployeeData() {
		Employee employee = new Employee();
		employee.setId(1);
		employee.setName("Employee Test");
		employee.setEmail("email@test.com");
		return employee;
	}

}
