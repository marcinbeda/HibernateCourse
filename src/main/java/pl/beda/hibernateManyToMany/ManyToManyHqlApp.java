package pl.beda.hibernateManyToMany;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import pl.beda.hibernateManyToMany.entity.*;


public class ManyToManyHqlApp {

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

        int minEmployeeNumber = 6;
        String getTraining = "select t from Training t where size(t.employees) >= :minEmployeeNumber";

        String course = "javascript";
        String getEmployee = "select e from Employee e join e.trainings t where t.name=:course";

        int trainingNumber = 1;
        int maxSalary = 12000;

        String getEmployee2 = "select e from pl.beda.hibernateManyToMany.entity.Employee e where size(e.trainings) = :trainingNumber and e.salary < :maxSalary";

        session.beginTransaction();

        Query query = session.createQuery(getEmployee2);
//				query.setParameter("course", course);
//				query.setParameter("minEmployeeNumber", minEmployeeNumber);
        query.setParameter("trainingNumber", trainingNumber);
        query.setParameter("maxSalary", maxSalary);

        List<Employee> resultList = query.getResultList();

//				for(Training training : resultList) {
//					System.out.println(training);
//				}

        for (Employee employee : resultList) {
            System.out.println(employee);
        }

        session.getTransaction().commit();

        factory.close();
    }
}
