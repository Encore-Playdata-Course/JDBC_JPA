package probono.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import probono.exception.NotExistException;
import probono.model.ProbonoService;
import probono.model.dto.ProbonoProjectDTO;
import probono.view.RunningEndView;

//현 로직 : view.RunningStrartView에서 호출 
public class ProbonoProjectController {
		
	//모든 프로젝트 검색 로직
	public static ArrayList<ProbonoProjectDTO> getAllProbonoProjects(){
		ArrayList<ProbonoProjectDTO> allProject = null;
		try{			
			allProject = ProbonoService.getAllProbonoUsers();			
		}catch(SQLException s){
			s.printStackTrace();
			RunningEndView.showError("모든 프로젝트 검색시 에러 발생");
		}
		return allProject;
	}
	
	//새로운 프로젝트 저장 로직
	public static boolean addProbonoProject(ProbonoProjectDTO probonoProject) {
		boolean result = false;
		try{
			result = ProbonoService.addProbonoUser(probonoProject);
		}catch(SQLException s){
			s.printStackTrace();
			RunningEndView.showError("모든 프로젝트 저장시 에러 발생");
		}
		return result;
	}
	
	//프로보노 프로젝트 ID로  재능기부자 수정
	public static boolean updateProjectAct(int probonoUserId, String activistId) {
		boolean result = false;
		try{
			result = ProbonoService.updateProbonoUserActivist(probonoUserId, activistId);
		}catch(Exception s){
			s.printStackTrace();
			RunningEndView.showError("프로보노 프로젝트 ID로  재능기부자 수정 오류");
		}
		return result;
	}
	
	//프로보노 아이디로 프로젝트 삭제
	public static boolean deleteProject(int probonoUserId) {
		boolean result = false;
		try {
			result = ProbonoService.deleteProbonoUser(probonoUserId);
		} catch (Exception e) {
			e.printStackTrace();
			RunningEndView.showError("프로젝트 삭제 실패");
		}
		return result;
	}
	
}
