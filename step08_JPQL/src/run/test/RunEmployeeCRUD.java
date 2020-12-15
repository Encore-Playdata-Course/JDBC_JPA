package run.test;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import model.domain.Dept;
import model.domain.Employee;
import util.PublicCommon;
@Slf4j
public class RunEmployeeCRUD {

	public static void createEmployee(EntityManager em, int empno, String ename, int sal, int deptno) {
		Dept dept = em.find(Dept.class, deptno);
		Employee employee = Employee.builder().ename(ename).eid(empno)
				.salary(sal).department(dept).build();
		em.persist(employee);
		log.info("employee created");
	}

	public static void updateEmployee(EntityManager em, int empno, int sal) {
		int result = em.createNamedQuery("Employee.update")
				.setParameter("eid", empno).setParameter("sal", sal).executeUpdate();
		if(result != 0) {
			log.info("update complete");
		} else {
			log.info("not updated");
		}
	}

	// select
	//결과값이 없을 경우 에러가 발생하므로 1개의 데이터를 찾더라도 List로 받아야 함
	public static void findElement(EntityManager em, int empno) {
		List<Employee> emp = em.createNamedQuery("Employee.findByEmpno").setParameter("eid", empno).getResultList();
		emp.forEach(v->System.out.println(v));
		if(emp.size()==0) {
			log.info("not existing data");
		}
	}
	
	public static void findAllElement(EntityManager em) {
		List<Employee> empList = em.createNamedQuery("Employee.findAll").getResultList();
		empList.forEach(v->System.out.println(v));
	}

	// delete
	public static void deleteElement(EntityManager em, int empno) {
		int result = em.createNamedQuery("Employee.delete").setParameter("eid", empno).executeUpdate();
		if(result != 0) {
			log.info("delete complete");
		} else {
			log.info("not deleted");
		}
	}
	
  @Test
  public void runningTest() {
    EntityManager em = PublicCommon.getEntityManager();
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    try{
    	System.out.println("---------------select all------------");
    	findAllElement(em);
    	System.out.println("---------------create---------------");
    	createEmployee(em, 1234, "Bill Gates", 6000, 20);
    	System.out.println("---------------select one---------------");
    	findElement(em, 1234);
    	System.out.println("---------------update sal---------------");
    	updateEmployee(em, 1234, 5000);
    	em.flush();
    	em.clear();
    	findElement(em, 1234);
    	System.out.println("---------------delete---------------");
    	deleteElement(em, 1234);
    	findElement(em, 1234);
    	System.out.println("---------------end---------------");
    	tx.commit();
    } catch(Exception e){
    	tx.rollback();
    	e.printStackTrace();
    } finally{
      em.close();
      PublicCommon.close();
    }
  }
}
