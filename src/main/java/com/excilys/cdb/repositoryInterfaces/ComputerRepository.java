package com.excilys.cdb.repositoryInterfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.DTODatabase.DTOComputerDB;

@Repository
public interface ComputerRepository extends JpaRepository<DTOComputerDB, Long> {
	List<DTOComputerDB> findByNameContaining(String name);
	Page<DTOComputerDB> findByOrderByNameAsc( Pageable pageable );
	Page<DTOComputerDB> findByOrderByIntroducedAsc( Pageable pageable );
	Page<DTOComputerDB> findByOrderByDiscontinuedAsc( Pageable pageable );
	Page<DTOComputerDB> findByOrderByDtoCompanyDB_NameAsc( Pageable pageable );
	
	List<DTOComputerDB> removeByDtoCompanyDB_Id( Long companyId );
}