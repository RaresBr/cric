package ro.cric;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ro.cric.model.User;

@Component
public class UserService {
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public void register(User usr){
		// Acquire session
		Session session = sessionFactory.getCurrentSession();
		// Save user, saving behavior gets done in a transactional manner
		session.save(usr);
	}
}