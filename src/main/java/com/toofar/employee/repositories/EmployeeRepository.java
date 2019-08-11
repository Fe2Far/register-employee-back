package com.toofar.employee.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.toofar.employee.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	Optional<Employee> findByEmail(String email);

	Optional<Employee> findByName(String name);

	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Employee obj WHERE obj.sector.id = :sectorId ORDER BY obj.name")
	Optional<List<Employee>> findBySector(@Param("sectorId") Integer sectorId);

}
