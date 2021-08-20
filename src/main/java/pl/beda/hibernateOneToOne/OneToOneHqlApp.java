package pl.beda.hibernateOneToOne;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import pl.beda.hibernateOneToOne.entity.Company;
import pl.beda.hibernateOneToOne.entity.CompanyDetail;

/**
 * Created by Marcin Beda.
 */

public class OneToOneHqlApp {

	public static void main(String[] args) {

		Configuration conf = new Configuration();
		conf.configure("hibernate.cfg.xml");
		conf.addAnnotatedClass(Company.class);
		conf.addAnnotatedClass(CompanyDetail.class);
		SessionFactory factory = conf.buildSessionFactory();

		Session session = factory.getCurrentSession();

		String select = "select c from Company c";
		String where = "select c from Company c join c.companyDetail cd where cd.residence='Italy'";
		String sum = "select sum(c.value) from Company c join c.companyDetail cd where cd.residence='Poland'";
		String orderBy = "select c.name from CompanyDetail cd join cd.company c where cd.employeeNumber < 35000 order by c.value";
		
		session.beginTransaction();
		
		Query query = session.createQuery(orderBy);
		
//		List<Company> resultList = query.getResultList();
//		Long result = (Long)query.getSingleResult();
		List<String> resultList = query.getResultList();

		session.getTransaction().commit();
		
//		for(Company c : resultList) {
//			System.out.println(c + ", " + c.getCompanyDetail());
//		}
		
//		System.out.println("wartosc wszystkich polskich firm w bazie danych: " + result);
		
		for(String c : resultList) {
			System.out.println(c);
		}

		factory.close();
	}
}
