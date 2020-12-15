package run.test;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.jupiter.api.Test;

import step01.entity.Dept01;

public class Dept01Test {
	@Test
	public void crudTest() {
		//EntityManagerFactory 생성
		//xml에 설정된 entity 클래스로 매핑되는 table 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("oracleDBUse");
		
		//EntityManager 생성
		EntityManager em = emf.createEntityManager();
		
		//EntityTransaction 생성
		//insert.update.delete - DML에 필수인 API
//		EntityTransaction tx = em.getTransaction();
//		tx.begin();
//		
//		//데이터 저장 - insert
//		Dept01 dept = new Dept01(new BigDecimal("1"), "인사과", "남부");
//		em.persist(dept); //영속성 컨텍스트에 저장
//		
//		dept.setDname("총부부"); // 영속성 컨텍스트
//		
//		//crud
//		tx.commit();
		em.close();
		emf.close();
		
	}
}
