package pl.beda.hibernateHQL;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import pl.beda.hibernateHQL.entity.Employee;


public class OrderByApp {

    public static void main(String[] args) {

        Configuration conf = new Configuration();
        conf.configure("hibernate.cfg.xml");
        conf.addAnnotatedClass(Employee.class);
        SessionFactory factory = conf.buildSessionFactory();

        Session session = factory.getCurrentSession();

        session.beginTransaction();

        String orderBy = "select e.firstName, e.lastName from Employee e order by e.firstName";
        String orderBy2 = "select e.firstName, e.lastName from Employee e order by e.lastName desc";
        String orderBy3 = "select e.firstName, e.lastName, e.salary from Employee e order by e.salary asc";

        Query query = session.createQuery(orderBy3);
        List<Object[]> result = query.getResultList();

        session.getTransaction().commit();

        for (Object[] values : result) {
            System.out.println("firstName: " + values[0] + ", lastName: " + values[1] + ", salary: " + values[2]);
        }

        factory.close();
    }
}
