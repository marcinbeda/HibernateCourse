package pl.beda.hibernateOneToManyOneWay;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import pl.beda.hibernateOneToManyOneWay.entity.Company;
import pl.beda.hibernateOneToManyOneWay.entity.CompanyDetail;
import pl.beda.hibernateOneToManyOneWay.entity.Department;
import pl.beda.hibernateOneToManyOneWay.entity.Property;

/**
 * Created by Marcin Beda.
 */

public class OneToManyUniDeleteApp {

    public static void main(String[] args) {

		Configuration conf = new Configuration();
		conf.configure("hibernate.cfg.xml");
        conf.addAnnotatedClass(Company.class);
        conf.addAnnotatedClass(CompanyDetail.class);
        conf.addAnnotatedClass(Property.class);
        conf.addAnnotatedClass(Department.class);
        SessionFactory factory = conf.buildSessionFactory();

        Session session = factory.getCurrentSession();

        int id = 7;
        int idCompany = 92;
        String departmentNameToDelete = "HR";
        int idHql = 9;

        String delete = "delete Department d where d.idDepartment=:idDepartment";

        session.beginTransaction();

//				Department department = session.get(Department.class, id);
//				session.delete(department);

//				Company company = session.get(Company.class, idCompany);
//				
//				for(Department department : company.getDepartments()) {
//					if(department.getName().equals(departmentNameToDelete)) {
//						company.getDepartments().remove(department);
//						session.delete(department);
//					}
//				}

        Query query = session.createQuery(delete);
        query.setParameter("idDepartment", idHql);
        int deletedRows = query.executeUpdate();

        System.out.println("ilosc usunietych rekordow: " + deletedRows);

        session.getTransaction().commit();

        factory.close();
    }
}
