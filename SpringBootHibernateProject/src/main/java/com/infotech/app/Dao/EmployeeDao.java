package com.infotech.app.Dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.infotech.app.Entities.Employee;

@Repository
public class EmployeeDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	public Integer newEmployee(Employee employee){
		Session session= null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Integer id =(Integer) session.save(employee);
			System.out.println("Employee is created With Id::"+id);
			session.getTransaction().commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
	

	@SuppressWarnings("unchecked")
	public List<Employee> findAllEmployee() {
		
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<Employee> employee = session.createQuery("from Employee").list();
			System.out.println("Employee findAllEmployee::");
			session.getTransaction().commit();
			return employee;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}


	public Employee findEmployeeById(int id) {
				
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Employee employee = session.get(Employee.class, id);
			
			System.out.println("Employee findbylEmployee::"+employee.toString());
			session.getTransaction().commit();
			return employee;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}


	
	
	//here primary id need to be pass in jason object to execute the operation
	
	/*
	 * public void updateEmployee(int id, Employee employee) {
	 * 
	 * Session session = null;
	 * 
	 * try {
	 * 
	 * session = sessionFactory.openSession();
	 * 
	 * Transaction tx = session.beginTransaction();
	 * 
	 * session.update(employee);
	 * 
	 * tx.commit();
	 * 
	 * }catch(Exception e) { e.printStackTrace(); }
	 * 
	 * }
	 */

	
	// no need to pass od in jason object
	
	public void updateEmployee(Serializable id, Employee employee) {	//Serializable remove the associated instances
		
		Session session = null;
		
		try {
			
			Employee employee1 =null;
			
			session = sessionFactory.openSession();
			
			Transaction tx = session.beginTransaction();
			
			employee1 = session.get(Employee.class, id);
			
			employee1.setName(employee.getName());
			employee1.setSalary(employee.getSalary());
			
			session.update(employee1);
			
			tx.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	

	public void delete(String name) {
		
		
		
		Session session = null;
		try {

			session = sessionFactory.openSession();
			session.beginTransaction();
			
			Query query = session.createQuery("delete from Employee e where e.name =:name");
			query.setParameter("a",name);
			 
			int result = query.executeUpdate();
			
			//session.delete(name); used only with id
			
			System.out.println("Delete Employee where name::"+ result); 
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		
	}


	public void updateSalaryDao(int id, Double salary) {
		
		Session session = null;
		
		try {
			
			session = sessionFactory.openSession();
			
			Transaction tx =session.beginTransaction();
			
			String s = "Update Employee SET salary =:s where id =:id";
			
			Query q = session.createQuery(s).setParameter("s", salary).setParameter("id", id);
			
			q.executeUpdate();
			
			tx.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
