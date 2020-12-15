/* 모든 DAO가 공유하게 되는 자원 제공 및 자원 반환 처리
 * sql 의 내용을 load하고 있는 Properties 객체도 단일 객체로 생성해서 모든 DAO가 공유
 * 
 * 
 */
package util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBUtil {
	private static Properties dbInfo = new Properties();//DBUtil에서만 사용
	private static Properties sqlAll = new Properties();//DAO 클래스들이 사용하고자 하는 객체
	
	
	static {		
		try {
			dbInfo.load(new FileInputStream("dbinfo.properties"));
			sqlAll.load(new FileInputStream("allSql.properties"));
			
			
			
			
			Class.forName(dbInfo.getProperty("driver"));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("해당 driver가 없습니다.");
		}
	}
	
	//요청시마다 새로운 Connection 객체 생성해서 반환하는 메소드
	//URL, ID, PW
	//접속 문제 발생 : 예외처리로 유연하게 처리
	//? 1번 : 이 메소드 호출한 곳으로 예외 던져? 2번 : 이 메소드 내에서 try~catch로 처리하고 끝내?
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(dbInfo.getProperty("url"), 
										dbInfo.getProperty("id"),
										dbInfo.getProperty("pw"));
	}
	
	public static Properties getSqlAll() {
		return sqlAll;
	}
	
//	//단순 확인용 test 메소드 - 차후삭제예정
//	//단위 테스트
//	public static void main(String [] args) {
//		try {
//			System.out.println(getConnection());
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//	}

	//insert/update/delete = DML 자원바환
	public static void close(Connection con, Statement stmt) {
		try {
			if(stmt != null) {
				stmt.close();
			}
			if(con != null) {
				con.close();				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Connection con, Statement stmt, ResultSet rset) {
		try {
			if(rset != null) {
				rset.close(); //sql문장 오류시 null인 상태로 close()시도
				rset = null; //메모리 회수 요청을 하는 코드로 간주
			}
			if(stmt != null) {
				stmt.close();
			}
			if(con != null) {
				con.close();				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
