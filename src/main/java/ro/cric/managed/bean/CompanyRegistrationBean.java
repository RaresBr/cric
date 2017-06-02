package ro.cric.managed.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import ro.cric.model.Company;
import ro.cric.service.CompanyService;

@ManagedBean(name = "companyRegistrationBean")
@ViewScoped
public class CompanyRegistrationBean {
	
	@ManagedProperty("#{companyService")
	private CompanyService companyService;
	
	private Company company = new Company();
	
	public CompanyService getCompanyService() {
		return companyService;
	}

	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	public String registerCompany() {
		companyService.register(company);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("The company " + this.company.getCompanyName()
		+ " Is Registered Successfully"));
		return "";
	}

}
