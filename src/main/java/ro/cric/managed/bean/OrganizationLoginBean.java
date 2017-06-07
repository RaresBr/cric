package ro.cric.managed.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import ro.cric.component.SessionData;
import ro.cric.model.Organization;
import ro.cric.service.OrganizationService;

@ManagedBean(name = "organizationLoginBean")
@ViewScoped
public class OrganizationLoginBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;

	@ManagedProperty("#{organizationService}")
	private OrganizationService organizationService;

	@ManagedProperty("#{sessionComponent}")
	private SessionData session;

	public OrganizationService getOrganizationService() {
		return organizationService;
	}

	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public SessionData getSession() {
		return session;
	}

	public void setSession(SessionData session) {
		this.session = session;
	}

	public String login() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		FacesMessage message = null;
		boolean loggedIn = false;
		if (username != null && password != null) {
			Organization organization = organizationService.getOrganizationByCredentials(password, username);
			if (organization != null) {
				message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", username);
				loggedIn = true;
				session.setLoggedOrganization(organization);
				System.out.println(session.getLoggedOrganization().getEmail());

			} else
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login error", "Invalid credentials");
		}

		FacesContext.getCurrentInstance().addMessage(null, message);

		if (loggedIn) {
			ec.getFlash().setKeepMessages(true);
			return "dashboard?faces-redirect=true";
		}
		return null;
	}

	public String logout() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "You have been signed out", "");
		FacesContext.getCurrentInstance().addMessage(null, message);
		ec.getFlash().setKeepMessages(true);
		ec.invalidateSession();
		session.setLoggedUser(null);

		return "home?faces-redirect=true";
	}
}
