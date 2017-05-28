package ro.cric.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ro.cric.model.User;
import ro.cric.service.UserService;

@Component(value = "sessionComponent")
@Scope(value = "session")
public class SessionData {

	@Autowired
	private UserService userService;
	private User loggedUser;

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

}
