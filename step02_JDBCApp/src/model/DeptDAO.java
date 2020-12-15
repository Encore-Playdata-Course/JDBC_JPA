package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import model.domain.DeptDTO;
import util.DBUtil;

public class DeptDAO {
	//? allSql.properties 파일의 내용을 read해서 각각의 메소드의 sql문장 반영하기
	//? allSql.properties 다수의 DAO가 공유해서 사용하는 자원 파일 - 최적의 구조로 설계해서 개발 하고자 한다면?
	
	// 모든 메소드가 allSql.properties 파일의 내용을 load하고 있는 객체를 사용
	static Properties sqlAll = DBUtil.getSqlAll();
	
	
	//dept가 존재하는 수 만큼 DeptDTO 생성해서 ArrayList에 저장 후 반환
	public static ArrayList<DeptDTO> deptAll() throws SQLException{
		Connection con = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		ArrayList<DeptDTO> datas = null;
		
		try {
			con = DBUtil.getConnection();
			stmt = con.createStatement();
					
//			rset = stmt.executeQuery("select * from dept");
			rset = stmt.executeQuery(sqlAll.getProperty("deptAll"));//멤버 변수로 선언 후 멤버 변수 활용 
			
			
			//정상으로 select문 실행이 완료된 직후에 ArrayList 생성
			//완료되기도 전에 객체 생성과의 차이점 : 비정상 문제 발생 시 ArrayList객체는 어차피 쓰레기 객체
			datas = new ArrayList<>();
			while(rset.next()) {
				datas.add(new DeptDTO(rset.getInt("deptno"),rset.getString("dname"),rset.getString("loc")));
			}
		}  finally {
			DBUtil.close(con, stmt, rset);
		}
		return datas;
	}
	
	public static DeptDTO getDept(int deptno) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
				
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sqlAll.getProperty("dept.getDept"));
			
			pstmt.setInt(1, deptno);
			
			rset = pstmt.executeQuery();//멤버 변수로 선언 후 멤버 변수 활용 
			
			if(rset.next()) {
				return new DeptDTO(rset.getInt("deptno"),rset.getString("dname"),rset.getString("loc"));				
			}
		}  finally {
			DBUtil.close(con, pstmt, rset);
		}
		return null;
	}
	
	//Controller에서 view 화면 통해서 입력된 데이터를 가령 포맷 검증 후 문제 없을 경우 DeptDTO 객체 생성해서 저장요청
	public static boolean insert(DeptDTO newdept) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;		
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sqlAll.getProperty("dept.insert"));
			
			pstmt.setInt(1, newdept.getDeptno());
			pstmt.setString(2, newdept.getDname());
			pstmt.setString(3, newdept.getLoc());
			
			if(pstmt.executeUpdate() != 0) {
				return true;
			}
		}  finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	//? 로직 수정해서 코드 완성하기
	public static boolean update(int deptno, String newLoc) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sqlAll.getProperty("dept.update"));
			
					
			pstmt.setString(1, newLoc);
			pstmt.setInt(2, deptno);
					
			if(pstmt.executeUpdate() != 0) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	public static boolean delete(int deptno) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sqlAll.getProperty("dept.delete"));
			
			pstmt.setInt(1, deptno);
			
			if(pstmt.executeUpdate() != 0) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	
}
