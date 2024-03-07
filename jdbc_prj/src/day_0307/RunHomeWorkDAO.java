package day_0307;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class RunHomeWorkDAO {
	public void searchCarList() {
		HomeworkDAO hDAO = HomeworkDAO.getInstance();
		
		String inputMaker = JOptionPane.showInputDialog("제조사 입력");
		if(inputMaker != null && !inputMaker.isEmpty()) {
			try {
				List<HomeworkVO> list = hDAO.selectCar(inputMaker);
				
				StringBuilder output = new StringBuilder();
				output.append(inputMaker).append("입력 결과 \n");
				output.append("제조국\t제조사\t모델명\t년식\t가격\t옵션\n");
				
				if(list.isEmpty()) {
					output.append("검색된 데이터가 존재하지 않습니다.");
				}//end if
				
				for(HomeworkVO hVO : list) {
					output
					.append(hVO.getCountry()).append("\t")
					.append(hVO.getMaker()).append("\t")
					.append(hVO.getModel()).append("\t")
					.append(hVO.getCarYear()).append("\t")
					.append(hVO.getPrice()).append("\t")
					.append(hVO.getCarOption()).append("\n");
				}//end for
				
				JTextArea jta = new JTextArea(output.toString(), 10, 80);
				JScrollPane jsp = new JScrollPane(jta);
				JOptionPane.showMessageDialog(null, jsp);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		RunHomeWorkDAO rDAO = new RunHomeWorkDAO();
		rDAO.searchCarList();
	}
}
