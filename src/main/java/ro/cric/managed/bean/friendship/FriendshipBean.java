package ro.cric.managed.bean.friendship;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.dao.DataIntegrityViolationException;

import ro.cric.component.SessionData;
import ro.cric.model.User;
import ro.cric.service.UserService;

@ManagedBean(name = "friendshipBean")
@ViewScoped
public class FriendshipBean {
	private User user;
	private String friendUsername;

	public String getFriendUsername() {
		return friendUsername;
	}

	public void setFriendUsername(String friendUsername) {
		this.friendUsername = friendUsername;
	}

	@ManagedProperty("#{userService}")
	private UserService userService;

	@ManagedProperty("#{sessionComponent}")
	private SessionData sessionData;

	@PostConstruct
	public void init() {
		this.user = sessionData.getLoggedUser();
	}

	public void addUser() {
		User friend = userService.getUserByUsername(friendUsername);
		userService.addFriend(user, friend);

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("friendship.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}

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

	public SessionData getSessionData() {
		return sessionData;
	}

	public void setSessionData(SessionData sessionData) {
		this.sessionData = sessionData;
	}

}
