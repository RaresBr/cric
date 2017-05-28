package ro.cric.cap;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.xml.sax.SAXParseException;

import com.google.publicalerts.cap.Alert;
import com.google.publicalerts.cap.CapException;
import com.google.publicalerts.cap.CapValidator;
import com.google.publicalerts.cap.CapXmlBuilder;
import com.google.publicalerts.cap.CapXmlParser;
import com.google.publicalerts.cap.Group;
import com.google.publicalerts.cap.NotCapException;
import com.google.publicalerts.cap.XmlSigner;

@ManagedBean(name = "caplib")
@RequestScoped
public class CapMain {
	
	private String capXml ="placeholder";
	public String callCap() throws SAXParseException, NotCapException, CapException {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
	  String sender = params.get("sender");
	  String message = params.get("message");
		// Generate a CAP document
		Alert alert = Alert.newBuilder().setXmlns(CapValidator.CAP_LATEST_XMLNS).setIdentifier("43b080713727")
				.setSender(sender).setSent("2003-04-02T14:39:01-05:00").setStatus(Alert.Status.ACTUAL)
				.setMsgType(Alert.MsgType.ALERT).setSource("a source").setScope(Alert.Scope.PUBLIC)
				.setAddresses(Group.newBuilder().addValue("address 1").addValue("address2").build()).addCode("abcde")
				.addCode("fghij").setNote("a note")
				.setNote(message)
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
		System.out.println(sender);
		System.out.println(message);
		capXml = xml.toString();
		 return xml.toString();
		// id = "delete"
	}
	public String getCapXml() {
		return capXml;
	}
	public void setCapXml(String capXml) {
		this.capXml = capXml;
	}


}
