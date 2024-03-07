package kr.co.sist.callable.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.dao.DbConnection;
import kr.co.sist.statement.vo.EmployeeVO;
import kr.co.sist.vo.ResultVO;
import oracle.jdbc.internal.OracleTypes;

public class CallableStatementDAO {
	private static CallableStatementDAO cDAO;

	private CallableStatementDAO() {

	}// CallableStatementDAO

	public static CallableStatementDAO getInstance() {
		if (cDAO == null) {
			cDAO = new CallableStatementDAO();
		} // end if
		return cDAO;
	}// getInstance

	public ResultVO insertEmployee(EmployeeVO eVO) throws SQLException {
		ResultVO rVO = null;

		DbConnection dbCon = DbConnection.getInstance();

		Connection con = null;
		CallableStatement cstmt = null;
		// 1.
		try {
			// 2.
			String id = "scott";
			String pass = "tiger";

			con = dbCon.getConnection(id, pass);
			cstmt = con.prepareCall("{call insert_employee(?,?,?,?,?,?)}");
			// 3.
			// 4.바인드 변수에 값 할당
			// in parameter
			cstmt.setInt(1, eVO.getEmpno());
			cstmt.setString(2, eVO.getEname());
			cstmt.setString(3, eVO.getJob());
			cstmt.setDouble(4, eVO.getSal());
			// out parameter
			cstmt.registerOutParameter(5, Types.NUMERIC);
			cstmt.registerOutParameter(6, Types.VARCHAR);
			// 5.
			cstmt.execute();
			rVO = new ResultVO(cstmt.getInt(5), cstmt.getString(6));
		} finally {
			// 6.
			dbCon.dbClose(null, cstmt, con);
		}
		return rVO;
	}// insertEmployee

	public ResultVO updateEmployee(EmployeeVO eVO) throws SQLException {
		ResultVO rVO = null;
		DbConnection dbCon = DbConnection.getInstance();

		Connection con = null;
		CallableStatement cstmt = null;
		// 1.
		try {
			// 2.
			String id = "scott";
			String pass = "tiger";

			con = dbCon.getConnection(id, pass);

			// 3.
			cstmt = con.prepareCall("{call update_employee(?,?,?,?,?)}");
			// 4.바인드 변수에 값 할당
			// in parameter
			cstmt.setInt(1, eVO.getEmpno());
			cstmt.setString(2, eVO.getJob());
			cstmt.setDouble(3, eVO.getSal());
			// out parameter
			cstmt.registerOutParameter(4, Types.NUMERIC);
			cstmt.registerOutParameter(5, Types.VARCHAR);
			// 5.
			cstmt.execute();
			rVO = new ResultVO(cstmt.getInt(4), cstmt.getString(5));
		} finally {
			// 6.
			dbCon.dbClose(null, cstmt, con);
		}

		return rVO;
	}// updateEmployee

	public ResultVO deleteEmployee(EmployeeVO eVO) throws SQLException {
		ResultVO rVO = null;
		DbConnection dbCon = DbConnection.getInstance();

		Connection con = null;
		CallableStatement cstmt = null;
		// 1.
		try {
			// 2.
			String id = "scott";
			String pass = "tiger";

			con = dbCon.getConnection(id, pass);

			// 3.
			cstmt = con.prepareCall("{call delete_employee(?,?,?)}");
			// 4.바인드 변수에 값 할당
			// in parameter
			cstmt.setInt(1, eVO.getEmpno());
			// out parameter
			cstmt.registerOutParameter(2, Types.NUMERIC);
			cstmt.registerOutParameter(3, Types.VARCHAR);
			// 5.
			cstmt.execute();
			rVO = new ResultVO(cstmt.getInt(2), cstmt.getString(3));
		} finally {
			// 6.
			dbCon.dbClose(null, cstmt, con);
		}

		return rVO;
	}// updateEmployee
	
	public List<EmployeeVO> selectAllEmployee()throws SQLException{
		List<EmployeeVO> list = new ArrayList<EmployeeVO>();
		
		DbConnection dbCon = DbConnection.getInstance();
		
		//1.
		Connection con = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		//2.
		try {
			String id = "scott";
			String pass = "tiger";
			con = dbCon.getConnection(id, pass);
		//3.
			cstmt = con.prepareCall("{call select_all_employee(?)}");
		//4.
			cstmt.registerOutParameter(1, Types.REF_CURSOR);
//			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			
		//5.
			cstmt.execute();
			
			
			rs =(ResultSet)cstmt.getObject(1);
			
			while(rs.next()) {
				EmployeeVO eVO = null;
				eVO = new EmployeeVO(rs.getInt("empno"), rs.getString("ename"), rs.getString("job"), 
						rs.getDouble("sal"), rs.getDate("hiredate"));
				list.add(eVO);
			}//end while
		//6.
		}finally{
			dbCon.dbClose(rs, cstmt, con);
		}
		return list;
	}
	
	public EmployeeVO selectOneEmployee(int empno)throws SQLException{
		EmployeeVO eVO = null;
		DbConnection dbCon  = DbConnection.getInstance();
		
		//1.
		Connection con = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		try {
		//2.
			String id = "scott";
			String pass = "tiger";
			con = dbCon.getConnection(id, pass);
			
		//3.
			cstmt = con.prepareCall("{call select_one_employee(?,?,?)}");
		//4.
			cstmt.setInt(1, empno);
			cstmt.registerOutParameter(2, OracleTypes.CURSOR);
			cstmt.registerOutParameter(3, OracleTypes.VARCHAR);
		//5.
			cstmt.execute();
			
			rs =(ResultSet)cstmt.getObject(2);
			if(rs.next()) {
				eVO = new EmployeeVO(empno, rs.getString("ename"), rs.getString("job"), rs.getDouble("sal"), rs.getDate("hireDate"));
			}//end if
			
			System.out.println(cstmt.getString(3));
		//6.
		}finally {
			dbCon.dbClose(rs, cstmt, con);
		}
		return eVO;
		
	}
//	public static void main(String[] args) throws SQLException {
//		System.out.println(CallableStatementDAO.getInstance().selectAllEmployee());
//	}
}// class
