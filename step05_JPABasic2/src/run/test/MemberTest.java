package run.test;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import step03.entity.sequence.Member3;

@Slf4j
public class MemberTest {
	@Test
	public void crudTest() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("oracleDBUse");
		
		EntityManager em = emf.createEntityManager();
		
		//insert/update/delete
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//? id는 시퀀스 즉 oracle db 자체적으로 자동 증가시키는 컬럼
		// client가 입력 못하는 수정 불가한 데이터로 간주
		//? 객체 생성
		Member3 m1 = new Member3();
		
		m1.setName("유재석");
		m1.setAge(47);
		m1.setGender("남자");
		
		em.persist(m1);
		
		Member3 m2 = new Member3();
		
		m2.setName("김민건");
		m2.setAge(26);
		m2.setGender("남자");
		
		em.persist(m2);
		tx.commit();
		
		em.close();
		emf.close();
		
	}
}
