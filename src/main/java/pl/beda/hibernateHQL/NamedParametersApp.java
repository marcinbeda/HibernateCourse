package pl.beda.hibernateHQL;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import pl.beda.hibernateHQL.entity.Employee;


public class NamedParametersApp {

    public static void main(String[] args) {

        Configuration conf = new Configuration();
        conf.configure("hibernate.cfg.xml");
        conf.addAnnotatedClass(Employee.class);
        SessionFactory factory = conf.buildSessionFactory();

        Session session = factory.getCurrentSession();

        String employeeFirstName = "Steven";
        String employeeLastName = "King";

        session.beginTransaction();

        String queryString = "select e.firstName, e.lastName, e.salary from Employee e where e.firstName='"
                + employeeFirstName + "' and e.lastName='" + employeeLastName + "'";

        Query query = session.createQuery(queryString);

        String namedParametersString = "select e.firstName, e.lastName, e.salary from Employee e where e.firstName=:firstName and e.lastName=:lastName";

        Query namedParametersQuery = session.createQuery(namedParametersString);
        namedParametersQuery.setParameter("firstName", employeeFirstName);
        namedParametersQuery.setParameter("lastName", employeeLastName);

        List<Object[]> result = namedParametersQuery.getResultList();

        session.getTransaction().commit();

        for (Object[] values : result) {
            System.out.println("firstName: " + values[0] + ", lastName: " + values[1] + ", salary: " + values[2]);
        }

        factory.close();
    }
}
