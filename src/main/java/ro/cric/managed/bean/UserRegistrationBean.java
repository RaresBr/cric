package ro.cric.managed.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import ro.cric.model.User;
import ro.cric.service.UserService;

@ManagedBean(name = "userRegistrationBean")
@ViewScoped
public class UserRegistrationBean {

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
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		FacesMessage message = null;
		if ((userService.doesEmailExist(user.getEmail()))) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Register error", "Email already in use");
		} else if (userService.doesUsernameExist(user.getUsername())) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Register error", "Username already in use");
		} else {
			userService.register(user);
			message = new FacesMessage("The user " + this.user.getFirstName() + " " + this.user.getLastName()
					+ " Is Registered Successfully");
		}

		FacesContext.getCurrentInstance().addMessage(null, message);
		ec.getFlash().setKeepMessages(true);

		return "/pages/home";
	}
}