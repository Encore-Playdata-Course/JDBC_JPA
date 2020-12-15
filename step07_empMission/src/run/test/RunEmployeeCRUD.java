//employee table crud 로직 정상 실행되게 완벽하게 구현
//개별 메소드로 개발 차후에 리펙토링 해야 함

package run.test;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.domain.Department;
import model.domain.Employee;
import util.PublicCommon;

public class RunEmployeeCRUD {
	public static void createEmployee() {

		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			Department dept1 = Department.builder().dname("Technical Manager").loc("Seoul").employees(new ArrayList<Employee>()).build();
			Employee emp1 = Employee.builder().eId(1201).ename("Gopal").salary(40000).dname(dept1).build();
			
			dept1.getEmployees().add(emp1);
			
			em.persist(dept1);
			em.persist(emp1);
			
			tx.commit();		
		} catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}finally {
			em.close();
			PublicCommon.close();
		}
		
	}

	public static void updateEmployee() {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			
			Employee emp1 = em.find(Employee.class, 1201);
			em.persist(emp1);
			// before update
			System.out.println("update 전 : " + emp1.getSalary());
			emp1.setSalary(46000);
			em.persist(emp1);
			// after update
			System.out.println("update 후 : " + emp1.getSalary());

			
			tx.commit();		
		} catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}finally {
			em.close();
			PublicCommon.close();
		}
		
	}

	// select
	public static void findElement() {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			
			Employee employee = em.find(Employee.class, 1201);
			Department dept = em.find(Department.class, "Technical Manager");

			System.out.println("employee ID = " + employee.getEId());
			System.out.println("employee NAME = " + employee.getEname());
			System.out.println("employee SALARY = " + employee.getSalary());
			System.out.println("employee DEPARTMENTNAME = " + dept.getDname());
			

			
			tx.commit();		
		} catch(Exception e){
			tx.rollback();
			System.out.println("검색 요청한 직원은 미존재합니다");
			e.printStackTrace();
		}finally {
			em.close();
			PublicCommon.close();
		}
	}

//	// delete
	public static void deleteElement() {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			Employee employee = em.find(Employee.class, 1201);
			em.remove(employee);
			
			tx.commit();		
		} catch(Exception e){
			tx.rollback();
			System.out.println("검색 요청한 직원은 미존재합니다");
			e.printStackTrace();
		}finally {
			em.close();
			PublicCommon.close();
		}
		
	}

	public static void main(String[] args) {
//		createEmployee();  //실행 직후 none로 대체하기
//		updateEmployee();
//		findElement();
//		deleteElement();
	}
	
}
