package ro.cric.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.cric.dao.CompanyDao;
import ro.cric.model.Company;
import ro.cric.service.CompanyService;

@Service(value = "companyService")
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyDao companyDao;

	@Override
	@Transactional
	public Company getCompanyById(long id) {
		return companyDao.getById(id);
	}

	@Override
	@Transactional
	public boolean doesEmailExist(String email) {
		return companyDao.doesEmailExist(email);
	}

	@Override
	@Transactional
	public boolean doesUsernameExist(String username, Long id) {
		return companyDao.doesUsernameExist(username, id);
	}

	@Override
	@Transactional
	public void register(Company company) {
		companyDao.persist(company);
	}

	@Override
	public Company getCompanyByCredentials(String password, String username) {
		return companyDao.getByCredentials(password, username);
	}

}
