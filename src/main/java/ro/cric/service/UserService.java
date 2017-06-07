package ro.cric.service;

import java.util.List;

import com.google.publicalerts.cap.Alert;

import ro.cric.model.User;

public interface UserService {
	
	public User getUserById(long id);
	public boolean doesEmailExist(String email);
	public boolean doesUsernameExist(String username);
	public User getUserByCredentials(String password, String username);
	public void register(User user);
	public void notifyUsersInTheArea(Alert area, String capXml);
	public List<User> getAllUsers();
	public boolean changePassword(Long id, String oldPassword, String newPassword);
}