package kr.co.sist.statement.service;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import kr.co.sist.statement.dao.StatementDAO;
import kr.co.sist.statement.vo.EmployeeVO;

import static java.lang.Integer.parseInt;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;

/**
 * CRUD를 사용하는 클래스
 */
public class RunStatementDAO {

	public void addEmp() {
		String inputData = JOptionPane.showInputDialog("추가할 사원정보를 입력해주세요.\n사원번호, 사원명, 직무, 연봉");
		if (inputData != null) {
			String[] tempData = inputData.split(",");
			if (tempData.length != 4) {
				JOptionPane.showMessageDialog(null, "입력 형식을 확인해주세요");
				return;
			} // end if

			// 입력된 데이터 하나로 묶어 관리
			try {
				EmployeeVO eVO = new EmployeeVO(parseInt(tempData[0]), tempData[1], tempData[2],
						parseDouble(tempData[3]), null);
				// DB에 추가하면 된다.
				StatementDAO sDAO = new StatementDAO();
				try {
					sDAO.insertEmp(eVO);
					JOptionPane.showMessageDialog(null, tempData[0] + "번 사원정보가 추가 되었습니다.");
				} catch (SQLException se) {
					se.printStackTrace();
					String errMsg = "";
					switch (se.getErrorCode()) {
					case 1:
						errMsg = tempData[0] + "번 사원번호가 중복되었습니다.";
						break;
					case 1438:
						errMsg = "연봉은 정수 5자리 실수 2자리로 입력가능합니다.";
						break;
					case 12899:
						errMsg = "사원명과 직무는 한글 3자까지만 가능합니다.";
						break;
					default:
						errMsg = "문제가 발생했습니다.";
					}// end switch
					JOptionPane.showMessageDialog(null, errMsg);
				} // end catch

			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, "사원번호나 연봉은 숫자형태이어야 한다.");
			} // end catch

		} // end if
	}// addEmp

	public void modifyEmp() {
		//데이터 입력하기 위한 창을 받음
		String inputData = JOptionPane.showInputDialog
				("사원정보 변경\n사원번호, 직무, 연봉을 입력하면 사원번호에 해당하는 사원의 직무와 연봉을 변경합니다.");
		if(inputData != null) {
			String[] tempData = inputData.split(",");
			//tempdata에 입력된 값이 사원번호, 직무, 연봉보다 많거나 적으면 메시지를 띄우고 return
			if(tempData.length != 3) {
				JOptionPane.showMessageDialog(null, "입력은 사원번호, 직무, 연봉의 형식입니다.");
				return;
			}
			try {
				//사원번호와 연봉의 형식이 숫자인경우, tempdata[0]과 tempdata[2]를 변환함
				EmployeeVO eVO = new EmployeeVO(parseInt(tempData[0]), null,
						tempData[1], parseDouble(tempData[2]), null);
				
				//DBMS테이블의 update 수행 :
				StatementDAO sDAO = new StatementDAO();
				int cnt = sDAO.updateEmp(eVO); //0~n건 변경
				
				String msg = tempData[0]+"번 사원은 존재하지 않습니다. 사원번호를 확인해주세요.";
				if(cnt != 0) {//1건 이상 변경되었을 때
					msg = tempData[0] + "번 으로 "+cnt+"건 변경 되었습니다. ";
				}//end if
				JOptionPane.showMessageDialog(null, msg);
				
			} catch (NumberFormatException nfe) {
				//catch를 통한 예외처리
				JOptionPane.showMessageDialog(null, "사원번호와 연봉은 숫자형태 입니다.");
			} catch (SQLException se) {
				se.printStackTrace();
				
				String errMsg = "";
				switch (se.getErrorCode()) {
				case 1438://오류 번호
					errMsg = "연봉은 정수 5자리 실수 2자리로 입력가능합니다.";
					break;
				case 12899://오류 번호
					errMsg = "직무는 한글 3자까지만 가능합니다.";
					break;
				default:
					errMsg = "문제가 발생했습니다.";
				}// end switch
				JOptionPane.showMessageDialog(null, errMsg);
			}//end catch
			
		}//end if

	}// modifyEmp

	public void removeEmp() {
		String inputData = JOptionPane.showInputDialog
				("사원정보 삭제\n사원번호를 입력하면 사원번호에 해당하는 사원의 직무와 연봉을 삭제합니다.");
		
		if(inputData != null) {
			String number = "[0-9]+";
			if(!(inputData.matches(number))) {
				JOptionPane.showMessageDialog(null, "사원번호는 숫자로만 입력해주세요.");
			}
			String msg = "";
			int empno = parseInt(inputData);
			//tempdata에 입력된 값이 사원번호, 직무, 연봉보다 많거나 적으면 메시지를 띄우고 return
			if(inputData.length() > 4) {
				JOptionPane.showMessageDialog(null, "사원번호는 4자리입니다.");
				return;
			}
			try {
				StatementDAO sDAO = new StatementDAO();
				int cnt = sDAO.deleteEmp(empno);
				if(cnt != 0) {
					msg = cnt +"건 삭제 되었습니다.";
				}
				JOptionPane.showMessageDialog(null, msg);
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, "사원번호와 연봉은 숫자형태 입니다.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
				
		}//end if

	}// removeEmp

	public void searchALLEmp() {
		StatementDAO sDAO = new StatementDAO();
		try {
			List<EmployeeVO> listAllEmp = sDAO.selectALLEmp();
			System.out.println(listAllEmp);
			StringBuilder output = new StringBuilder();
			output.append("사원번호\t사원명\t직무\t연봉\t입사일\n");
			if(listAllEmp.isEmpty()) {
				output.append("데이터가 없습니다.");
			}else {
				SimpleDateFormat  sdf = new SimpleDateFormat("MM-dd-yyyy");
				for(EmployeeVO eVO : listAllEmp) {
					output.append(eVO.getEmpno()).append("\t")
					.append(eVO.getEname()).append("\t")
					.append(eVO.getJob()).append("\t")
					.append(eVO.getSal()).append("\t")
					.append(sdf.format(eVO.getHiredate())).append("\n");
				}//end for
			}//end else
			JTextArea jta = new JTextArea(output.toString(), 10, 80);
			JScrollPane jsp = new JScrollPane(jta);
			JOptionPane.showMessageDialog(null, jsp);
		} catch (Exception e) {
		}
	}// searchALLEmp

	public void searchOneEmp() {
		String inputData = JOptionPane.showInputDialog("사원검색\n사원번호를 입력해 주세요");
		if(inputData == null) {
			JOptionPane.showMessageDialog(null, "사원번호를 입력해주세요");
			return;
		}//end if
		
		try {
			int empno = parseInt(inputData);
			//DBMS에서 조회된 결과를 받아서 사용자에게 보여준다.
			StatementDAO sDAO = new StatementDAO();
			EmployeeVO eVO = sDAO.selectOneEmp(empno);
			System.out.println(eVO);
			StringBuilder output = new StringBuilder();
			output.append(empno).append("번 사원번호 검색 결과 \n");
			if(eVO == null) {
				output.append("사원번호를 확인하세요.😊😊😊😊😊😊");
			}else {
				output.append("사원명 : ").append( eVO.getEname()).append("\n");
				output.append("직무 : ").append( eVO.getJob()).append("\n");
				output.append("연봉 : ").append( eVO.getSal()).append("\n");
				SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
				output.append("입사일 : ").append( sdf.format(eVO.getHiredate()));
			}//end if
			
			JTextArea jta = new JTextArea(output.toString(), 10, 80);
			JScrollPane jsp = new JScrollPane(jta);
			JOptionPane.showMessageDialog(null, jsp);
			
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "사원번호는 숫자형태 이어야 합니다.");
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}// searchOneEmp

	public void menu() {
		boolean exitFlag = false;

		String inputMenu = "";
		do {
			inputMenu = JOptionPane
					.showInputDialog("메뉴 선택\n1. 사원정보 추가 2. 사원정보 변경 3. 사원정보 삭제 4. 전체 사원 검색 5. 특정 사원 검색 6. 종료");

			if (inputMenu != null) {
				switch (inputMenu) {
				case "1":
					addEmp();
					break;
				case "2":
					modifyEmp();
					break;
				case "3":
					removeEmp();
					break;
				case "4":
					searchALLEmp();
					break;
				case "5":
					searchOneEmp();
					break;
				case "6":
					exitFlag = true;
					break;
				default:
					JOptionPane.showMessageDialog(null, "메뉴는 1, 2, 3, 4, 5, 6 중 하나만 입력해주세요");

				}// end switch
			} // end if
		} while (!exitFlag);
	}// menu

	public static void main(String[] args) {
		RunStatementDAO rsDAO = new RunStatementDAO();
		rsDAO.menu();
	}// main

}// class
