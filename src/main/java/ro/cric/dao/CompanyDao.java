package ro.cric.dao;

import ro.cric.model.Company;

public interface CompanyDao extends GenericDao<Company> {

	public boolean doesEmailExist(String email);
	public boolean doesUsernameExist(String username, Long id);
	public Company getByCredentials(String password, String username);
}
