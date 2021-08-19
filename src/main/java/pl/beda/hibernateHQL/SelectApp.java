package pl.beda.hibernateHQL;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import pl.beda.hibernateHQL.entity.Employee;

/**
 * Created by Marcin Beda.
 */

public class SelectApp {

    public static void main(String[] args) {

		Configuration conf = new Configuration();
		conf.configure("hibernate.cfg.xml");
        conf.addAnnotatedClass(Employee.class);
        SessionFactory factory = conf.buildSessionFactory();

        Session session = factory.getCurrentSession();

        session.beginTransaction();

        String select = "select firstName, lastName from Employee";
        String select2 = "select e.firstName, e.lastName from Employee as e";
        String select3 = "select e.firstName, e.lastName from Employee e";

        Query query = session.createQuery(select3);
        List<Object[]> result = query.getResultList();

        session.getTransaction().commit();

        for (Object[] values : result) {
            System.out.println("firstName: " + values[0] + ", lastName: " + values[1]);
        }

        factory.close();
    }
}
