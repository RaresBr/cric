package ro.cric.managed.bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.swing.text.DateFormatter;

import org.primefaces.event.map.GeocodeEvent;
import org.xml.sax.SAXParseException;

import com.google.publicalerts.cap.Alert;
import com.google.publicalerts.cap.Area;
import com.google.publicalerts.cap.CapException;
import com.google.publicalerts.cap.CapValidator;
import com.google.publicalerts.cap.CapXmlBuilder;
import com.google.publicalerts.cap.CapXmlParser;
import com.google.publicalerts.cap.Circle;
import com.google.publicalerts.cap.Group;
import com.google.publicalerts.cap.Info;
import com.google.publicalerts.cap.NotCapException;
import com.google.publicalerts.cap.Point;
import com.google.publicalerts.cap.ValuePair;
import com.google.publicalerts.cap.XmlSigner;
import com.google.publicalerts.cap.Info.Category;
import com.google.publicalerts.cap.Info.ResponseType;
import com.google.publicalerts.cap.Info.Urgency;

import ro.cric.component.SessionData;
import ro.cric.model.User;
import ro.cric.service.AlertService;

@ManagedBean(name = "capBean")
@RequestScoped
public class CapBean {

	@ManagedProperty("#{sessionComponent}")
	private SessionData session;

	@ManagedProperty("#{alertService}")
	private AlertService alertService;

	private User user;

	private String capXml;

	private String message;
	
	private String areaDescription;
	
	private double radius;
	
	private String event;

	@PostConstruct
	private void init() {
		user = session.getLoggedUser();

	}

	public String callCap() throws SAXParseException, NotCapException, CapException {
		TimeZone tz = TimeZone.getTimeZone("UTC");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		dateFormat.setTimeZone(tz);
		Date sentDate = null;

		// Generate a CAP document
		String date;
		Alert alert = Alert.newBuilder().setXmlns(CapValidator.CAP_LATEST_XMLNS).setIdentifier("43b080713727")
				.setSender(user.getEmail()).setSent(date = dateFormat.format(sentDate = new Date()).concat("-00:00"))
				.setStatus(Alert.Status.ACTUAL).setMsgType(Alert.MsgType.ALERT).setScope(Alert.Scope.PUBLIC)
				.setAddresses(Group.newBuilder().addValue("address 1").addValue("address2").build()).addCode("abcde")
				.addCode("fghij").setNote("a note").setNote(message)
				.setIncidents(Group.newBuilder().addValue("incident1").addValue("incident2").build()).buildPartial();
		Alert alert1 = Alert.newBuilder().setXmlns(CapValidator.CAP_LATEST_XMLNS)
				.setIdentifier(user.getUserId().toString()).setSender(user.getEmail())
				.setSent(date = dateFormat.format(sentDate = new Date()).concat("-00:00"))
				.setStatus(Alert.Status.ACTUAL).setMsgType(Alert.MsgType.ALERT).setScope(Alert.Scope.PUBLIC)
				.addInfo(
						Info.newBuilder().setEvent("Homeland Security Advisory System Update")
								.setUrgency(Info.Urgency.IMMEDIATE).setSeverity(Info.Severity.SEVERE).setCertainty(
										Info.Certainty.LIKELY)
								.addCategory(Category.FIRE).addResponseType(ResponseType.EVACUATE)
								.addArea(Area.newBuilder().setAreaDesc("Iasi").addCircle(Circle.newBuilder()
										.setPoint(Point.newBuilder().setLatitude(47.157972).setLongitude(27.60520935))
										.setRadius(3).build()))
								.build())
				.buildPartial();

		// Write it out to XML
		CapXmlBuilder builder = new CapXmlBuilder();
		String xml = builder.toXml(alert);
		capXml = builder.toXml(alert1);

		// Sign it
		XmlSigner signer = XmlSigner.newInstanceWithRandomKeyPair();
		String signedXml = signer.sign(xml);

		// Parse it, with validation
		CapXmlParser parser = new CapXmlParser(true);
		Alert parsedAlert = parser.parseFrom(signedXml);

		//capXml = parsedAlert.toString();
		return capXml;
		// id = "delete"
	}

	public CapBean() {

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
