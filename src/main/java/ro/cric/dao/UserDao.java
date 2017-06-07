package ro.cric.dao;

import java.util.List;
import java.util.Set;

import ro.cric.model.User;

public interface UserDao extends GenericDao<User> {

	public boolean doesEmailExist(String email);

	public boolean doesUsernameExist(String username);

	public User getByCredentials(String password, String username);
	
	public Set<User> getAllFriends(User user);
	
	public void addFriend(User requestee, User toBeFriended);
	
	public User getUserByUsername(String username);
}
