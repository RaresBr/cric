package ro.cric.service;

import java.util.Set;

import ro.cric.model.User;

public interface UserService {
	
	public User getUserById(long id);
	public boolean doesEmailExist(String email);
	public boolean doesUsernameExist(String username);
	public User getUserByCredentials(String password, String username);
	public void register(User user);
	public User getUserByUsername(String username);
	public Set<User> getAllFriends(User user);
	public void addFriend(User requestee, User toBeFriended);
	public void notifyUsersInTheArea(double latitude, double longitude, double radius);
	
}