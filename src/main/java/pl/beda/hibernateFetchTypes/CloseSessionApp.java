package pl.beda.hibernateFetchTypes;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.beda.hibernateFetchTypes.entity.Company;
import pl.beda.hibernateFetchTypes.entity.CompanyDetail;
import pl.beda.hibernateFetchTypes.entity.Property;

/**
 * Created by Marcin Beda.
 */

public class CloseSessionApp {

    public static void main(String[] args) {

        Configuration conf = new Configuration();
        conf.configure("hibernate.cfg.xml");
        conf.addAnnotatedClass(Company.class);
        conf.addAnnotatedClass(CompanyDetail.class);
        conf.addAnnotatedClass(Property.class);
        SessionFactory factory = conf.buildSessionFactory();

        Session session = factory.getCurrentSession();

        int id = 87;

        session.beginTransaction();

        System.out.println("Pobieranie obiektu company");
        Company company = session.get(Company.class, id);
        System.out.println("Pobrano obiekt company");
        System.out.println(company);
        System.out.println("...");

        session.getTransaction().commit();

        session = factory.getCurrentSession();

        session.beginTransaction();

        Company mergedCompany = (Company) session.merge(company);

        System.out.println("Nieruchomosci:");
        for (Property property : mergedCompany.getProperties()) {
            System.out.println(property);
        }

        session.getTransaction().commit();

        factory.close();
    }
}
