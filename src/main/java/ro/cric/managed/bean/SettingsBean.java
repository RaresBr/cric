package ro.cric.managed.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import ro.cric.component.SessionData;
import ro.cric.model.Organization;
import ro.cric.model.User;
import ro.cric.service.OrganizationService;
import ro.cric.service.UserService;

@ManagedBean(name = "settingsBean")
@RequestScoped
public class SettingsBean {

	@ManagedProperty("#{sessionComponent}")
	private SessionData session;

	@ManagedProperty("#{userService}")
	private UserService userService;

	@ManagedProperty("#{organizationService}")
	private OrganizationService organizationService;

	private User user;

	private Organization selectedOrganization = new Organization();

	@PostConstruct
	private void init() {
		user = session.getLoggedUser();
	}

	public SessionData getSession() {
		return session;
	}

	public void setSession(SessionData session) {
		this.session = session;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public OrganizationService getOrganizationService() {
		return organizationService;
	}

	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}

	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	public List<Organization> getAllOrganizations() {
		return organizationService.getAllOrganizations();
	}

	public Organization getSelectedOrganization() {
		return selectedOrganization;
	}

	public void setSelectedOrganization(Organization selectedOrganization) {
		this.selectedOrganization = selectedOrganization;
	}

	public String setEmergencyOrganization() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		FacesMessage message = null;

		System.out.println(selectedOrganization);
		user.setOrganization(selectedOrganization);
		userService.register(user);

		message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Emergency organization changed", null);

		FacesContext.getCurrentInstance().addMessage(null, message);
		ec.getFlash().setKeepMessages(true);
		return "settings?faces-redirect=true";
	}

}
