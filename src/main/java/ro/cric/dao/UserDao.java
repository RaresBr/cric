package ro.cric.dao;

import java.util.List;

import ro.cric.model.User;

public interface UserDao extends GenericDao<User> {

	public boolean doesEmailExist(String email);

	public boolean doesUsernameExist(String username);

	public User getByCredentials(String password, String username);
	
	public List<User> getAllFriends(User user);
	
	public void addFriend(User requestee, User toBeFriended);
	
	public User getUserByUsername(String username);
}
