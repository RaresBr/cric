package ro.cric;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import ro.cric.model.User;
import ro.cric.UserService;

@ManagedBean
@SessionScoped
public class RegisterUser {

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
		// Calling Business Service
		userService.register(user);
		// Add message
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage("The user "+ this.user.getFirstName() + " " +  this.user.getLastName() + 
						" Is Registered Successfully"));
		return "";
	}
}