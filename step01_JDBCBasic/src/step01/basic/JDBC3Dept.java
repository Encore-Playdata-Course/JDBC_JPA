/* 학습내용
 * 1. dept 라는 table을 java program으로 crud 로직 처리하는 연습
 * 2. non-pattern 즉 연습용 코드
 * 3. 실습 순서
 * 	1. 존재하는 데이터들 검색(select = query)
 * 	2. 하나의 새로운 부서 추가 저장(insert-존재하는 table의 내용 갱신 : DML) - 검색
 *	3. 데이터 수정(update-존재하는 table의 내용 갱신 : DML) - 검색
 *	4. 데이터 삭제(delete-존재하는 table의 내용 갱신 : DML) - 검색
 *	*5. JDBC 주요 API
 *		1. select(query) 실행 메소드
 *			ResultSet executeQuery(String select)
 *				row가 있는 상태의 ResultSet 객체 or
 *				row가 없는 상태의 ResultSet 객체
 *
 *		2. insert/update/delete 실행 메소드
 *			int executionUpdate(String dml)
 * 4. 공통된 로직
 * 	1. driver 로딩 필수
 * 	2. 접속 - Connection 객체 획득
 * 		- url / ID / PW 동일
 * 	3. sql 문장 실행 - Statement 객체 생성 및 메소드로 crud로직 실행 및 결과 확인
 * 		- 로직별 다름
 * 	4. db접속 해제 - 자원 반환
 * 
 */
package step01.basic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import util.DBUtil;

public class JDBC3Dept {
	
	
	static void select() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rset = null;
		try {
			con = DBUtil.getConnection();
			
			stmt = con.createStatement();
			
			String sql = "select * from dept";
			rset = stmt.executeQuery(sql);
			System.out.println(rset);//oracle.jdbc.driver.OracleResultSetImpl@61386958
			while(rset.next()) {
//				System.out.println(rset.getInt(1)+ " "+ rset.getString(2)+ " "+rset.getString(3));
				System.out.println(rset.getInt("deptno")+ " "+ rset.getString("dname")+ " "+rset.getString("loc"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("죄송합니다 접속문제가 생겼으니 잠시 후에 재 요청해주세요~");
		} finally {
			DBUtil.close(con, stmt, rset);
		}		
	}
	
	static void insert() {
		Connection con = null;
		Statement stmt = null;
		
		try {
			con = DBUtil.getConnection();
			stmt = con.createStatement();
			
			//rset = stmt.executeQuery(sql);
			int result = stmt.executeUpdate("insert into dept values(60,'인사과','남부')");
			if(result != 0) {
				System.out.println("저장 성공");
			}else {
				System.out.println("저장 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("죄송합니다 접속문제가 생겼으니 잠시 후에 재 요청해주세요~");
		} finally {
			DBUtil.close(con, stmt);
		}		
	}
	
	//update - update dept set loc = '서초' where deptno = 60;
	static void update() {
		Connection con = null;
		Statement stmt = null;
		
		try {
			con = DBUtil.getConnection();
			stmt = con.createStatement();
			
			//rset = stmt.executeQuery(sql);
			int result = stmt.executeUpdate("update dept set loc = '서초' where deptno = 60");
			if(result != 0) {
				System.out.println("갱신 성공");
			}else {
				System.out.println("갱신 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("죄송합니다 접속문제가 생겼으니 잠시 후에 재 요청해주세요~");
		} finally {
			DBUtil.close(con, stmt);
		}
		
	}
	
	//delete - delete from dept where deptno = 60;
	//?
	static void delete() {
		Connection con = null;
		Statement stmt = null;
		
		try {
			con = DBUtil.getConnection();
			stmt = con.createStatement();
			
			//rset = stmt.executeQuery(sql);
			int result = stmt.executeUpdate("delete from dept where deptno = 60");
			if(result != 0) {
				System.out.println("삭제 성공");
			}else {
				System.out.println("삭제 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("죄송합니다 접속문제가 생겼으니 잠시 후에 재 요청해주세요~");
		} finally {
			DBUtil.close(con, stmt);
		}
	}
	
	
	public static void main(String[] args) {
		//select 호출
		select();
//		System.out.println("-- 저장 --");
//		insert();
//		System.out.println("-- 저장 후 검색 --");
//		select();
//		System.out.println("-- 수정 --");
//		update();
//		System.out.println("-- 수정 후 검색 --");
//		select();
		System.out.println("-- 삭제 --");
		delete();
		System.out.println("-- 삭제 후 검색");
		select();
	}

}
