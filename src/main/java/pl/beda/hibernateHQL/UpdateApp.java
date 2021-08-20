package pl.beda.hibernateHQL;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import pl.beda.hibernateHQL.entity.Employee;


public class UpdateApp {

    public static void main(String[] args) {

        Configuration conf = new Configuration();
        conf.configure("hibernate.cfg.xml");
        conf.addAnnotatedClass(Employee.class);
        SessionFactory factory = conf.buildSessionFactory();

        Session session = factory.getCurrentSession();

        int idEmployee = 297;
        int salary = 16000;

        session.beginTransaction();

        String update = "update Employee e set e.salary=:salary where e.idEmployee=:idEmployee";
        Query query = session.createQuery(update);
        query.setParameter("salary", salary);
        query.setParameter("idEmployee", idEmployee);
        query.executeUpdate();

        session.getTransaction().commit();

        factory.close();
    }
}
