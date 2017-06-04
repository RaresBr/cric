package ro.cric.view;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;


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
	
	public void onReturnFromDialog(SelectEvent event) throws IOException {
		if(event.getObject().toString().equals("true")) {
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			ec.redirect("/pages/dashboard.xhtml");
		}
	}
}