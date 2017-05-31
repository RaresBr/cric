package ro.cric.managed.bean;

import javax.annotation.PostConstruct;
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
	public void init(){
		this.user = sessionData.getLoggedUser();
	}
	
	public void submit(){
		System.out.println(latitude);
	}
	public String action(){
		   String value = FacesContext.getCurrentInstance().
			getExternalContext().getRequestParameterMap().get("myForm:userId");
		   System.out.println(value);
		return value;
		}
	public void updateLocation(){
		user.setLongitude(longitude);
		user.setLatitude(latitude);
		userService.register(user);
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
