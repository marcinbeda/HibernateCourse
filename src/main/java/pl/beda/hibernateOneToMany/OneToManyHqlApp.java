package pl.beda.hibernateOneToMany;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import pl.beda.hibernateOneToMany.entity.*;


public class OneToManyHqlApp {

    public static void main(String[] args) {

        Configuration conf = new Configuration();
        conf.configure("hibernate.cfg.xml");
        conf.addAnnotatedClass(Company.class);
        conf.addAnnotatedClass(CompanyDetail.class);
        conf.addAnnotatedClass(Property.class);
        SessionFactory factory = conf.buildSessionFactory();

        Session session = factory.getCurrentSession();

        String getCompany = "select c.name from Property p join p.company c where p.city='Sevilla'";
        String getCompany2 = "select c.name from Property p join p.company c join c.companyDetail cd where p.city='Barcelona' and cd.residence='Switzerland'";
        String getCompany3 = "select c.name from pl.beda.hibernateOneToMany.entity.Company AS c where size(c.properties) > 4";

        session.beginTransaction();
        Query query = session.createQuery(getCompany3);

        List<String> resultList = query.getResultList();
        session.getTransaction().commit();

        for (String result : resultList) {
            System.out.println(result);
        }

        factory.close();
    }
}
