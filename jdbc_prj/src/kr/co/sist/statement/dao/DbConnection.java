package kr.co.sist.statement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	public Connection getConnection(String url, String id, String pass)throws SQLException{
		Connection con = null;
		//1.드라이버 로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}//end catch
		
		//2.Connection 얻기
		con = DriverManager.getConnection(url, id, pass);
		
		return con;
	}//getConnection
}
