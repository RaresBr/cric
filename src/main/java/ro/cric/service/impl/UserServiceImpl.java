package ro.cric.service.impl;

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
	public boolean doesUsernameExist(String username, Long id) {
		return userDao.doesUsernameExist(username, id);
	}

	@Override
	@Transactional
	public void register(User user) {
		userDao.persist(user);
	}

}
