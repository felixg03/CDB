package com.excilys.cdb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.DTOs.DTODatabase.DTOCompanyDB;
import com.excilys.cdb.mappers.DTODatabaseMappers.CompanyDTODatabaseMapper;
import com.excilys.cdb.models.Company;
import com.excilys.cdb.repositoryInterfaces.CompanyRepository;
import com.excilys.cdb.repositoryInterfaces.ComputerRepository;


@Service
@Scope( value = ConfigurableBeanFactory.SCOPE_SINGLETON )
public final class CompanyService {

	private CompanyRepository companyRepository;
	private ComputerRepository computerRepository;
	
	@Autowired
	public CompanyService( CompanyRepository companyRepository
						 , ComputerRepository computerRepository ) {
		super();
		this.companyRepository = companyRepository;
		this.computerRepository = computerRepository;
	}



	public List<Company> getListCompanies(int offset) {
		int pageNumber = offset / 10;
		int nbOfCompany = 10;
		Page<DTOCompanyDB> pageDTOCompanyDB = companyRepository.findAll( PageRequest.of( pageNumber, nbOfCompany ) );
		return CompanyDTODatabaseMapper.convertToListCompany( pageDTOCompanyDB.getContent() );
	}
	
	
	
	public List<Company> getListCompanies() {
		return CompanyDTODatabaseMapper.convertToListCompany( companyRepository.findAll() );
	}
	
	
	@Transactional
	public void callCompanyDeletion( Long companyId ) {
		computerRepository.removeByDtoCompanyDB_Id( companyId );
		companyRepository.deleteById( companyId );
	}
	
	
	public boolean checkCompanyId( Long companyId ) {
		return companyRepository.existsById( companyId );
	}
}
