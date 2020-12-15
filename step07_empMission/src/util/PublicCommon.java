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
	
	
	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	public static void close() {
		emf.close();
	}
}
