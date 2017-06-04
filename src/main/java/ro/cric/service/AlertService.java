package ro.cric.service;

import ro.cric.model.Alert;
import ro.cric.model.Organization;

public interface AlertService {

	public Alert getAlertById(Long id);
	public boolean addAlert(Organization organization, com.google.publicalerts.cap.Alert alert);
}
