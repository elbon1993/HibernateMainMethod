package com.elbon.HibernateMain;

import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class InsertEmployee {

	public static void main(String[] args) {
		System.out.println("New employee: "+createEmployee("Tarun", "profiles"));
		System.out.println(getEmployeesNativeQuery());
		System.out.println("deleting employee tarun: ");
		deleteEmployeeByName("Tarun");
		System.out.println(getEmployeesNativeQuery());
		System.out.println("updating employee Gowtham: ");
		updateEmployeeLastName("Gowtham","Lingutla");
		System.out.println(getEmployeesNativeQuery());
	}
	
	private static int createEmployee(String fname, String lname) {
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("com/elbon/Sample/hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		Employee e1 = new Employee();
		e1.setFirstName(fname);
		e1.setLastName(lname);

		int id = (Integer)session.save(e1);
		t.commit();
		factory.close();
		session.close();
		return e1.getId();
	}
	
	private static List<Employee> getEmployeesNativeQuery() {
		
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("com/elbon/Sample/hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();

		TypedQuery<Employee> query = session.createNativeQuery("select id, fname, lname from employee", Employee.class);
		List<Employee> employees = query. getResultList();
		
		System.out.println("fetching all employees:");
		factory.close();
		session.close();
		return employees;
	}
	
	private static void deleteEmployeeByName(String fname) {
		
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("com/elbon/Sample/hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		TypedQuery query = session.createNamedQuery("DELETE_EMPLOYEE_BY_NAME");
		query.setParameter("name", fname);
		query.executeUpdate();
		t.commit();
		
		factory.close();
		session.close();
	}
	
	private static void updateEmployeeLastName(String fname, String lname) {
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("com/elbon/Sample/hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		TypedQuery query = session.createNamedQuery("UPDATE_EMPLOYEE_BY_FNAME");
		query.setParameter("fname", fname);
		query.setParameter("lname", lname);
		query.executeUpdate();
		t.commit();
		
		factory.close();
		session.close();
	}
	
}
