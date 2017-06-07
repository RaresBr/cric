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

@ManagedBean(name = "changePasswordBean")
@ViewScoped
public class ChangePasswordBean {

	@ManagedProperty("#{sessionComponent}")
	private SessionData session;

	@ManagedProperty("#{userService}")
	private UserService userService;

	private String oldPassword;
	private String newPassword;

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public SessionData getSession() {
		return session;
	}

	public void setSession(SessionData session) {
		this.session = session;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String changePassword() {
		User user = session.getLoggedUser();
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		FacesMessage message = null;
		System.out.println(newPassword);
		if (userService.changePassword(user.getUserId(), oldPassword, newPassword)) {
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Password changed", null);
		} else
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Password incorrect", null);

		FacesContext.getCurrentInstance().addMessage(null, message);
		ec.getFlash().setKeepMessages(true);
		return "settings?faces-redirect=true";
	}
}
