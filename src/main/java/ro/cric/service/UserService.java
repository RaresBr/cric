package ro.cric.service;

import java.util.List;

import com.google.publicalerts.cap.Alert;
import java.util.Set;

import ro.cric.model.User;

public interface UserService {

	User getUserById(long id);

	boolean doesEmailExist(String email);

	boolean doesUsernameExist(String username);

	User getUserByCredentials(String password, String username);

	void register(User user);

	void notifyUsersInTheArea(Alert area, String capXml);

	List<User> getAllUsers();

	boolean changePassword(Long id, String oldPassword, String newPassword);

	User getUserByUsername(String username);

	Set<User> getAllFriends(User user);

	void addFriend(User requestee, User toBeFriended);
}