package ro.cric.managed.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import ro.cric.component.SessionData;
import ro.cric.model.User;
import ro.cric.service.UserService;

@ManagedBean(name = "loginBean")
@ViewScoped
public class LoginBean {
	
	private String username;
	private String password;

	@ManagedProperty("#{userService}")
	private UserService userService;

	@ManagedProperty("#{sessionComponent}")
	private SessionData session;

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

	public String login() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		FacesMessage message = null;
		boolean loggedIn = false;
		if (username != null && password != null) {
			User user = userService.getUserByCredentials(password, username);
			if (user != null) {
				message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", username);
				loggedIn = true;
				session.setLoggedUser(user);
				System.out.println(session.getLoggedUser().getFirstName());

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
