package ro.cric.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.publicalerts.cap.Info;

import ro.cric.dao.AlertDao;
import ro.cric.model.Alert;
import ro.cric.model.Organization;
import ro.cric.service.AlertService;

@Service(value = "alertService")
public class AlertServiceImpl implements AlertService {

	@Autowired
	private AlertDao alertDao;

	@Override
	public Alert getAlertById(Long id) {
		return alertDao.getById(id);
	}

	@Override
	@Transactional
	public boolean addAlert(Organization organization, com.google.publicalerts.cap.Alert alert) {
		String sentAt = alert.getSent();
		List<Info> info = alert.getInfoList();
		String urgency = info.get(0).getUrgency().toString();
		String severity = info.get(0).getSeverity().toString();
		String category = info.get(0).getCategory(0).toString();
		double radius = info.get(0).getArea(0).getCircle(0).getRadius();
		double latitude = info.get(0).getArea(0).getCircle(0).getPoint().getLatitude();
		double longitude = info.get(0).getArea(0).getCircle(0).getPoint().getLongitude();

		ro.cric.model.Alert alertModel = new ro.cric.model.Alert();
		alertModel.setCategory(category);
		alertModel.setSentAt(sentAt);
		alertModel.setSeverity(severity);
		alertModel.setUrgency(urgency);
		alertModel.setLatitude(latitude);
		alertModel.setLongitude(longitude);
		alertModel.setRadius(radius);

		alertModel.setOrganization(organization);

		Alert alertPersisted = alertDao.persist(alertModel);
		if(alertPersisted != null)
			return true;
		return false;

	}
}
