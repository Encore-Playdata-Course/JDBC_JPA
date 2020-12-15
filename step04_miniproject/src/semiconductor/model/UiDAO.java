package semiconductor.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import semiconductor.model.util.DBUtil;

public class UiDAO {
	static Properties sql = DBUtil.getSql();
	public static boolean addui(int waferId) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.getProperty("ui.insert"));
			pstmt.setInt(1, waferId);

			int result = pstmt.executeUpdate();
		
			if(result == 1){
				return true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	public static String processN(int i) {
		String p = "정상";
		if(i == 2) {
			p = "Clean1";
		}else if(i ==3) {
			p = "Clean2";
		}else if(i ==4) {
			p = "Rinse1";
		}else if(i ==5) {
			p = "Clean3";
		}else if(i ==6) {
			p = "Rinse2";
		}
		return p;
	}
	public static boolean updateUi(int waferId) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;				
		ResultSet rset = null;
		ResultSet rset2 = null;
		ResultSet rset3 = null;
		try{
			con = DBUtil.getConnection();
			pstmt1 = con.prepareStatement(sql.getProperty("tpro.getTotalprocess"));
			pstmt2 = con.prepareStatement(sql.getProperty("ui.updateResult"));
			pstmt3 = con.prepareStatement(sql.getProperty("ui.updateerrorpoint"));
			pstmt1.setInt(1, waferId);
			rset = pstmt1.executeQuery();
			if(rset.next()){
				for(int i =2;i<7;i++) {
					if(rset.getString(i).equals("불량")) {
						pstmt2.setString(1, "불량");
						pstmt2.setInt(2, waferId);
						pstmt3.setString(1, processN(i));
						pstmt3.setInt(2, waferId);
						break;
					}else if(rset.getString(i).equals("실행 전")) {
						pstmt2.setString(1, "실행 전");
						pstmt2.setInt(2, waferId);
						pstmt3.setString(1, processN(i));
						pstmt3.setInt(2, waferId);
						break;
					}else {
						pstmt2.setString(1, "정상");
						pstmt2.setInt(2, waferId);
						pstmt3.setString(1, "전과정성공");
						pstmt3.setInt(2, waferId);
					}
					
			}
			rset2 = pstmt2.executeQuery();
			rset3 = pstmt3.executeQuery();
				return true;				
			}		
		}finally{
			DBUtil.close(con, pstmt1);
			DBUtil.close(con, pstmt2);
			DBUtil.close(con, pstmt3);
		}
		return false;
	}
	public static boolean deleteUi(int waferId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.getProperty("ui.delete"));
			pstmt.setInt(1, waferId);
			int result = pstmt.executeUpdate(); // CUD에 executeUpdate 사용
			if (result == 1){
				return true;
			}
		} catch (SQLException s) {
			s.printStackTrace();
			throw s;
		} finally {
			DBUtil.close(con, pstmt); // 질문 프로세스1 웨이퍼아이디 지움 > 나머지 자동삭제 원리 물어봐라 선생님들한테
		}
		return false;
	}
	
}
