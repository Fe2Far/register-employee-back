package com.toofar.employee.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.toofar.employee.models.Sector;

@Repository
@Transactional(readOnly=true)
public interface SectorRepository extends JpaRepository<Sector, Integer> {
	
	Optional<Sector> findByName(String name);

}
