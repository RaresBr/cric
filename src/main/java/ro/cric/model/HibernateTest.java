package ro.cric.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateTest {
	public static void main(String [] args){
		// Create a configuration instance
		Configuration configuration = new Configuration();
		// Provide configuration file
		configuration.configure("hibernate.cfg.xml");
		// Build a SessionFactory
		SessionFactory factory = configuration.buildSessionFactory();
		// Get current session, current session is already associated with Thread
		Session session = factory.getCurrentSession();
		// Begin transaction, if you would like save your instances, your calling of save must be associated with a transaction
		session.getTransaction().begin();
		// Create User
		User usr = new User();
		usr.setFirstName("Gogu");
		usr.setLastName("Martalogu");
		usr.setLoginId("qwerty");
		usr.setLoginPasswd("qwerty123");
		usr.setEmail("qwerty@dummy.com");
//		emp.setEmployeeName("Peter Jousha");
//		emp.setEmployeeSalary(2000);
//		emp.setEmployeeHireDate(new Date());
		// Save
		session.save(usr);
		// Commit, calling of commit will cause save an instance of employee
		session.getTransaction().commit();
	}
}

