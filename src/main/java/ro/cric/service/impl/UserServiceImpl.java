package ro.cric.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.publicalerts.cap.Alert;

import ro.cric.component.EmailComponent;
import ro.cric.dao.UserDao;
import ro.cric.model.User;
import ro.cric.service.UserService;
import ro.cric.util.Haversine;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private EmailComponent emailSender;

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
	public void notifyUsersInTheArea(Alert alert, String capXml) {
		double latitude = alert.getInfoList().get(0).getArea(0).getCircle(0).getPoint().getLatitude();
		double longitude = alert.getInfoList().get(0).getArea(0).getCircle(0).getPoint().getLongitude();
		double radius = alert.getInfoList().get(0).getArea(0).getCircle(0).getRadius();
		//String from = alert.getSender();
		String subject = alert.getInfoList().get(0).getDescription();

		List<User> allUsers = userDao.getAll();
		for (User currentUser : allUsers) {
			if (currentUser.getLatitude() != null && currentUser.getLongitude() != null) {
				double distanceBetweenUserAndAlertLocation = Haversine.distance(currentUser.getLatitude(),
						currentUser.getLongitude(), latitude, longitude);
				if (distanceBetweenUserAndAlertLocation <= radius) {
					emailSender.sendSimpleMessage(currentUser.getEmail(), subject, capXml);
				} else
					System.out.println("User " + currentUser.getEmail() + " outside the radius");
			}

		}

	}

	@Override
	public List<User> getAllUsers() {
		return userDao.getAll();
	}

	public EmailComponent getEmailSender() {
		return emailSender;
	}

	public void setEmailSender(EmailComponent emailSender) {
		this.emailSender = emailSender;
	}

	@Override
	@Transactional
	public boolean changePassword(Long id, String oldPassword, String newPassword) {
		User user = userDao.getById(id);
		if (oldPassword != null && newPassword != null) {
			user.setPassword(newPassword);
			userDao.persist(user);
			return true;
		}
		return false;

	}

}
