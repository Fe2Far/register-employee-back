package com.toofar.employee.repositories;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.toofar.employee.models.Employee;
import com.toofar.employee.models.Sector;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class SectorRepositoryTest {

	@Autowired
	private SectorRepository sectorRepository;

	@Autowired
	private EmployeeRepository employeeRepository;
	
	private static final String NAME = "Sector 1";
	
	@Before
	public void setUp() throws Exception {

		Sector sector = new Sector();
		sector.setName(NAME);

		this.sectorRepository.save(sector);

		Employee e = new Employee();
		e.setName("Employee1 Sector 1");
		e.setEmail("employee1@test.com.br");
		e.setSector(sector);

		Employee e2 = new Employee();
		e2.setName("Employee2 Sector 1");
		e2.setEmail("employee2@test.com.br");
		e2.setSector(sector);

		this.employeeRepository.saveAll(Arrays.asList(e,e2));

	}
	
	@After
	public void tearDown() throws Exception {
		this.employeeRepository.deleteAll();
		this.sectorRepository.deleteAll();
	}
	
	@Test
	public void testFindEmployeesBySector() {
		Optional<Sector> sector = this.sectorRepository.findByName(NAME);
		Optional<List<Employee>> listEmployees = this.employeeRepository.findBySector(sector.get().getId());

		assertEquals(2,listEmployees.get().size());

	}

}
