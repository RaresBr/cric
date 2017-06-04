package ro.cric.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ro.cric.model.Organization;
import ro.cric.model.User;
import ro.cric.service.OrganizationService;
import ro.cric.service.UserService;

@Component(value = "sessionComponent")
@Scope(value = "session")
public class SessionData {

	@Autowired
	private UserService userService;
	
	@Autowired
	private OrganizationService organizationService;
	
	private String sessionId;
	
	private User loggedUser;
	
	private Organization loggedOrganization;

	public OrganizationService getOrganizationService() {
		return organizationService;
	}

	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}

	public Organization getLoggedOrganization() {
		return loggedOrganization;
	}

	public void setLoggedOrganization(Organization loggedOrganization) {
		this.loggedOrganization = loggedOrganization;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public User getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
	}

	public boolean isLogged() {
		boolean isLogged;
		if (loggedUser != null)
			isLogged = true;
		else
			isLogged = false;
		return isLogged;
	}
	
	public boolean isOrganizationLogged() {
		boolean isLogged;
		if(loggedOrganization != null)
			isLogged = true;
		else
			isLogged = false;
		return isLogged;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

}
