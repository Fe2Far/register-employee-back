package com.toofar.employee.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.toofar.employee.models.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class EmployeeRepositoryTest {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	private static final String EMAIL = "email@test.com";
	
	@Before
	public void setUp() throws Exception {
		Employee employee = this.employeeRepository.save(getEmployeeData());
		this.employeeRepository.save(employee);
	}

	@Test
	public void testFindByEmail() {
		Employee employee = this.employeeRepository.findByEmail(EMAIL);
		assertEquals(EMAIL, employee.getEmail());
	}
	
	@Test
	public void testFindByEmailInvalid() {
		Employee employee = this.employeeRepository.findByEmail(EMAIL);
		assertNotNull(employee);
	}
	
	@After
	public final void tearDown() {
		this.employeeRepository.deleteAll();
	}

	private Employee getEmployeeData() {
		Employee employee = new Employee();
		employee.setName("Employee Test");
		employee.setEmail("email@test.com");
		return employee;
	}

}
