package semiconductor.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import semiconductor.model.dto.Process1DTO;
import semiconductor.model.dto.Process2DTO;
import semiconductor.model.dto.TotalprocessDTO;
import semiconductor.model.util.DBUtil;
import semiconductor.view.RunningEndView;


@Log4j
public class TotalprocessDAO {
	static Properties sql = DBUtil.getSql();


	public static String checkpf(int minute) {
		String result = "정상";
		if(minute == 0) {
			result = "실행 전";
		}else if(minute< 13 || minute > 20) {
			result = "불량";
		}
		return result;
	}

	public static boolean addTotalprocess(int waferId) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		Process1DTO process1 = Process1DAO.getProcess1(waferId);
		Process2DTO process2 = Process2DAO.getProcess2(waferId);
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.getProperty("tpro.insert"));
			pstmt.setInt(1, waferId);
			pstmt.setString(2, checkpf(process1.getClean1()));
			pstmt.setString(3, checkpf(process1.getClean2()));
			pstmt.setString(4, checkpf(process1.getRinse1()));
			pstmt.setString(5, checkpf(process2.getClean3()));
			pstmt.setString(6, checkpf(process2.getRinse2()));
			int result = pstmt.executeUpdate();
			if(result == 1) {
				return true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(con, pstmt);
		}
		return false;
	}

  public static boolean updateTotalprocess(int waferId) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		Process1DTO process1 = Process1DAO.getProcess1(waferId);
		Process2DTO process2 = Process2DAO.getProcess2(waferId);
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.getProperty("tpro.update"));
			pstmt.setString(1, checkpf(process1.getClean1()));
			pstmt.setString(2, checkpf(process1.getClean2()));
			pstmt.setString(3, checkpf(process1.getRinse1()));
			pstmt.setString(4, checkpf(process2.getClean3()));
			pstmt.setString(5, checkpf(process2.getRinse2()));
			pstmt.setInt(6, waferId);
			TotalprocessDTO t = new TotalprocessDTO(waferId, checkpf(process1.getClean1()), 
					checkpf(process1.getClean2()), checkpf(process1.getRinse1()), 
					checkpf(process2.getClean3()), checkpf(process2.getRinse2()));
			int result = pstmt.executeUpdate();
			if(result == 1) {
				return true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(con, pstmt);
		}
		return false;
	}
  
  public static boolean deleteTotalprocess(int waferId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.getProperty("tpro.delete"));
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
