package ro.cric.dao;

import ro.cric.model.Organization;

public interface OrganizationDao extends GenericDao<Organization>  {
	
	public boolean doesEmailExist(String email);

	public boolean doesUsernameExist(String username);

	public Organization getByCredentials(String password, String username);

}
