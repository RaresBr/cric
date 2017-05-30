package ro.cric.managed.bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.swing.text.DateFormatter;

import org.xml.sax.SAXParseException;

import com.google.publicalerts.cap.Alert;
import com.google.publicalerts.cap.CapException;
import com.google.publicalerts.cap.CapValidator;
import com.google.publicalerts.cap.CapXmlBuilder;
import com.google.publicalerts.cap.CapXmlParser;
import com.google.publicalerts.cap.Group;
import com.google.publicalerts.cap.NotCapException;
import com.google.publicalerts.cap.XmlSigner;

import ro.cric.component.SessionData;
import ro.cric.model.User;

@ManagedBean(name = "capBean")
@RequestScoped
public class CapBean {

	@ManagedProperty("#{sessionComponent}")
	private SessionData session;

	private User user;

	private String capXml = "placeholder";

	private String message;

	@PostConstruct
	private void init() {
		user = session.getLoggedUser();

	}

	public String callCap() throws SAXParseException, NotCapException, CapException {
		// Generate a CAP document
		Alert alert = Alert.newBuilder().setXmlns(CapValidator.CAP_LATEST_XMLNS).setIdentifier("43b080713727")
				.setSender(user.getUsername()).setSent("2003-04-02T14:39:01-05:00").setStatus(Alert.Status.ACTUAL)
				.setMsgType(Alert.MsgType.ALERT).setScope(Alert.Scope.PUBLIC)
				.setAddresses(Group.newBuilder().addValue("address 1").addValue("address2").build()).addCode("abcde")
				.addCode("fghij").setNote("a note").setNote(message)
				.setIncidents(Group.newBuilder().addValue("incident1").addValue("incident2").build()).buildPartial();

		// Write it out to XML
		CapXmlBuilder builder = new CapXmlBuilder();
		String xml = builder.toXml(alert);

		// Sign it
		XmlSigner signer = XmlSigner.newInstanceWithRandomKeyPair();
		String signedXml = signer.sign(xml);

		// Parse it, with validation
		CapXmlParser parser = new CapXmlParser(true);
		Alert parsedAlert = parser.parseFrom(signedXml);
		System.out.println(user.getUsername());
		System.out.println(message);
		capXml = xml.toString();
		return xml.toString();
		// id = "delete"
	}

	public CapBean() {

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
