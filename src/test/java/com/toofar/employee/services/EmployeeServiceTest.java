package com.toofar.employee.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


import com.toofar.employee.models.Employee;
import com.toofar.employee.repositories.EmployeeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class EmployeeServiceTest {

	@MockBean
	private EmployeeRepository employeeRepository;

	@Autowired
	private EmployeeService employeeService;

	private static final Integer ID = 1;
	
	@Before
	public void setUp() throws Exception {
		BDDMockito.given(this.employeeRepository.findById(Mockito.anyInt())).willReturn(Optional.of(new Employee()));
		BDDMockito.given(this.employeeRepository.save(Mockito.any(Employee.class))).willReturn(new Employee());
	}

	@Test
	public void testFindById() {
		Employee employee = this.employeeService.findById(ID);
		assertTrue(employee != null);
	}
	
	@Test
	public void testPersistirEmpresa() {
		Employee employee = this.employeeService.insert(new Employee());
		assertNotNull(employee);
	}

}
