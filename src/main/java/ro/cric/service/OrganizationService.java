package ro.cric.service;

import java.util.List;

import com.google.publicalerts.cap.Alert;

import ro.cric.model.Organization;

public interface OrganizationService {
	
	public Organization getOrganizationById(long id);
	public boolean doesEmailExist(String email);
	public boolean doesUsernameExist(String username);
	public Organization getOrganizationByCredentials(String password, String username);
	public void register(Organization organization);
	public List<Organization> getAllOrganizations();

}
