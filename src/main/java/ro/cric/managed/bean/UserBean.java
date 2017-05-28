package ro.cric.managed.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import ro.cric.component.SessionData;
import ro.cric.model.User;
import ro.cric.service.UserService;

@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean {

	private User user;

	@ManagedProperty(value = "#{userService}")
	private UserService userService;

	@ManagedProperty(value = "#{sessionComponent}")
	private SessionData session;

	public UserBean() {

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public SessionData getSession() {
		return session;
	}

	public void setSession(SessionData session) {
		this.session = session;
	}

	public boolean isLoggedIn() {
		return session.isLogged();
	}

	public String home() {
		if (session.getLoggedUser() == null)
			return "/pages/home.xhtml";
		return "/pages/dashboard.xhtml";
	}

}
