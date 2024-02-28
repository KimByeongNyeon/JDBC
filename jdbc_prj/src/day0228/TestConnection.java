package day0228;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * DBMS 연동
 */
public class TestConnection {
@SuppressWarnings("unused")
public TestConnection() throws SQLException {
	//1.드라이버 로딩
	try {
		Class.forName("oracle.jdbc.OracleDriver");
		System.out.println("드라이버 로딩 성공!!");
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}//end catch
	//2.로딩된 드라이버를 사용하여 Connection 얻기
	String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
	String pass = "tiger";
	String id = "scott";
	
	Connection con = null;
	Statement stmt = null;
	try {
		//2.로딩된 드라이버를 사용하여 Connection 얻기
		con = DriverManager.getConnection(url, id, pass);//con은 autocommit 기본설정
		//con.setAutoCommit(false);
		System.out.println("DB연결 성공");
		//3.쿼리문 생성객체 얻기
		stmt = con.createStatement();
		//4.쿼리문 수행 후 결과 얻기 ( CP_DEPT 테이블에 레코드를 추가 )
		int deptno = 21;
		String dname = "영업부";
		String loc = "서울";
		
//		String insertCpDept = "insert into cp_dept(deptno,dename,loc) values"
//				+ "("+deptno+",'"+dname+"', '" + loc +"')"; //자바에서 실행하는 쿼리문에는 절대로 ;를 붙이지 않는다.
		StringBuilder insertCpDept = new StringBuilder();
		insertCpDept.append("insert into cp_dept(deptno,dname,loc) values (")
				.append(deptno).append(", '").append(dname).append("', '").append(loc).append("')");
		
		System.out.println(insertCpDept);
		
		int cnt = stmt.executeUpdate(insertCpDept.toString());
		System.out.println(cnt+"건의 레코드가 추가되었습니다.");
		
		if(cnt == 1) {
			con.commit();
		}
	} finally {
		//5.연결 끊기
		if(con != null) {con.close();}//end if
	}
}

public static void main(String[] args) {
	try {
		TestConnection tc = new TestConnection();
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
}

