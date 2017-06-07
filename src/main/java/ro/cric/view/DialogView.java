package ro.cric.view;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;

import org.primefaces.context.RequestContext;


@ManagedBean(name = "dialogView")
public class DialogView {
	
	public void viewOrganizationRegisterForm () {
		Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("width", 640);
        options.put("height", 340);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("headerElement", "customheader");
		RequestContext.getCurrentInstance().openDialog("/pages/registerOrganization" , options, null);
	}
	
	public void viewOrganizationLoginForm () {
		Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("width", 640);
        options.put("height", 340);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("headerElement", "customheader");
		RequestContext.getCurrentInstance().openDialog("/pages/loginOrganization" , options, null);
	}
}