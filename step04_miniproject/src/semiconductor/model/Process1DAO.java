package semiconductor.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import semiconductor.model.dto.Process1DTO;
import semiconductor.model.util.DBUtil;



public class Process1DAO {
	static Properties sql = DBUtil.getSql();
	//기부자 등록(insert)
	public static boolean addProcess(int waferId) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.getProperty("pro1.insert"));
			pstmt.setInt(1, waferId);

			int result = pstmt.executeUpdate();
		
			if(result == 1){
				Process2DAO.addProcess2(waferId);
				TotalprocessDAO.addTotalprocess(waferId);
				UiDAO.addui(waferId);
				return true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return false;
	}

	//수정  
	//기부자 id로 주요 기부 내용 수정하기
	public static boolean updateClean1(int waferId, int minute) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBUtil.getConnection();
			
			pstmt = con.prepareStatement(sql.getProperty("pro1.updateClean1"));
			pstmt.setInt(1, minute);
			pstmt.setInt(2, waferId);
			
			int result = pstmt.executeUpdate();
			if(result == 1){
				TotalprocessDAO.updateTotalprocess(waferId);
				UiDAO.updateUi(waferId);
				return true;
			}		
		}finally{
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	public static boolean updateClean2(int waferId, int minute) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBUtil.getConnection();
			
			pstmt = con.prepareStatement(sql.getProperty("pro1.updateClean2"));
			pstmt.setInt(1, minute);
			pstmt.setInt(2, waferId);
			
			int result = pstmt.executeUpdate();
			if(result == 1){
				TotalprocessDAO.updateTotalprocess(waferId);
				UiDAO.updateUi(waferId);
				return true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	public static boolean updateRinse1(int waferId, int minute) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBUtil.getConnection();
			
			pstmt = con.prepareStatement(sql.getProperty("pro1.updateRinse1"));
			pstmt.setInt(1, minute);
			pstmt.setInt(2, waferId);
			
			int result = pstmt.executeUpdate();
			if(result == 1){
				TotalprocessDAO.updateTotalprocess(waferId);
				UiDAO.updateUi(waferId);
				return true;
			}		
		}finally{
			DBUtil.close(con, pstmt);
		}
		return false;
	}

	public static boolean deleteProcess(int waferId) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.getProperty("pro1.delete"));
			pstmt.setInt(1, waferId);
			int result = pstmt.executeUpdate();
			if(result == 1){
				Process2DAO.deleteProcess2(waferId);
				TotalprocessDAO.deleteTotalprocess(waferId);
				UiDAO.deleteUi(waferId);
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

	public static Process1DTO getProcess1(int waferId) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Process1DTO process1 = null;
		
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.getProperty("pro1.getProcess1"));
			pstmt.setInt(1, waferId);
			rset = pstmt.executeQuery();
			if(rset.next()){
				process1 = new Process1DTO(rset.getInt(1), rset.getInt(2), rset.getInt(3), rset.getInt(4));
			}			
		}finally{
			DBUtil.close(con, pstmt, rset);
		}
		return process1;
	}

	//???모든 기부자 검색해서 반환
	//sql - select * from activist
	public static ArrayList<Process1DTO> getAllProcess1() throws SQLException{
		Connection con = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		ArrayList<Process1DTO> datas = null;
		
		try {
			con = DBUtil.getConnection();
			stmt = con.createStatement();
					
			rset = stmt.executeQuery(sql.getProperty("pro1All"));//멤버 변수로 선언 후 멤버 변수 활용 
			
			
			datas = new ArrayList<>();
			while(rset.next()) {
				datas.add(new Process1DTO(rset.getInt(1), rset.getInt(2), rset.getInt(3), rset.getInt(4)));
			}
		}  finally {
			DBUtil.close(con, stmt, rset);
		}
		return datas;
	}
	
	public static void main(String [] args) throws SQLException {
//		addProcess1(4);
//		Process2DAO.addProcess2(4);
//		updateClean1(1, 12);
//		updateClean2(1, 9);
//		updateRinse1(1, 15);
//		Process2DAO.updateClean3(1, 10);
//		Process2DAO.updateRinse2(1, 10);
//		System.out.println(getProcess1(1));
//		System.out.println(Process2DAO.getProcess2(1));
//		TotalprocessDAO.addTotalprocess(3);
//		UiDAO.addui(3);
		UiDAO.updateUi(3);

	}
}
