package pl.beda.hibernateOneToMany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import pl.beda.hibernateOneToMany.entity.Company;
import pl.beda.hibernateOneToMany.entity.CompanyDetail;
import pl.beda.hibernateOneToMany.entity.Property;

/**
 * Created by Marcin Beda.
 */

public class OneToManyGetApp {

    public static void main(String[] args) {

		Configuration conf = new Configuration();
		conf.configure("hibernate.cfg.xml");
        conf.addAnnotatedClass(Company.class);
        conf.addAnnotatedClass(CompanyDetail.class);
        conf.addAnnotatedClass(Property.class);
        SessionFactory factory = conf.buildSessionFactory();

        Session session = factory.getCurrentSession();

        String getCompany = "select c from Company c where c.name='Strefakursow'";

        session.beginTransaction();

        Query query = session.createQuery(getCompany);

        Company company = (Company) query.getSingleResult();

        System.out.println(company);

        for (Property property : company.getProperties()) {
            System.out.println(property);
        }

        session.getTransaction().commit();

        factory.close();
    }
}
