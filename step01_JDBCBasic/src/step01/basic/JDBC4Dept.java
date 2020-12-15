/* 1. 추가 응용
 * - web 브라우저를 통해서 실시간 검색? 수정? 삭제? 저장? 등의 개별 로직 호출 시에 입력데이터가 client별 다르게 요청이 들어온다 가정
 * 
 * 2. sql 문장 실행 가능한 활용 API
 * 	1. java.sql.Statement
 * 		- sql문장 구성 시 문자열의 단일 따옴표 등 명확하게 처리 필수
 * 
 * 	2. java.sql.PrepareStatement
 * 		- sql문장 구성 시 단일 따옴표 처리 불필요, 데이터값 위치에? 표기로만 처리
 * 		- Statement 상속
 * 		- 실무형 API
 * 		- DB들이 실행 시 인식할 때 Statement 보다 실행 속도가 더 향상된 형식으로 처리
 * 		- sql 실행 요청 프로세스
 * 			db가 요청 수락 -> sql문장 분석 -> 검증 후 sql 문장을 db만의 실행 코드로 변환 -> 실행
 * 			동일한 sql 요청이 유입되었을 때는 이미 변환된 실행코드만 실행 (sql문장 분석 -> 검증 후 ql 문장을 db만의 실행 코드로 변환 skip)
 */
package step01.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import util.DBUtil;

public class JDBC4Dept {
	
	
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
	
	static void insert(int newDeptno, String newDname, String newLoc) {
		Connection con = null;
		//Statement stmt = null;
		PreparedStatement pstmt = null;		
		
		try {
			con = DBUtil.getConnection();
//			stmt = con.createStatement();
			pstmt = con.prepareStatement("insert into dept values(?, ?, ?)");
			
			
			//rset = stmt.executeQuery(sql);
//			int result = stmt.executeUpdate("insert into dept values(60,'인사과','남부')");
			
			//? 표기의 sql문장에 데이터 값 셋팅 - 각 column별로 별도로 값 셋팅
			pstmt.setInt(1, newDeptno);
			pstmt.setString(2, newDname);
			pstmt.setString(3, newLoc);
			
			int result = pstmt.executeUpdate(); //db에 실제 insert하는 로직
			
			if(result != 0) {
				System.out.println("저장 성공");
			}else {
				System.out.println("저장 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("죄송합니다 접속문제가 생겼으니 잠시 후에 재 요청해주세요~");
		} finally {
			DBUtil.close(con, pstmt);
		}		
	}
	
	//update - update dept set loc = '서초' where deptno = 60;
	static void update(String newLoc, int newDeptno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("update dept set loc = ? where deptno = ?");
			
			//rset = stmt.executeQuery(sql);
//			int result = stmt.executeUpdate("update dept set loc = '서초' where deptno = 60");
					
			pstmt.setString(1, newLoc);
			pstmt.setInt(2, newDeptno);
					
			int result = pstmt.executeUpdate();		
			if(result != 0) {
				System.out.println("갱신 성공");
			}else {
				System.out.println("갱신 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("죄송합니다 접속문제가 생겼으니 잠시 후에 재 요청해주세요~");
		} finally {
			DBUtil.close(con, pstmt);
		}
		
	}
	
	//delete - delete from dept where deptno = 60;
	//?
	static void delete(int deptno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("delete from dept where deptno = ?");
			
			pstmt.setInt(1, deptno);
			
			int result = pstmt.executeUpdate();
			if(result != 0) {
				System.out.println("삭제 성공");
			}else {
				System.out.println("삭제 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("죄송합니다 접속문제가 생겼으니 잠시 후에 재 요청해주세요~");
		} finally {
			DBUtil.close(con, pstmt);
		}
	}
	
	
	public static void main(String[] args) {
		//select 호출
		select();
//		System.out.println("-- 저장 --");
//		insert(50, "hr", "일산");
//		System.out.println("-- 저장 후 검색 --");
//		select();
//		System.out.println("-- 수정 --");
//		update("서초",50);
//		System.out.println("-- 수정 후 검색 --");
//		select();
		System.out.println("-- 삭제 --");
		delete(50);
		System.out.println("-- 삭제 후 검색");
		select();
	}

}
