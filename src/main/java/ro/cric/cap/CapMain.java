package ro.cric.cap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.xml.sax.SAXParseException;

import com.google.publicalerts.cap.Alert;
import com.google.publicalerts.cap.CapException;
import com.google.publicalerts.cap.CapXmlBuilder;
import com.google.publicalerts.cap.CapXmlParser;
import com.google.publicalerts.cap.NotCapException;
import com.google.publicalerts.cap.XmlSigner;

@ManagedBean(name="caplib")
@SessionScoped
public class CapMain {
	public String callCap(String id) throws SAXParseException, NotCapException, CapException {
		// Generate a CAP document
        Alert alert = CapTestUtil.getValidAlertBuilder().build();

        // Write it out to XML
        CapXmlBuilder builder = new CapXmlBuilder();
        String xml = builder.toXml(alert);

        // Sign it
        XmlSigner signer = XmlSigner.newInstanceWithRandomKeyPair();
        String signedXml = signer.sign(xml);

        // Parse it, with validation
        CapXmlParser parser = new CapXmlParser(true);
        Alert parsedAlert = parser.parseFrom(signedXml);
        System.out.println(parsedAlert.getSender());
        System.out.println(parsedAlert.toString());
		return id;
		  //id = "delete"
		}
}

