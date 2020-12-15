/*
 * CREATE TABLE probono (
       probono_id           	VARCHAR2(50) PRIMARY KEY,
       probono_name     	VARCHAR2(50) NOT NULL,
       probono_purpose  VARCHAR2(200) NOT NULL
);  */
package probono.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import probono.model.dto.ProbonoDTO;
import probono.model.util.DBUtil;

public class ProbonoDAO {	
	static Properties comm = DBUtil.getComm();
	//저장
	public static boolean addProbono(ProbonoDTO probono) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(comm.getProperty("Probono.insert"));
			pstmt.setString(1, probono.getProbonoId());
			pstmt.setString(2, probono.getProbonoName());
			pstmt.setString(3, probono.getProbonoPurpose());
			
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

	//수정
	//프로보노 id로 프로보노 목적 수정하기
	public static boolean updateProbono(String probonoId, String probonoPurpose) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(comm.getProperty("Probono.update"));
			pstmt.setString(1, probonoPurpose);
			pstmt.setString(2, probonoId);
			
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


	//삭제 
	public static boolean deleteProbono(String probonoId) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(comm.getProperty("Probono.delete"));
			pstmt.setString(1, probonoId);
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
	
	//프로포노 아이디로 해당 프로보노 모든 정보 검색
	public static ProbonoDTO getProbono(String probonoId) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ProbonoDTO probono = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(comm.getProperty("Probono.getProbono"));
			pstmt.setString(1, probonoId);
			rset = pstmt.executeQuery();
			if(rset.next()){
				probono = new ProbonoDTO(rset.getString(1), rset.getString(2), rset.getString(3));
			}
		}catch(SQLException s){
			s.printStackTrace();
			throw s;
		}finally{
			DBUtil.close(con, pstmt, rset);
		}
		return probono;
	}

	//모든 프로보노 검색
	public static ArrayList<ProbonoDTO> getAllProbonos() throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ProbonoDTO> list = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(comm.getProperty("Probono.All"));
			rset = pstmt.executeQuery();
			
			list = new ArrayList<ProbonoDTO>();
			while(rset.next()){
				list.add(new ProbonoDTO(rset.getString(1), rset.getString(2), rset.getString(3)) );
			}
		}catch(SQLException s){
			s.printStackTrace();
			throw s;
		}finally{
			DBUtil.close(con, pstmt, rset);
		}
		return list;
	}
}
