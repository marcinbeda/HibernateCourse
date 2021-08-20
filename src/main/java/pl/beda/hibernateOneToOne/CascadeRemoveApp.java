package pl.beda.hibernateOneToOne;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.beda.hibernateOneToOne.entity.Company;
import pl.beda.hibernateOneToOne.entity.CompanyDetail;

/**
 * Created by Marcin Beda.
 */

public class CascadeRemoveApp {

    public static void main(String[] args) {

        Configuration conf = new Configuration();
        conf.configure("hibernate.cfg.xml");
        conf.addAnnotatedClass(Company.class);
        conf.addAnnotatedClass(CompanyDetail.class);
        SessionFactory factory = conf.buildSessionFactory();

        Session session = factory.getCurrentSession();

        session.beginTransaction();

        Company company = session.get(Company.class, 33);

        session.remove(company);

        session.getTransaction().commit();

        factory.close();
    }
}