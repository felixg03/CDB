package com.excilys.cdb.repositoryInterfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.DTOs.DTODatabase.DTOCompanyDB;

@Repository
public interface CompanyRepository extends JpaRepository<DTOCompanyDB, Long> {
	
}