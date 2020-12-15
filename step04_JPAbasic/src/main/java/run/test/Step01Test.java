package run.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import step01.entity.Member;

@Slf4j
public class Step01Test {

	@Test
	public void memberCrud() {
		log.info("member table에 crud 적용하기 ------------------\n");
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("step00_basic");
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();  

		//insert 문장 생성 -> db에 insert 실행 (commit 메소드 호출시에 실제 db에 적용)
		Member member = new Member("id1", "신동엽", 10); 
		em.persist(member); //insert 기능의 메소드, 영속성컨텍스트에저장, insert 생성, 스냅샵에 저장

		member.setAge(40); //update 수행 로직	
		Member oneFindMember = em.find(Member.class, "id1");

		//*** step01.entity.Member@4c03a37
		System.out.println("*** " + oneFindMember);

		//JPQL
		//검색
		List<Member> allMember = em.createQuery("select m from Member m", Member.class).getResultList();
		System.out.println("회원 수 : " + allMember.size());

		for (Member m : allMember) {
			System.out.println(m);
		}
		
		//데이터 삭제 - delete sql문장 생성
		em.remove(oneFindMember);
		
		tx.commit();
		
		System.out.println("-- 삭제 후 검색 해 보기 --");
		oneFindMember = em.find(Member.class, "id1");
		System.out.println(oneFindMember);
		
	}

}
