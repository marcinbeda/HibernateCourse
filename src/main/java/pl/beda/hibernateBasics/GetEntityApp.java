package pl.beda.hibernateBasics;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.beda.hibernateBasics.entity.Employee;

/**
 * Created by Marcin Beda.
 */

public class GetEntityApp {

	public static void main(String[] args) {

		Configuration conf = new Configuration();
		conf.configure("hibernate.cfg.xml");
		conf.addAnnotatedClass(Employee.class);
		SessionFactory factory = conf.buildSessionFactory();

		Session session = factory.getCurrentSession();

		Employee employee = new Employee();
		employee.setFirstName("Tadeusz");
		employee.setLastName("Wisniewski");
		employee.setSalary(10000);

		session.beginTransaction();

		Integer id = (Integer) session.save(employee);

		session.getTransaction().commit();

		session = factory.getCurrentSession();

		session.beginTransaction();

		Employee retrievedEmployee = session.get(Employee.class, id);

		session.getTransaction().commit();
		
		System.out.println("Dane pracownika: " + retrievedEmployee);

		factory.close();
	}
}
