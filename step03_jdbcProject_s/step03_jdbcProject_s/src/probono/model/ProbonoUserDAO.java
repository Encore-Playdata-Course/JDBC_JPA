package probono.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import probono.model.dto.ProbonoUserDTO;
import probono.model.util.DBUtil;
public class ProbonoUserDAO {
	static Properties comm = DBUtil.getComm();
		//프로보노 프로젝트 저장
			public static boolean addProbonoUser(ProbonoUserDTO probonoUser) throws SQLException{
				Connection con = null;
				PreparedStatement pstmt = null;
				try{
					con = DBUtil.getConnection();
					pstmt = con.prepareStatement(comm.getProperty("ProUser.insert"));
					pstmt.setInt(1, probonoUser.getProuserId());
					pstmt.setString(2, probonoUser.getProbonoId());
					pstmt.setString(3, probonoUser.getActivistId());
					pstmt.setString(4, probonoUser.getReceiveId());
					pstmt.setString(5, probonoUser.getProjectContent());
					
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
			// 프로보노 프로젝트 ID로  재능기부자 수정
			public static boolean updateProbonoUserActivist(int prouserId, String activistId) throws SQLException{		
				Connection con = null;
				PreparedStatement pstmt = null;
				try{
					con = DBUtil.getConnection();
					
					pstmt = con.prepareStatement(comm.getProperty("ProUser.Aupdate"));
					pstmt.setString(1, activistId);
					pstmt.setInt(2, prouserId);
					
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
			//프로보노 프로젝트 id로 수해자 정보 변경
			public static boolean updateProbonoUserReceive(int prouserId, String  receiveId) throws SQLException{
				Connection con = null;
				PreparedStatement pstmt = null;
				try{
					con = DBUtil.getConnection();
					
					pstmt = con.prepareStatement(comm.getProperty("ProUser.Rupdate"));
					pstmt.setString(1, receiveId);
					pstmt.setInt(2, prouserId);
					
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
			//프로보노 프로젝트 id로 프로보노 프로젝트 삭제
			public static boolean deleteProbonoUser(int prouserId) throws SQLException{
				Connection con = null;
				PreparedStatement pstmt = null;
				try{
					con = DBUtil.getConnection();
					pstmt = con.prepareStatement(comm.getProperty("ProUser.Delete"));
					pstmt.setInt(1, prouserId);
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
			
			//프로보노 프로젝트 id로 해당 프로보노프로젝트 검색
			public static ProbonoUserDTO getProbonoUser(int prouserId) throws SQLException{
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rset = null;
				ProbonoUserDTO probonoUser = null;
				
				try{
					con = DBUtil.getConnection();
					pstmt = con.prepareStatement(comm.getProperty("ProUser.getProUser"));
					pstmt.setInt(1, prouserId);
					rset = pstmt.executeQuery();
					if(rset.next()){
						probonoUser = new ProbonoUserDTO(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5));
					}
				}catch(SQLException s){
					s.printStackTrace();
					throw s;
				}finally{
					DBUtil.close(con, pstmt, rset);
				}
				return probonoUser;
			}
			
			//모든 프로보노 프로젝트 검색
			public static ArrayList<ProbonoUserDTO> getAllProbonoUsers() throws SQLException{
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rset = null;
				ArrayList<ProbonoUserDTO> list = null;
				try{
					con = DBUtil.getConnection();
					pstmt = con.prepareStatement(comm.getProperty("ProUser.All"));
					rset = pstmt.executeQuery();
					
					list = new ArrayList<ProbonoUserDTO>();
					while(rset.next()){
						list.add( new ProbonoUserDTO(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5)) );
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