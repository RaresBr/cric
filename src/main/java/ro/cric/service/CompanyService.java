package ro.cric.service;

import ro.cric.model.Company;

public interface CompanyService {
	
	public Company getCompanyById(long id);
	public boolean doesEmailExist(String email);
	public boolean doesUsernameExist(String username, Long id);
	public Company getCompanyByCredentials(String password, String username);
	public void register(Company company);
}