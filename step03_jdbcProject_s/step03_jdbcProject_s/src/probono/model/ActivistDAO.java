/* CREATE TABLE activist (
       activist_id          VARCHAR2(20)  PRIMARY KEY,
       name                 VARCHAR2(20) NULL,
       password             VARCHAR2(20) NULL,
       major                VARCHAR2(50) NULL
);  */
package probono.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import probono.model.dto.Process1DTO;
import probono.model.util.DBUtil;
//기부자 table과 관계된 DAO 클래스 
public class ActivistDAO {
	
	static Properties comm = DBUtil.getComm();
		//기부자 등록(insert)
		public static boolean addActivist(Process1DTO activist) throws SQLException{
			Connection con = null;
			PreparedStatement pstmt = null;
			try{
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement(comm.getProperty("act.insert"));
				pstmt.setString(1, activist.getId());
				pstmt.setString(2, activist.getName());
				pstmt.setString(3, activist.getPassword());
				pstmt.setString(4, activist.getMajor());
				
				int result = pstmt.executeUpdate();
			
				if(result == 1){
					return true;
				}
			}finally{
				DBUtil.close(con, pstmt);
			}
			return false;
		}
	
		//수정  
		//기부자 id로 주요 기부 내용 수정하기
		public static boolean updateActivist(String activistId, String major) throws SQLException{
			Connection con = null;
			PreparedStatement pstmt = null;
			try{
				con = DBUtil.getConnection();
				
				pstmt = con.prepareStatement(comm.getProperty("act.update"));
				pstmt.setString(1, major);
				pstmt.setString(2, activistId);
				
				int result = pstmt.executeUpdate();
				if(result == 1){
					return true;
				}		
			}finally{
				DBUtil.close(con, pstmt);
			}
			return false;
		}

	
		//??? 삭제
		//sql - delete from activist where activist_id=?
		public static boolean deleteActivist(String activistId) throws SQLException{
			Connection con = null;
			PreparedStatement pstmt = null;
			try{
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement(comm.getProperty("act.delete"));
				pstmt.setString(1, activistId);
				int result = pstmt.executeUpdate();
				if(result == 1){
					return true;
				}
			}catch(SQLException s){
				s.printStackTrace();
				throw s;
			}finally{
				DBUtil.close(con, pstmt);
			}
			return false;
		}
	
		//id로 해당 기부자의 모든 정보 반환
		public static Process1DTO getActivist(String activistId) throws SQLException{
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			Process1DTO activist = null;
			
			try{
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement("select * from activist where activist_id=?");
				pstmt.setString(1, activistId);
				rset = pstmt.executeQuery();
				if(rset.next()){
					activist = new Process1DTO(rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4));
				}			
			}finally{
				DBUtil.close(con, pstmt, rset);
			}
			return activist;
		}

		//???모든 기부자 검색해서 반환
		//sql - select * from activist
		public static ArrayList<Process1DTO> getAllActivists() throws SQLException{
			Connection con = null;
			Statement stmt = null;
			ResultSet rset = null;
			
			ArrayList<Process1DTO> datas = null;
			
			try {
				con = DBUtil.getConnection();
				stmt = con.createStatement();
						
//				rset = stmt.executeQuery("select * from dept");
				rset = stmt.executeQuery(comm.getProperty("actAll"));//멤버 변수로 선언 후 멤버 변수 활용 
				
				
				//정상으로 select문 실행이 완료된 직후에 ArrayList 생성
				//완료되기도 전에 객체 생성과의 차이점 : 비정상 문제 발생 시 ArrayList객체는 어차피 쓰레기 객체
				datas = new ArrayList<>();
				while(rset.next()) {
					datas.add(new Process1DTO(rset.getString("activist_id"),rset.getString("name"),rset.getString("password"),rset.getString("major")));
				}
			}  finally {
				DBUtil.close(con, stmt, rset);
			}
			return datas;
		}
}
