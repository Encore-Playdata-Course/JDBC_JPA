package test;

import java.sql.SQLException;
import java.util.ArrayList;

import model.DeptDAO;
import model.domain.DeptDTO;

public class DeptTest {

	public static void main(String[] args) {
		//모든 검색 - 버튼 클릭으로 요청
		try {
			ArrayList<DeptDTO> all = DeptDAO.deptAll();
			//차후에 EndView로 출력 위임
			for(DeptDTO dept : all) {
				System.out.println(dept); //dept.toString() 코드가 자동 완성
			}
		} catch (SQLException e) {
			e.printStackTrace();
			//예외 메세지는 차후에 FailView로 출력 위임
			System.out.println("모든 부서 정보 검색 실패, 재 요청해 주세요");
		}		 
		
		//저장 - deptno/dname/loc - 
//		try {
//			//client 가 입력한 데이터의 포멧 검증 완료 했다 가정
//			boolean result = DeptDAO.insert(new DeptDTO(72, "재무팀", "마포"));
//			if(result == true) {
//				System.out.println("저장성공");
//			}else {
//				System.out.println("다시 시도해 보세요");
//			}
//		} catch (SQLException e) { //부서번호 중복시 에러 발생 -> SQLException 과 연관
//			e.printStackTrace();
//			System.out.println("이미 해당 부서 번호는 존재합니다 재 확인후 저장시도 하세요");
//		}
				 
		//저장된 dept 정보만 검색 - deptno값으로 검색
		try {
			DeptDTO dept = DeptDAO.getDept(72);
			System.out.println(dept);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("부서 번호로 검색시 문제 발생, 잠시후에 재요청 해 주세요");
		}
		
		//?수정 - deptno기준으로 loc 값 수정
		try {
			//client 가 입력한 데이터의 포멧 검증 완료 했다 가정
			boolean result = DeptDAO.update(72, "관악");
			if(result == true) {
				System.out.println("수정성공");
			}else {
				System.out.println("다시 시도해 보세요");
			}
		} catch (SQLException e) { //부서번호 중복시 에러 발생 -> SQLException 과 연관
			e.printStackTrace();
			System.out.println("이미 해당 부서 번호는 존재하지 않습니다 재 확인후 저장시도 하세요");
		}
		
		//?검색 - deptno로 값 검색
		
		//?삭제 - deptno로 삭제
		try {
			//client 가 입력한 데이터의 포멧 검증 완료 했다 가정
			boolean result = DeptDAO.delete(72);
			if(result == true) {
				System.out.println("삭제성공");
			}else {
				System.out.println("다시 시도해 보세요");
			}
		} catch (SQLException e) { //부서번호 중복시 에러 발생 -> SQLException 과 연관
			e.printStackTrace();
			System.out.println("이미 해당 부서 번호는 존재하지 않습니다 재 확인후 저장시도 하세요");
		}
		//?모든 검색 - 
		try {
			ArrayList<DeptDTO> all = DeptDAO.deptAll();
			//차후에 EndView로 출력 위임
			for(DeptDTO dept : all) {
				System.out.println(dept); //dept.toString() 코드가 자동 완성
			}
		} catch (SQLException e) {
			e.printStackTrace();
			//예외 메세지는 차후에 FailView로 출력 위임
			System.out.println("모든 부서 정보 검색 실패, 재 요청해 주세요");
		}
	}

}