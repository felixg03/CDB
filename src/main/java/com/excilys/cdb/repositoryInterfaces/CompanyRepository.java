package com.excilys.cdb.repositoryInterfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.excilys.cdb.DTODatabase.DTOCompanyDB;

public interface CompanyRepository extends JpaRepository<DTOCompanyDB, Long> {}