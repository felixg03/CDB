package com.excilys.cdb.repositoryInterfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.excilys.cdb.DTODatabase.DTOComputerDB;

public interface ComputerRepository extends JpaRepository<DTOComputerDB, Long> {
	
}