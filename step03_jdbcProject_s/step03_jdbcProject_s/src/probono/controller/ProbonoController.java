package probono.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import probono.model.ProbonoService;
import probono.model.dto.ProbonoDTO;
import probono.view.RunningEndView;

//현 로직 : view.RunningStrartView에서 호출 
public class ProbonoController {
	
	
	//모든 probono 검색 로직
	public static ArrayList<ProbonoDTO> getAllProbonos(){
		ArrayList<ProbonoDTO> allProbono = null;
		try{			
			allProbono = ProbonoService.getAllProbonos();			
		}catch(SQLException s){
			s.printStackTrace();
			RunningEndView.showError("모든 Probono 검색시 에러 발생");
		}
		return allProbono;
	}
	
	//새로운 probono 저장 로직
	public static boolean addProbono(ProbonoDTO probono) {
		boolean result = false;
		try{
			result = ProbonoService.addProbono(probono);
		}catch(SQLException s){
			s.printStackTrace();
			RunningEndView.showError("모든 probono 저장시 에러 발생");
		}
		return result;
	}
		
	//프로보노 아이디로 프로보노 목적 수정
	public static boolean updateProbono(String probonoId, String probonoPurpose){
		boolean result = false;
		try{
			result = ProbonoService.updateProbono(probonoId, probonoPurpose);
		}catch(Exception s){
			s.printStackTrace();
			RunningEndView.showError("프로보노 id로 프로보노 목적 변경 오류");
		}
		return result;
	}
	
	//프로보노 정보 검색
	public static ProbonoDTO getProbono(String probonoId) {
		ProbonoDTO probono = null;
		try {
			probono = ProbonoService.getProbono(probonoId);
		} catch (Exception e) {
			e.printStackTrace();
			RunningEndView.showError("프로보노 id로 해당 프로보노 검색 오류 ");
		}
		return probono;
	}
}
