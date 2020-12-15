package run.test;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.jupiter.api.Test;

import step03.entity.Member;
import step03.entity.Team;
import util.PublicCommon;

public class RunningTest {
	
	
	
//	step05 - 즉시 로딩이나 늦은 로딩
//	@Test
	public void runningTest5() {
		EntityManager em = PublicCommon.geEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
			
		try {
			Team team = Team.builder().teamName("team A").members(new ArrayList<Member>()).build();
			
			Member m1 = Member.builder().age(20).name("유재석").teamId(team).build();
			Member m2 = Member.builder().age(30).name("강호동").teamId(team).build();
			
			team.getMembers().add(m1);
			team.getMembers().add(m2);
			
			em.persist(team); //insert 문장생성 + 스냅샷으로 저장 : 영속성 컨텍스트에 저장
			em.persist(m1);
			em.persist(m2);
			
			
			//검색 - 영속성 컨텍스트에서 검색 시도, 동일한 데이터 존재할 경우 db까지 select 실행 안 함
			//영속성 컨텍스트 초기화 - 잔존된 데이터들 삭제 따라서 검색 요청 시 무조건 db에 select 실행
			em.flush();
			em.clear();
			
			Member sm = em.find(Member.class, m1.getMemberId());
			System.out.println(sm.getName());
			System.out.println(sm.getTeamId().getTeamName());
			
			tx.commit(); //실제 insert
		} catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}finally {
			em.close();
			PublicCommon.close();
		}
	}
	
	//step04 -일대다 다대일 관계에서의 Entity
		//@Test
		public void runningTest4() {
			EntityManager em = PublicCommon.geEntityManager();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
				
			try {
				Team team = Team.builder().teamName("team A").members(new ArrayList<Member>()).build();
				Member m1 = Member.builder().age(20).name("유재석").teamId(team).build();
				Member m2 = Member.builder().age(30).name("강호동").teamId(team).build();
				team.getMembers().add(m1);
				team.getMembers().add(m2);
				em.persist(team);
				em.persist(m1);
				em.persist(m2);
				Team teaminfo = em.find(Team.class, team.getTeamId());
				System.out.println("멤버 수 : "+teaminfo.getMembersCount());
				Member sm = em.find(Member.class, 2L);
				System.out.println(sm.getName());
				System.out.println(sm);
				tx.commit();
			} catch(Exception e){
				tx.rollback();
				e.printStackTrace();
			}finally {
				em.close();
				PublicCommon.close();
			}
		}
		
		//step03
			//@Test
			public void runningTest3() {
				EntityManager em = PublicCommon.geEntityManager();
				EntityTransaction tx = em.getTransaction();
				tx.begin();
					
				try {
					Team team = Team.builder().teamName("team A").members(new ArrayList<Member>()).build();
					Member m1 = Member.builder().age(20).name("유재석").teamId(team).build();
					Member m2 = Member.builder().age(30).name("강호동").teamId(team).build();
					team.getMembers().add(m1);
					team.getMembers().add(m2);
					em.persist(team);
					em.persist(m1);
					em.persist(m2);
					tx.commit();
				} catch(Exception e){
					e.printStackTrace();
				}finally {
					em.close();
					PublicCommon.close();
				}
			}
	//step02
	//@Test
	public void runningTest2() {
		EntityManager em = PublicCommon.geEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			Team t1 = Team.builder().teamName("team A").build();
			Team t2 = Team.builder().teamName("team B").build();
			em.persist(t1);
			em.persist(t2);
			Member m1 = Member.builder().teamId(t2).age(26).name("김민건").build();
			em.persist(m1);
			tx.commit();
		} finally {
			em.close();
		}
	}
	
	//step01 - 비합리적인 table 설계로 인한 거지같은 코드 개발 확인
//	@Test
	public void runningTest1() {
		EntityManager em = PublicCommon.geEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			Team t1 = Team.builder().teamName("team A").build();
			em.persist(t1);
			//? Member 객체 생성 후 insert 시도할 경우 Member의 teamId는 이미 존재하는 team의 id값이어야 함
			//Member m1 = Member.builder().team(t1).age(26).name("김민건").build();
			//em.persist(m1);
			tx.commit();
		} finally {
			em.close();
		}
	}
}
