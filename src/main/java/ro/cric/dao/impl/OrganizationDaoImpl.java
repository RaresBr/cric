package ro.cric.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import ro.cric.dao.OrganizationDao;
import ro.cric.model.Organization;

@Repository
public class OrganizationDaoImpl extends GenericDaoImpl<Organization> implements OrganizationDao  {
	
	@Override
	public boolean doesEmailExist(String email) {
		TypedQuery<Long> query = entityManager
				.createQuery("select count(organization.id) from Organization organization"
						+ " where email=:email", Long.class)
				.setParameter("email", email);

		return query.getSingleResult().intValue() == 1 ? true : false;
	}

	@Override
	public boolean doesUsernameExist(String username) {
		TypedQuery<Organization> query = entityManager
				.createQuery("select o From Organization as o where o.username=:id_username", Organization.class)
				.setParameter("id_username", username);

		List<Organization> list = query.getResultList();

		if (list.isEmpty())
			return false;

		return true;
	}

	@Override
	public Organization getByCredentials(String password, String username) {
		TypedQuery<Organization> query = entityManager
				.createQuery(
						"select o From Organization as o where o.username=:searched_username "
						+ "and o.password=:searched_password",
						Organization.class)
				.setParameter("searched_username", username).setParameter("searched_password", password);
		List<Organization> list = query.getResultList();

		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}


}
