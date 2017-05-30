package ro.cric.dao.impl;

import org.springframework.stereotype.Repository;

import ro.cric.dao.UserDao;
import ro.cric.model.User;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

}
