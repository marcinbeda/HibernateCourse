package pl.beda.hibernateManyToMany;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.beda.hibernateManyToMany.entity.*;


public class ManyToManyGetApp {

    public static void main(String[] args) {

        Configuration conf = new Configuration();
        conf.configure("hibernate.cfg.xml");
        conf.addAnnotatedClass(Company.class);
        conf.addAnnotatedClass(CompanyDetail.class);
        conf.addAnnotatedClass(Property.class);
        conf.addAnnotatedClass(Department.class);
        conf.addAnnotatedClass(Employee.class);
        conf.addAnnotatedClass(Training.class);
        SessionFactory factory = conf.buildSessionFactory();

        Session session = factory.getCurrentSession();

        int id = 4;

        session.beginTransaction();

//				Training training = new Training("java training");
//				
//				Employee employee1 = session.get(Employee.class, 303);
//				Employee employee2 = session.get(Employee.class, 304);
//				
//				training.addEmployee(employee1);
//				training.addEmployee(employee2);
//				
//				session.persist(training);

        String getTraining = "from Training";

        Query query = session.createQuery(getTraining);

        List<Training> resultList = query.getResultList();

        for (Training training : resultList) {
            System.out.println("\n" + training);
            for (Employee employee : training.getEmployees()) {
                System.out.println("- " + employee);
            }
        }

//				Training training = session.get(Training.class, id);
//
//				System.out.println(training);
//				
//				for(Employee employee : training.getEmployees()) {
//					System.out.println("- " + employee);
//				}

        session.getTransaction().commit();

        factory.close();
    }
}
