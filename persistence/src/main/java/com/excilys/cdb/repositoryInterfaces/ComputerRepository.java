package com.excilys.cdb.repositoryInterfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.DTOs.DTODatabase.DTOComputerDB;

@Repository
public interface ComputerRepository extends JpaRepository<DTOComputerDB, Long> {
	
	List<DTOComputerDB> findByNameContaining( String name, Sort sort );
	
	Page<DTOComputerDB> findByNameContaining( String name, Pageable pageable );
	
	List<DTOComputerDB> removeByDtoCompanyDB_Id( Long companyId );
}