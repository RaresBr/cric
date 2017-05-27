package ro.cric.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import ro.cric.dao.UserDao;
import ro.cric.model.User;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

	@Override
	public boolean doesEmailExist(String email) {
		TypedQuery<Long> query = entityManager
				.createQuery("select count(user.id) from User user where email=:email", Long.class)
				.setParameter("email", email);

		return query.getSingleResult().intValue() == 1 ? true : false;
	}

	@Override
	public boolean doesUsernameExist(String username, Long id) {
		TypedQuery<User> query = entityManager
				.createQuery("select u From User as u where u.username=:id_username and u.id!=:user_id", User.class)
				.setParameter("id_username", username).setParameter("user_id", id);

		List<User> list = query.getResultList();

		if (list.isEmpty())
			return false;

		return true;
	}

}
