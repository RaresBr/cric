 package ro.cric.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.dao.DataIntegrityViolationException;
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
	public boolean doesUsernameExist(String username) {
		TypedQuery<User> query = entityManager
				.createQuery("select u From User as u where u.username=:id_username", User.class)
				.setParameter("id_username", username);

		List<User> list = query.getResultList();

		if (list.isEmpty())
			return false;

		return true;
	}

	@Override
	public User getByCredentials(String password, String username) {
		TypedQuery<User> query = entityManager
				.createQuery(
						"select u From User as u where u.username=:searched_username and u.password=:searched_password",
						User.class)
				.setParameter("searched_username", username).setParameter("searched_password", password);
		List<User> list = query.getResultList();

		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	public List<User> getAllFriends(User user){
		return user.getFriends();
	}
	
	public void addFriend(User requestee, User toBeFriended) throws DataIntegrityViolationException {
		if(!doesUsernameExist(toBeFriended.getUsername()))
			return;
		if(requestee.getFriends().contains(toBeFriended))
			return;
		System.out.println("IM AFTER RETURN IN DAO");
		requestee.getFriends().add(toBeFriended);
		requestee.getFriendOf().add(toBeFriended);
		System.out.println("IM AFTER ADD IN DAO");
		this.persist(requestee);
		System.out.println("IM AFTER PERSIST IN DAO");
	}


	@Override
	public User getUserByUsername(String username) {
		TypedQuery<User> query = entityManager
				.createQuery(
						"select u From User as u where u.username=:searched_username",
						User.class)
				.setParameter("searched_username", username);
		List<User> list = query.getResultList();

		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}


}
