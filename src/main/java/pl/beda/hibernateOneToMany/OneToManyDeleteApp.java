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

public class OneToManyDeleteApp {

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
//		Company company = (Company) query.getSingleResult();
//
//		for(Property property : company.getProperties()) {
//			if("Gdynia".equals(property.getCity())) {
//				session.delete(property);
//			}
//		}

        int idToDelete = 6;

        Property property = session.get(Property.class, idToDelete);

        session.delete(property);

        session.getTransaction().commit();

        factory.close();
    }
}
