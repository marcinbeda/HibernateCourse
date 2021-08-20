package pl.beda.hibernateOneToManyOneWay;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.beda.hibernateOneToManyOneWay.entity.Company;
import pl.beda.hibernateOneToManyOneWay.entity.CompanyDetail;
import pl.beda.hibernateOneToManyOneWay.entity.Department;
import pl.beda.hibernateOneToManyOneWay.entity.Property;

/**
 * Created by Marcin Beda.
 */

public class OneToManyUniGetApp {

    public static void main(String[] args) {

		Configuration conf = new Configuration();
		conf.configure("hibernate.cfg.xml");
        conf.addAnnotatedClass(Company.class);
        conf.addAnnotatedClass(CompanyDetail.class);
        conf.addAnnotatedClass(Property.class);
        conf.addAnnotatedClass(Department.class);
        SessionFactory factory = conf.buildSessionFactory();

        Session session = factory.getCurrentSession();

        int id = 92;

        session.beginTransaction();

//		Company company = session.get(Company.class, id);
//		System.out.println(company);
//		
//		Set<Department> departments = company.getDepartments();
//		
//		for(Department department : departments) {
//			System.out.println(department);
//		}

        Department department = session.get(Department.class, 8);

        System.out.println(department);

        session.getTransaction().commit();

        factory.close();
    }
}
