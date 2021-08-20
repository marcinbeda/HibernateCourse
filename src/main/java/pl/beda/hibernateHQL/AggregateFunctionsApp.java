package pl.beda.hibernateHQL;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import pl.beda.hibernateHQL.entity.Employee;


public class AggregateFunctionsApp {

    public static void main(String[] args) {

        Configuration conf = new Configuration();
        conf.configure("hibernate.cfg.xml");
        conf.addAnnotatedClass(Employee.class);
        SessionFactory factory = conf.buildSessionFactory();

        Session session = factory.getCurrentSession();

        session.beginTransaction();

        String avg = "select avg(e.salary) from Employee e";
        String sum = "select sum(e.salary) from Employee e";
        String min = "select min(e.salary) from Employee e";
        String max = "select max(e.salary) from Employee e";
        String count = "select count(e.salary) from Employee e";

        Query query = session.createQuery(count);

        Long result = (Long) query.getSingleResult();

        session.getTransaction().commit();

        System.out.println("wynik: " + result);

        factory.close();
    }
}
