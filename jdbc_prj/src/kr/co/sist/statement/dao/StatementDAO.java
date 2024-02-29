package kr.co.sist.statement.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.statement.vo.EmployeeVO;

/**
 * DAO(Date Access Object) 
 */
public class StatementDAO {

	public void insertEmp(EmployeeVO eVO) throws SQLException{
		//1. 드라이버로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}// end catch
		
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String id="scott";
		String pass="tiger";
		
		Connection con = null;
		Statement stmt = null;
		try {
			//2. 커넥션얻기 : autocommit
			con = DriverManager.getConnection(url, id, pass);
		//3. 쿼리문 생성객체 얻기
			stmt = con.createStatement();
		//4. 쿼리문 수행 후 결과 얻기
			StringBuilder insertEmployee = new StringBuilder();
			insertEmployee
			.append("insert into employee(empno,ename,job,sal) values(")
			.append(eVO.getEmpno()).append(", '").append(eVO.getEname()).append("', '")
			.append(eVO.getJob()).append("', ").append(eVO.getSal()).append(")");
			
			stmt.executeUpdate(insertEmployee.toString());
		}finally {
		//5. 연결 끊기
			if(stmt != null) {stmt.close();}
			if(con != null) {con.close();}
		}
	}//insertEmp
	
	public int updateEmp(EmployeeVO eVO) throws SQLException{
		int cnt = 0;
		//1. 드라이버 로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}//end catch
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String id = "scott";
		String pass = "tiger";
		
		Connection con = null;
		Statement stmt = null;
		
		try {
		//2. 커넥션 얻기
			con=DriverManager.getConnection(url,id,pass);
		//3. 쿼리문 생성객체 얻기
			stmt=con.createStatement();
		//4. 쿼리문 수행 후 결과 얻기
			//사원번호에 해당하는 레코드를 찾아서 직무와 연봉을 변경
			StringBuilder updateEmp = new StringBuilder();
			updateEmp
			.append("update employee	")
			.append("set job='").append(eVO.getJob()).append("', sal=")
			.append(eVO.getSal())
			.append(" where empno=").append(eVO.getEmpno());
			
			cnt = stmt.executeUpdate(updateEmp.toString());
			
			System.out.println(updateEmp);
		} finally {
		//5. 연결 끊기
			if(stmt != null) { stmt.close();}//end if
			if(con != null) {con.close();};//end if
		}//end finally
		return cnt;
	}//updateEmp
	
	public int deleteEmp(int empno) throws SQLException{
		int cnt = 0;
		//1.드라이버 로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}//end catch
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String id = "scott";
		String pass = "tiger";
		
		Connection con = null;
		Statement stmt = null;
		try{//2.커넥션 얻기
		con = DriverManager.getConnection(url,id,pass);
		//3.쿼리문 생성객체 얻기
		stmt = con.createStatement();
		//4.쿼리문 수행 후 결과 얻기
		StringBuilder deleteEmp = new StringBuilder();
		deleteEmp
		.append("delete from employee where empno=")
		.append(empno);
		
		cnt = stmt.executeUpdate(deleteEmp.toString());
		}finally{
			if(stmt != null) {stmt.close();}
			if(con != null) {con.close();}
		}//5.연결 끊기
		return cnt;
	}//updateEmp
	
	public List<EmployeeVO> selectALLEmp() throws SQLException{
		List<EmployeeVO> list = new ArrayList<EmployeeVO>();
		//1.드라이버 로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
		}//end catch
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		String id = "scott";
		String pass = "tiger";
		try {
		//2.커넥션 얻기
			con = DriverManager.getConnection(url, id, pass);
		//3.쿼리문 생성객체 얻기
			stmt = con.createStatement();
		//4.쿼리문 수행 후 결과 얻기
			String select = "select empno, ename, job, sal, hiredate from employee";
			rs = stmt.executeQuery(select);
			EmployeeVO eVO = null;
			while(rs.next()) {
				eVO = new EmployeeVO(rs.getInt("empno"), rs.getString("ename"), rs.getString("job"), rs.getDouble("sal"), 
						rs.getDate("hiredate"));
				list.add(eVO);
			}
		}finally{//5. 연결 끊기
		if(rs != null) {rs.close();}
		if(stmt != null) {stmt.close();}
		if(con != null) {con.close();}
		}
		return list;
	}
	
	public EmployeeVO selectOneEmp(int empno) throws SQLException{
		EmployeeVO eVO = null;
		//1. 드라이버 로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}//end catch
		Connection con = null;
		Statement stmt = null;
		
		ResultSet rs = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String id = "scott";
		String pass = "tiger";
		try {
		//2. 커넥션 얻기
		con=DriverManager.getConnection(url,id,pass);
		//3. 쿼리문 생성객체 얻기
		stmt=con.createStatement();
		//4. 쿼리문 수행 후 결과 얻기
		StringBuilder selectEmp = new StringBuilder();
		selectEmp
		.append(" select ename, job, sal, hiredate ")
		.append(" from   employee ")
		.append(" where  empno=").append(empno);
		
		rs = stmt.executeQuery(selectEmp.toString());
		
		String ename = "", job = "";
		double sal = 0.0;
		Date hiredate = null;
		
		if(rs.next()) {//쿼리문이 실행되고 결과가 나왔을 때 레코드포인터 (cursor)
			//레코드 포인터가 다음 레코드로 이동하고, 이동된 위치의 컬럼 값을 얻는다.
//			eVO = new EmployeeVO(empno, rs.getString("ename"),
//					rs.getString("job"), rs.getDouble("sal"),
//					rs.getDate("hiredate"));
			ename = rs.getString("ename");
			job = rs.getString("job");
			sal = rs.getDouble("sal");
			hiredate = rs.getDate("hiredate");
			
			eVO = new EmployeeVO(empno, ename, job, sal, hiredate);
		}
		
		}finally{//5. 연결 끊기
			if(rs != null) {rs.close();}
			if(stmt != null) {stmt.close();}//end if
			if(con != null) {con.close();}//end if
		}		
			
		return eVO;
	}
	
}//class
