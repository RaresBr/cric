package ro.cric.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.cric.dao.AlertDao;
import ro.cric.model.Alert;
import ro.cric.service.AlertService;

@Service(value = "alertService")
public class AlertServiceImpl implements AlertService {

	@Autowired
	private AlertDao alertDao;

	@Override
	public Alert getAlertById(Long id) {
		return alertDao.getById(id);
	}
}
