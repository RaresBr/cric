package ro.cric.managed.bean.cap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.publicalerts.cap.Alert;
import com.google.publicalerts.cap.Area;
import com.google.publicalerts.cap.CapException;
import com.google.publicalerts.cap.CapValidator;
import com.google.publicalerts.cap.Circle;
import com.google.publicalerts.cap.Group;
import com.google.publicalerts.cap.Info;
import com.google.publicalerts.cap.Point;
import com.google.publicalerts.cap.Polygon;
import com.google.publicalerts.cap.Reason;
import com.google.publicalerts.cap.Reasons;
import com.google.publicalerts.cap.Resource;
import com.google.publicalerts.cap.ValuePair;


import java.util.Map.Entry;

/**
 * Test utilities.
 *
* @author shakusa@google.com (Steve Hakusa)
 */
public class CapTestUtil {
  public static Alert.Builder getValidAlertBuilder() {
    return Alert.newBuilder()
        .setXmlns(CapValidator.CAP_LATEST_XMLNS)
        .setIdentifier("43b080713727")
        .setSender("hsas@dhs.gov")
        .setSent("2003-04-02T14:39:01-05:00")
        .setStatus(Alert.Status.ACTUAL)
        .setMsgType(Alert.MsgType.ALERT)
        .setSource("a source")
        .setScope(Alert.Scope.PUBLIC)
        .addCode("abcde")
        .setNote("a note")
        .setReferences(Group.newBuilder()
            .addValue("hsas@dhs.gov,123,2003-02-02T14:39:01-05:00")
            .addValue("hsas@dhs.gov,456,2003-03-02T14:39:01-05:00").build())
        .setIncidents(Group.newBuilder()
            .addValue("incident1").addValue("incident2").build())
        .addInfo(getValidInfoBuilder());
  }

  public static Info.Builder getValidInfoBuilder() {
    return Info.newBuilder()
        .addCategory(Info.Category.SECURITY)
        .addCategory(Info.Category.SAFETY)
        .setEvent("Homeland Security Advisory System Update")
        .setUrgency(Info.Urgency.IMMEDIATE)
        .setSeverity(Info.Severity.SEVERE)
        .setCertainty(Info.Certainty.LIKELY)
        .setSenderName("Department of Homeland Security")
        .setHeadline("Homeland Security Sets Code ORANGE")
        .setDescription("DHS has set the threat level to ORANGE.")
        .setInstruction("Take Protective Measures.")
        .setWeb("http://www.dhs.gov/dhspublic/display?theme=29")
        .addParameter(ValuePair.newBuilder()
            .setValueName("HSAS").setValue("ORANGE").build())
        .setAudience("an audience")
        .setContact("a contact")
        .addEventCode(ValuePair.newBuilder()
            .setValueName("EC").setValue("v1").build())
        .setEffective("2003-04-02T14:39:01-05:00")
        .setOnset("2003-04-02T15:39:01+05:00")
        .setExpires("2003-04-02T16:39:01-00:00")
        .addArea(getValidAreaBuilder())
        .addResource(getValidResourceBuilder());
  }

  public static Area.Builder getValidAreaBuilder() {
    return Area.newBuilder()
        .setAreaDesc("U.S. nationwide")
        .addPolygon(Polygon.newBuilder()
            .addPoint(Point.newBuilder().setLatitude(1).setLongitude(2).build())
            .addPoint(Point.newBuilder().setLatitude(3).setLongitude(4).build())
            .addPoint(Point.newBuilder().setLatitude(5).setLongitude(6).build())
            .addPoint(Point.newBuilder().setLatitude(1).setLongitude(2).build())
            .build())
        .addCircle(Circle.newBuilder()
            .setPoint(Point.newBuilder().setLatitude(1).setLongitude(2).build())
            .setRadius(0).build())
        .addGeocode(ValuePair.newBuilder()
            .setValueName("G1").setValue("v1").build())
        .setAltitude(5.5)
        .setCeiling(6.5);
  }

  public static Resource.Builder getValidResourceBuilder() {
    return Resource.newBuilder()
        .setResourceDesc("Image file (GIF)")
        .setUri("http://www.dhs.gov/dhspublic/getAdvisoryImage")
        .setMimeType("image/gif")
        .setSize(123);
  }
  
  /**
   * Asserts that the {@code expected} matches {@code actual}, where equality
   * between {@link Reason} objects is guaranteed if two objects have same:
   * <ul>
   * <li>reason type,
   * <li>XPath.
   * </ul>
   */
  
  
  /**
   * @see #assertReasons(Reasons, Reason...)
   */

  
  private CapTestUtil() {}
}

