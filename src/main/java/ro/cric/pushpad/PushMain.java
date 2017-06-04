package ro.cric.pushpad;

import java.util.Map;

import javax.faces.bean.ManagedBean;
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

import xyz.pushpad.DeliveryException;
import xyz.pushpad.Pushpad;

@ManagedBean(name = "sendpushbeannot")
@ViewScoped
public class PushMain {
	public String sendNotification() {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();

		String message = params.get("message");
		String title = params.get("title");
		String authToken = "9061dd8893ecb319f043c926fd257cf7";
		String projectId = "3675";
		Pushpad pushpad = new Pushpad(authToken, projectId);
		xyz.pushpad.Notification notification = pushpad.buildNotification(title, message, "http://example.com");

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
		System.out.println("Called pushnotification method");
		System.out.println(message);
		System.out.println(title);
		//
		//
		try {

			// // deliver the notification to a user
			// notification.deliverTo("user100");
			//
			// // deliver the notification to a group of users
			// String[] users = {"user123", "user100"};
			// notification.deliverTo(users);
			//
			// // deliver to some users only if they have a given preference
			// // e.g. only "users" who have a interested in "events" will be
			// reached
			// String[] tags1 = {"events"};
			// notification.deliverTo(users, tags1);
			//
			// // deliver to segments
			// // e.g. any subscriber that has the tag "segment1" OR "segment2"
			// String[] tags2 = {"segment1", "segment2"};
			// notification.broadcast(tags2);
			//
			// // you can use boolean expressions
			// // they must be in the disjunctive normal form (without
			// parenthesis)
			// String[] tags3 = {"zip_code:28865 && !optout:local_events ||
			// friend_of:Organizer123"};
			// notification.broadcast(tags3);
			// String[] tags4 = {"tag1 && tag2", "tag3"}; // equal to "tag1 &&
			// tag2 || tag3"
			// notification.deliverTo(users, tags4);
			//
			// deliver to everyone
			notification.broadcast();
		} catch (DeliveryException e) {
			e.printStackTrace();
		}
		return "";
	}

}
