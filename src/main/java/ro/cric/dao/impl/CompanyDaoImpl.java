package ro.cric.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import ro.cric.dao.CompanyDao;
import ro.cric.model.Company;

@Repository
public class CompanyDaoImpl extends GenericDaoImpl<Company> implements CompanyDao {

	@Override
	public boolean doesEmailExist(String email) {
		TypedQuery<Long> query = entityManager
				.createQuery("select count(company.id) from Company company where email=:email", Long.class)
				.setParameter("email", email);

		return query.getSingleResult().intValue() == 1 ? true : false;
	}

	@Override
	public boolean doesUsernameExist(String username, Long id) {
		TypedQuery<Company> query = entityManager
				.createQuery("select c From Company as c where c.username=:id_username and c.id!=:company_id", Company.class)
				.setParameter("id_username", username).setParameter("user_id", id);

		List<Company> list = query.getResultList();

		if (list.isEmpty())
			return false;

		return true;
	}

	@Override
	public Company getByCredentials(String password, String username) {
		TypedQuery<Company> query = entityManager
				.createQuery("select c From Company as c where c.username=:searched_username and c.password=:searched_password",
						Company.class)
				.setParameter("searched_username", username).setParameter("searched_password", password);
		List<Company> list = query.getResultList();
		
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

}