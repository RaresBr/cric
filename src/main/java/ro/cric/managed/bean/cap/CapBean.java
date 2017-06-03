package ro.cric.managed.bean.cap;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.xml.sax.SAXParseException;

import com.google.publicalerts.cap.Alert;
import com.google.publicalerts.cap.Area;
import com.google.publicalerts.cap.CapException;
import com.google.publicalerts.cap.CapValidator;
import com.google.publicalerts.cap.CapXmlBuilder;
import com.google.publicalerts.cap.CapXmlParser;
import com.google.publicalerts.cap.Circle;
import com.google.publicalerts.cap.Info;
import com.google.publicalerts.cap.Info.Category;
import com.google.publicalerts.cap.Info.Certainty;
import com.google.publicalerts.cap.Info.ResponseType;
import com.google.publicalerts.cap.Info.Urgency;
import com.google.publicalerts.cap.NotCapException;
import com.google.publicalerts.cap.Point;
import com.google.publicalerts.cap.XmlSigner;

import ro.cric.component.SessionData;
import ro.cric.model.Organization;
import ro.cric.service.AlertService;
import ro.cric.service.OrganizationService;

@ManagedBean(name = "capBean")
@ViewScoped
public class CapBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{sessionComponent}")
	private SessionData session;

	@ManagedProperty("#{alertService}")
	private AlertService alertService;

	private MapModel emptyModel = new DefaultMapModel();

	private Organization organization;

	private String capXml;

	private String capXmlParsed;

	private String message;

	private String areaDescription;

	private double latitude;

	private double longitude;

	private double radius;

	private String event;

	private Urgency urgency;

	private Category category;

	private ResponseType responseType;

	private Certainty certainty;

	public Urgency[] getUrgencies() {
		return Urgency.values();
	}

	public Category[] getCategories() {
		return Category.values();
	}

	public ResponseType[] getResponseTypes() {
		return ResponseType.values();
	}

	public Certainty[] getCertainties() {
		return Certainty.values();
	}

	@PostConstruct
	private void init() {
		organization = session.getLoggedOrganization();
	}

	public void addMarker() {
		Marker marker = new Marker(new LatLng(latitude, longitude), event);
		emptyModel.addOverlay(marker);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Alert added",
				"Latitude:" + latitude + ", Longitude:" + longitude));
	}

	public String callCap() {

		Alert parsedAlert = null;
		String signedXml;
		Alert alert = generateAlert();
		// Write it out to XML
		CapXmlBuilder builder = new CapXmlBuilder();
		// String xml = builder.toXml(alert);
		capXml = builder.toXml(alert);

		// Sign it
		try {
			XmlSigner signer = XmlSigner.newInstanceWithRandomKeyPair();
			signedXml = signer.sign(capXml);
			CapXmlParser parser = new CapXmlParser(true);
			// Parse it, with validation
			parsedAlert = parser.parseFrom(signedXml);
		} catch (NotCapException | SAXParseException | CapException e) {
			e.printStackTrace();
		}

		capXmlParsed = parsedAlert.toString();
		System.out.println(capXml);
		alertService.addAlert(organization, alert);
		return "cap?faces-redirect=true";
	}

	public Alert generateAlert() {
		TimeZone tz = TimeZone.getTimeZone("UTC");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		dateFormat.setTimeZone(tz);
		Date sentDate = null;

		// Generate a CAP document
		String date;
		Alert alert = Alert.newBuilder().setXmlns(CapValidator.CAP_LATEST_XMLNS)
				.setIdentifier(organization.getOrganizationId().toString()).setSender(organization.getEmail())
				.setSent(date = dateFormat.format(sentDate = new Date()).concat("-00:00"))
				.setStatus(Alert.Status.ACTUAL).setMsgType(Alert.MsgType.ALERT).setScope(Alert.Scope.PUBLIC)
				.addInfo(
						Info.newBuilder().setEvent(event).setUrgency(urgency).setSeverity(Info.Severity.SEVERE)
								.setCertainty(certainty).addCategory(category).addResponseType(responseType)
								.addArea(Area.newBuilder().setAreaDesc(areaDescription).addCircle(Circle.newBuilder()
										.setPoint(Point.newBuilder().setLatitude(latitude).setLongitude(longitude))
										.setRadius(radius).build()))
								.build())
				.buildPartial();

		return alert;
	}

	public CapBean() {

	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getAreaDescription() {
		return areaDescription;
	}

	public void setAreaDescription(String areaDescription) {
		this.areaDescription = areaDescription;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public AlertService getAlertService() {
		return alertService;
	}

	public void setAlertService(AlertService alertService) {
		this.alertService = alertService;
	}

	public String getCapXml() {
		return capXml;
	}

	public void setCapXml(String capXml) {
		this.capXml = capXml;
	}

	public SessionData getSession() {
		return session;
	}

	public void setSession(SessionData session) {
		this.session = session;
	}

	public Organization getUser() {
		return organization;
	}

	public void setUser(Organization organization) {
		this.organization = organization;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Urgency getUrgency() {
		return urgency;
	}

	public void setUrgency(Urgency urgency) {
		this.urgency = urgency;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public ResponseType getResponseType() {
		return responseType;
	}

	public void setResponseType(ResponseType responseType) {
		this.responseType = responseType;
	}

	public Certainty getCertainty() {
		return certainty;
	}

	public void setCertainty(Certainty certainty) {
		this.certainty = certainty;
	}

	public String getCapXmlParsed() {
		return capXmlParsed;
	}

	public void setCapXmlParsed(String capXmlParsed) {
		this.capXmlParsed = capXmlParsed;
	}

	public MapModel getEmptyModel() {
		return emptyModel;
	}

	public void setEmptyModel(MapModel emptyModel) {
		this.emptyModel = emptyModel;
	}

}
