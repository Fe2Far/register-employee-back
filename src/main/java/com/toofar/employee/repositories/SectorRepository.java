package com.toofar.employee.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.toofar.employee.models.Sector;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Integer> {

}
