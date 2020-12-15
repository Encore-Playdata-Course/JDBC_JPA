package probono.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import probono.model.ProbonoService;
import probono.model.dto.Process1DTO;
import probono.view.RunningEndView;

//현 로직 : view.RunningStrartView에서 호출 
public class ActivistController {
		
	//모든 Activist 검색 로직
		public static ArrayList<Process1DTO> getAllActivists(){
			ArrayList<Process1DTO> allProject = null;
			try{
				allProject = ProbonoService.getAllActivists();			
			}catch(SQLException s){
				s.printStackTrace();
				RunningEndView.showError("모든 재능 기부자 검색시 에러 발생");
			}
			return allProject;
		}
}
