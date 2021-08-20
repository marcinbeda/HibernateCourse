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

public class OneToManySaveApp {

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
		
		Property property1 = new Property("Warszawa", 40);
		Property property2 = new Property("Gdynia", 30);
		
		company.addProperty(property1);
		company.addProperty(property2);
		
		session.persist(property1);
		session.persist(property2);
		
		session.getTransaction().commit();

		factory.close();
	}
}
