package ro.cric.managed.bean.geo;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import ro.cric.component.SessionData;
import ro.cric.model.User;
import ro.cric.service.UserService;

@ManagedBean(name = "geoBean")
@ViewScoped
public class GeoBean {
	private User user;

	@ManagedProperty("#{userService}")
	private UserService userService;

	@ManagedProperty("#{sessionComponent}")
	private SessionData sessionData;

	private double longitude;
	private double latitude;

	@PostConstruct
	public void init() {
		this.user = sessionData.getLoggedUser();
	}

	public void submit() {
		System.out.println(latitude);
	}

	/*
	 * public void action() { longitude =
	 * Double.parseDouble(FacesContext.getCurrentInstance().getExternalContext()
	 * .getRequestParameterMap() .get("geolocationForm:longitude")); latitude =
	 * Double.parseDouble(
	 * FacesContext.getCurrentInstance().getExternalContext().
	 * getRequestParameterMap().get("geolocationForm:latitude"));
	 * System.out.println(longitude); System.out.println(latitude); }
	 */
	public void updateLocation() {
		user.setLongitude(longitude);
		user.setLatitude(latitude);
		userService.register(user);
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Location updated", "");
		FacesContext.getCurrentInstance().addMessage(null, message);
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

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

}
