package ro.cric.dao;

import ro.cric.model.User;

public interface UserDao extends GenericDao<User> {

	public boolean doesEmailExist(String email);

	public boolean doesUsernameExist(String username);

	public User getByCredentials(String password, String username);
}
