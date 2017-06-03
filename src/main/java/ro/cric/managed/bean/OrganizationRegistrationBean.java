package ro.cric.managed.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import ro.cric.model.Organization;
import ro.cric.service.OrganizationService;

@ManagedBean(name = "organizationRegistrationBean")
@ViewScoped
public class OrganizationRegistrationBean {

	@ManagedProperty("#{organizationService}")
	private OrganizationService organizationService;

	private Organization organization = new Organization();

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

	public String register() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		FacesMessage message = null;
		if ((organizationService.doesEmailExist(organization.getEmail()))) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Register error", "Email already in use");
		} else if (organizationService.doesUsernameExist(organization.getUsername())) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Register error", "Username already in use");
		} else {
			organizationService.register(organization);
			message = new FacesMessage("The organization with the username "
					+ this.organization.getUsername() + " is Registered Successfully");
		}

		FacesContext.getCurrentInstance().addMessage(null, message);
		ec.getFlash().setKeepMessages(true);

		return "/pages/home";
	}
}