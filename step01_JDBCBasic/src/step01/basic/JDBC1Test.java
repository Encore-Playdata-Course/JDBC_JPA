//DB접속 확인용 소스
package step01.basic;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBC1Test {

	public static void main(String[] args) {
		//db 접속을 위한 driver 인스톨 개념 및 DB접속 확인
		//byte code 메모리에 로딩하는 로직
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//db의 ip/id/pw 로 db 접속 시도
			//각 벤더사에서 설정한 데이터 활용해서 해당 db에 접속을 해야 함
			//oracle이 접속을 허락해주는 개발 언어 종류?? java, python, c#, php..., ruby,...
			//각 언어별 support 해주는 driver들을 구분해서 제공
			//벤더사에서 driver에 별도의 값을 설정 - url 값
			//하나의 java 프로그램이 동시에 2개 이상의 db와 연동이 되기도 함
			//java 코드는 각 db 구분을 명확히 함 + 각 db별 driver 구분 필요 - 관리자 제공 받음
			//DB 접속 API
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
			Connection con = DriverManager.getConnection(url, "SCOTT", "TIGER");
			System.out.println(con); //oracle.jdbc.driver.T4CConnection@4d76f3f8
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
