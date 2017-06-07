package ro.cric.managed.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import ro.cric.component.SessionData;
import ro.cric.model.Organization;
import ro.cric.service.OrganizationService;

@ManagedBean(name = "organizationBean")
@SessionScoped
public class OrganizationBean {

	@ManagedProperty(value = "#{sessionComponent}")
	private SessionData session;

	@ManagedProperty(value = "#{organizationService}")
	private OrganizationService organizationService;

	private Organization organization;
	
	public OrganizationBean() {
		
	}

	public SessionData getSession() {
		return session;
	}

	public void setSession(SessionData session) {
		this.session = session;
	}

	public OrganizationService getOrganizationService() {
		return organizationService;
	}

	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
	public boolean isLoggedIn() {
		return session.isOrganizationLogged();
	}
}
