package ro.cric.view;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;

import org.primefaces.context.RequestContext;


@ManagedBean(name = "registerOrganizationView")
public class RegisterOrganizationView {
	
	public void viewRegisterForm() {
		Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("width", 640);
        options.put("height", 340);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("headerElement", "customheader");
		RequestContext.getCurrentInstance().openDialog("/pages/registerOrganization.xhtml", options, null);
	}
}