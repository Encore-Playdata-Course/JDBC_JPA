package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PublicCommon {
	//db driver처럼 한번만 메모리에 생성 후 저장 및 쭉~ 쭉 재사용
	private static EntityManagerFactory emf;
	static {
		emf = Persistence.createEntityManagerFactory("oracleDBUse");
	}
	
	
	//CRUD 로직별 생성 및 client는 공유 금지
	//? EntityManager 반환
	public static EntityManager geEntityManager() {
		return emf.createEntityManager();
	}
	//? 자원반환
	/* 1. emf만 close?
	 * 2. emf? EntityManager close?
	 * 3. EntityManager close?
	 * 		-27line(em.close();) 한 문장만 실행한다면... 1. 메소드 분리해서 호출? 2. 사용했던 각 메소드에서 em.close() 코드로 처리?
	 */
	public static void close() {
		emf.close();
	}
}
