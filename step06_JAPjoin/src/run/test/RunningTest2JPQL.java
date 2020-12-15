package run.test;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.jupiter.api.Test;

import step03.entity.Member;
import step03.entity.Team;
import util.PublicCommon;

public class RunningTest2JPQL {
	
	//simple select
	//20살 미만의 멤버의 모든 정보 검색
	//select m from member m where m.age <20
	private static void simpleQuery(EntityManager em) {
		List<Member> resultList 
		= em.createQuery("select m from Member m where m.age < 20", Member.class).getResultList();
		
//		for(Member m : resultList) {
//			System.out.println(m);
//		}
		resultList.forEach(m -> System.out.println(m));
		

	}
	
	//join 
	//첫번째 실습 : Member의 teamId 타입인 Team의  teamName 변수 값을 검색하는 join문장
	/* m.teamId t
	 *  Member 객체의 teamId변수(Team 타입)
	 *  Team t = m.teamId;
	 *  
	 *  ? teamId값을 where 조건식으로 반영해보기
	 */
	private static void joinQuery(EntityManager em) {
		List<Member> resultList
		= em.createQuery("select m from Member m join m.teamId t where t.teamId=1").getResultList();
		
		resultList.forEach(v -> System.out.println(v));
	}
	
	@Test
	public void runningJPQL() {
		EntityManager em = PublicCommon.geEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
			
		try {
			Team team = Team.builder().teamName("team A").members(new ArrayList<Member>()).build();
			
			Member m1 = Member.builder().age(22).name("유재석").teamId(team).build();
			Member m2 = Member.builder().age(42).name("강호동").teamId(team).build();
			Member m3 = Member.builder().age(10).name("준형").teamId(team).build();
			Member m4 = Member.builder().age(15).name("정민").teamId(team).build();
			Member m5 = Member.builder().age(42).name("민건").teamId(team).build();
			
			team.getMembers().add(m1);
			team.getMembers().add(m2);
			team.getMembers().add(m3);
			team.getMembers().add(m4);
			team.getMembers().add(m5);
			
			em.persist(team);
			em.persist(m1);
			em.persist(m2);
			em.persist(m3);
			em.persist(m4);
			em.persist(m5);
			
			
			em.flush();
			em.clear();
			
			tx.commit();
			
//			simpleQuery(em);
			joinQuery(em);
		} catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}finally {
			em.close();
			PublicCommon.close();
		}
	}

}
