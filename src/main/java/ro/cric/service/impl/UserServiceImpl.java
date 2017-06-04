package ro.cric.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.cric.dao.UserDao;
import ro.cric.model.User;
import ro.cric.service.UserService;
import ro.cric.util.Haversine;

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
	@Transactional
	public User getUserByCredentials(String password, String username) {
		return userDao.getByCredentials(password, username);
	}

	@Override
	@Transactional
	public void notifyUsersInTheArea(double latitude, double longitude, double radius) {
		List<User> allUsers = userDao.getAll();
		for (User currentUser : allUsers) {
			if (currentUser.getLatitude() != null && currentUser.getLongitude() != null) {
				double distanceBetweenUserAndAlertLocation = Haversine.distance(currentUser.getLatitude(),
						currentUser.getLongitude(), latitude, longitude);
				if (distanceBetweenUserAndAlertLocation <= radius)
					System.out.println("User " + currentUser.getEmail() + " inside the radius");
				else
					System.out.println("User " + currentUser.getEmail() + " outside the radius");
			}

		}

	}

}
