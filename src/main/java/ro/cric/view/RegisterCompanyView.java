package ro.cric.view;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;

import org.primefaces.context.RequestContext;


@ManagedBean(name = "registerCompanyView")
public class RegisterCompanyView {
	
	public void viewRegisterForm() {
		Map<String,Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
		RequestContext.getCurrentInstance().openDialog("registerCompany", options, null);
	}

}