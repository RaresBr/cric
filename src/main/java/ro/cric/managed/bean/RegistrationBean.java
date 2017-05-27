package ro.cric.managed.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.transaction.annotation.Transactional;

import ro.cric.model.User;
import ro.cric.service.UserService;

@ManagedBean(name = "registrationBean")
@ViewScoped
public class RegistrationBean {

	@ManagedProperty("#{userService}")
	private UserService userService;

	private User user = new User();

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

	public String register() {
		userService.register(user);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("The user " + this.user.getFirstName() + " "
				+ this.user.getLastName() + " Is Registered Successfully"));
		return "";
	}
}