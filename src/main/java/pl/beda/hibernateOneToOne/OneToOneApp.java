package pl.beda.hibernateOneToOne;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import pl.beda.hibernateOneToOne.entity.Company;
import pl.beda.hibernateOneToOne.entity.CompanyDetail;

/**
 * Created by Marcin Beda.
 */

public class OneToOneApp {

	public static void main(String[] args) {

		Configuration conf = new Configuration();
		conf.configure("hibernate.cfg.xml");
		conf.addAnnotatedClass(Company.class);
		conf.addAnnotatedClass(CompanyDetail.class);
		SessionFactory factory = conf.buildSessionFactory();
		Session session = factory.getCurrentSession();

		Company company = new Company("Strefakursow", 10000000);
		CompanyDetail detail = new CompanyDetail("Poland", 150);
		company.setCompanyDetail(detail);
		
		session.beginTransaction();
		
		session.save(detail);
		session.save(company);

		session.getTransaction().commit();

		factory.close();
	}
}
