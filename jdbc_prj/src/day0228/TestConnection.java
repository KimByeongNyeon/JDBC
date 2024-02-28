package day0228;
/**
 * DBMS 연동
 */
public class TestConnection {
public TestConnection() {
	//1.드라이버 로딩
	try {
		Class.forName("orcale.jdbc.OracleDriver");
		System.out.println("드라이버 로딩 성공!!");
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}//end catch
	//2.로딩된 드라이버를 사용하여 Connection 얻기
	String url = "jdbc:oracle:thin:@127.0.0.1";
	String pass = "";
	String id = "";
}

public static void main(String[] args) {
	TestConnection tc = new TestConnection();
}
}

