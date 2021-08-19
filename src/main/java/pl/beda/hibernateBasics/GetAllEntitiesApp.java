package pl.beda.hibernateBasics;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.beda.hibernateBasics.entity.Employee;

/**
 * Created by Marcin Beda.
 */

public class GetAllEntitiesApp {

	public static void main(String[] args) {

		Configuration conf = new Configuration();
		conf.configure("hibernate.cfg.xml");
		conf.addAnnotatedClass(Employee.class);
		SessionFactory factory = conf.buildSessionFactory();

		Session session = factory.getCurrentSession();

		session.beginTransaction();

		List<Employee> resultList = session.createQuery("from Employee").getResultList();
		
		for(Employee employee : resultList) {
			System.out.println(employee);
		}

		session.getTransaction().commit();

		factory.close();
	}
}
