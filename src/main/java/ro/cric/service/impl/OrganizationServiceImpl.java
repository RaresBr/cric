package ro.cric.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.publicalerts.cap.Alert;
import com.google.publicalerts.cap.Info;

import ro.cric.dao.OrganizationDao;
import ro.cric.model.Organization;
import ro.cric.service.OrganizationService;

@Service(value = "organizationService")
public class OrganizationServiceImpl implements OrganizationService {

	@Autowired
	private OrganizationDao organizationDao;

	@Override
	@Transactional
	public Organization getOrganizationById(long id) {
		return organizationDao.getById(id);
	}

	@Override
	@Transactional
	public boolean doesEmailExist(String email) {
		return organizationDao.doesEmailExist(email);
	}

	@Override
	@Transactional
	public boolean doesUsernameExist(String username) {
		return organizationDao.doesUsernameExist(username);
	}

	@Override
	@Transactional
	public void register(Organization organization) {
		organizationDao.persist(organization);
	}

	@Override
	public Organization getOrganizationByCredentials(String password, String username) {
		return organizationDao.getByCredentials(password, username);
	}

}