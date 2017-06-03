package ro.cric.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.cric.dao.UserDao;
import ro.cric.model.User;
import ro.cric.service.UserService;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	@Transactional
	public User getUserById(long id) {
		return userDao.getById(id);
	}

	@Override
	@Transactional
	public boolean doesEmailExist(String email) {
		return userDao.doesEmailExist(email);
	}

	@Override
	@Transactional
	public boolean doesUsernameExist(String username) {
		return userDao.doesUsernameExist(username);
	}

	@Override
	@Transactional
	public void register(User user) {
		userDao.persist(user);
	}

	@Override
	public User getUserByCredentials(String password, String username) {
		return userDao.getByCredentials(password, username);
	}

	public List<User> getAllFriends(User user) {
		return userDao.getAllFriends(user);
	}
	@Transactional
	public void addFriend(User requestee, User toBeFriended) {
		userDao.addFriend(requestee, toBeFriended);
	}

	@Override
	public User getUserByUsername(String username) {
		return userDao.getUserByUsername(username);
	}

}
