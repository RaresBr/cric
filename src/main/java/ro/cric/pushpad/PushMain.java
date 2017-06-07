package ro.cric.pushpad;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.xml.sax.SAXParseException;

import com.google.publicalerts.cap.Alert;
import com.google.publicalerts.cap.CapException;
import com.google.publicalerts.cap.CapXmlBuilder;
import com.google.publicalerts.cap.CapXmlParser;
import com.google.publicalerts.cap.NotCapException;
import com.google.publicalerts.cap.XmlSigner;

import ro.cric.managed.bean.cap.CapBean;
import xyz.pushpad.DeliveryException;
import xyz.pushpad.Pushpad;

@ManagedBean(name = "pushBean")
@SessionScoped
public class PushMain {
	@ManagedProperty(value = "#{capBean}")
	private CapBean neededBean;

	private String pushMessage;
	private String pushArea;
	private String pushCategory;
	private String pushUrgency;

	public String sendNotification() {
		pushMessage = neededBean.getMessage();
		pushArea = neededBean.getAreaDescription();
		pushCategory = neededBean.getCategory().name();
		pushUrgency = neededBean.getUrgency().name();
		String pushFullMessage = pushMessage + " " + pushUrgency + " " + pushCategory;
		String authToken = "9061dd8893ecb319f043c926fd257cf7";
		String projectId = "3675";
		Pushpad pushpad = new Pushpad(authToken, projectId);
		xyz.pushpad.Notification notification = pushpad.buildNotification(pushArea, pushFullMessage,"http://example.com");

		// optional, defaults to the project icon
		// notification.iconUrl = "http://example.com/assets/icon.png";
		// optional, drop the notification after this number of seconds if a
		// device is offline
		// notification.ttl = 604800;
		// optional, add some action buttons to the notification
		// see https://pushpad.xyz/docs/action_buttons
		// ActionButton button1 = new ActionButton("My Button 1"); // Title (max
		// length is 20 characters)
		// button1.targetUrl = "http://example.com/button-link"; // optional
		// button1.icon = "http://example.com/assets/button-icon.png"; //
		// optional
		// button1.action = "myActionName"; // optional
		// notification.actionButtons = new ActionButton[]{button1};
//		System.out.println("Called pushnotification method");
//		System.out.println(pushArea);
//		System.out.println(pushFullMessage);
		try {
			notification.broadcast();
		} catch (DeliveryException e) {
			e.printStackTrace();
		}
		return "";
	}

	public CapBean getNeededBean() {
		return neededBean;
	}

	public void setNeededBean(CapBean neededBean) {
		this.neededBean = neededBean;
	}

	public String getPushMessage() {
		return pushMessage;
	}

	public void setPushMessage(String pushMessage) {
		this.pushMessage = pushMessage;
	}

	public String getPushArea() {
		return pushArea;
	}

	public void setPushArea(String pushArea) {
		this.pushArea = pushArea;
	}

	public String getPushCategory() {
		return pushCategory;
	}

	public void setPushCategory(String pushCategory) {
		this.pushCategory = pushCategory;
	}

	public String getPushUrgency() {
		return pushUrgency;
	}

	public void setPushUrgency(String pushUrgency) {
		this.pushUrgency = pushUrgency;
	}

}
