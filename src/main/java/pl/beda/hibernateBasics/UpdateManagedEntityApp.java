package pl.beda.hibernateBasics;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.beda.hibernateBasics.entity.Employee;

/**
 * Created by Marcin Beda.
 */

public class UpdateManagedEntityApp {

    public static void main(String[] args) {

        Configuration conf = new Configuration();
        conf.configure("hibernate.cfg.xml");
        conf.addAnnotatedClass(Employee.class);
        SessionFactory factory = conf.buildSessionFactory();

        Session session = factory.getCurrentSession();

        session.beginTransaction();

        Employee employee = session.get(Employee.class, 30);
        employee.setFirstName("Tadeusz");

        session.getTransaction().commit();

        System.out.println("Zaktualizowane dane pracownika: " + employee);

        factory.close();
    }
}
