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

public class FromApp {

    public static void main(String[] args) {

        Configuration conf = new Configuration();
        conf.configure("hibernate.cfg.xml");
        conf.addAnnotatedClass(Employee.class);
        SessionFactory factory = conf.buildSessionFactory();

        Session session = factory.getCurrentSession();

        session.beginTransaction();

        String from = "FROM Employee";
        String from2 = "from Employee";
        String from3 = "from pl.strefakursow.hqldemo1.entity.Employee";

        Query query = session.createQuery(from3);

        List<Employee> list = query.getResultList();

        session.getTransaction().commit();

        for (Employee employee : list) {
            System.out.println(employee);
        }

        factory.close();
    }
}
