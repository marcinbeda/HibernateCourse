package pl.beda.hibernateOneToOne;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.beda.hibernateOneToOne.entity.Company;
import pl.beda.hibernateOneToOne.entity.CompanyDetail;


public class CascadeApp {

    public static void main(String[] args) {

        Configuration conf = new Configuration();
        conf.configure("hibernate.cfg.xml");
        conf.addAnnotatedClass(Company.class);
        conf.addAnnotatedClass(CompanyDetail.class);
        SessionFactory factory = conf.buildSessionFactory();

        Session session = factory.getCurrentSession();

        Company company = new Company("Orlen", 200000000);
        CompanyDetail detail = new CompanyDetail("Poland", 16000);
        company.setCompanyDetail(detail);

        session.beginTransaction();

        session.persist(company);

        session.getTransaction().commit();

        factory.close();
    }
}
