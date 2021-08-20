package pl.beda.hibernateManyToMany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import pl.beda.hibernateManyToMany.entity.*;

/**
 * Created by Marcin Beda.
 */

public class ManyToManySaveApp {

	public static void main(String[] args) {

		Configuration conf = new Configuration();
		conf.configure("hibernate.cfg.xml");
		conf.addAnnotatedClass(Company.class);
		conf.addAnnotatedClass(CompanyDetail.class);
		conf.addAnnotatedClass(Property.class);
		conf.addAnnotatedClass(Department.class);
		conf.addAnnotatedClass(Employee.class);
		conf.addAnnotatedClass(Training.class);
		SessionFactory factory = conf.buildSessionFactory();

		Session session = factory.getCurrentSession();

		session.beginTransaction();
		
		Training training = new Training("sales training");
		
		Employee employee = new Employee("Johny","Depp",16000);
		Employee employee2 = new Employee("Miley","Cyrus",16000);
		
		training.addEmployee(employee);
		training.addEmployee(employee2);
		
		session.persist(training);
		
		session.getTransaction().commit();

		factory.close();
	}
}
