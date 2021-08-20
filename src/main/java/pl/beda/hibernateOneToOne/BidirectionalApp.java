package pl.beda.hibernateOneToOne;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.beda.hibernateOneToOne.entity.Company;
import pl.beda.hibernateOneToOne.entity.CompanyDetail;

/**
 * Created by Marcin Beda.
 */

public class BidirectionalApp {

	public static void main(String[] args) {

		Configuration conf = new Configuration();
		conf.configure("hibernate.cfg.xml");
		conf.addAnnotatedClass(Company.class);
		conf.addAnnotatedClass(CompanyDetail.class);
		SessionFactory factory = conf.buildSessionFactory();

		Session session = factory.getCurrentSession();

//		Company company = new Company("PZU", 1000000);
//		CompanyDetail detail = new CompanyDetail("Poland",17000);
//		detail.setCompany(company);
//		company.setCompanyDetail(detail);
		
		session.beginTransaction();
		
//		session.persist(detail);
		CompanyDetail detail = session.get(CompanyDetail.class, 23);
		session.remove(detail);

		session.getTransaction().commit();

//		System.out.println(detail.getCompany());

		factory.close();
	}
}
