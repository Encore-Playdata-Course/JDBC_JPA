package run.test;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;

import model.domain.Employee;
import util.PublicCommon;

public class RunningTest {
	//NamedQuery Test
	@Test
	public void runNamedQueryTest() {
		EntityManager em = PublicCommon.getEntityManager();
		try {
			// "Employee.findByEmpno" - 사용하기 위해서는 Employee에 선언시 매핑했던 이름과 사번값
			Employee e = (Employee)em.createNamedQuery("Employee.findByEmpno").setParameter("eid", 7369).getSingleResult();
			System.out.println(e);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
		}
	}
	
	
	
	//@Test
	public void runJPQLTest() {
		EntityManager em = PublicCommon.getEntityManager();
		try {
			//이름 소문자로 변경해서 검색
			String jpql = "select LOWER(e.ename) from Employee e";
			List<String> list = em.createQuery(jpql).getResultList();
			list.forEach(v -> System.out.println(v));
			
			System.out.println("--- max() ----");
			//최고 급여 검색
			jpql = "select max(e.salary) from Employee e";
			int maxSal = (int)em.createQuery(jpql).getSingleResult();
			System.out.println(maxSal);
		
			/*select e from Employee e
			 * select e from Employee e where e.salary between 800 and 3000
			 * Employee 객체들 생성 의미
			 * 
			 * sql문장 실행 후에는 Employee 객체들이 다수 저장된 list객체
			 */
			System.out.println("--- between ~ and ---");
			//between ~ and
			jpql = "select e from Employee e where e.salary between 800 and 3000";
			List<Employee> list2 = em.createQuery(jpql).getResultList();
			list2.forEach(v -> System.out.println(v));
			
			System.out.println("---- like ---");
			//? like 'M%' - M으로 시작하는 모든 사원의 모든 정보 검색 및 출력 
			jpql = "select e from Employee e where e.ename like 'M%'";
			list2 = em.createQuery(jpql).getResultList();
			list2.forEach(v -> System.out.println(v));
			
			System.out.println("--- order by ---");
			//? 사원명을 오름차순으로 정렬해서 사원명만 검색 및 출력하기
			jpql = "select e.ename from Employee e order by e.ename asc";
			list = em.createQuery(jpql).getResultList();
			list.forEach(v -> System.out.println(v));
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
		}
		
	}
}
