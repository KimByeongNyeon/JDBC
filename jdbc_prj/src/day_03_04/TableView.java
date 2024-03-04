package day_03_04;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import kr.co.sist.prepared.dao.PreparedStatementDAO;

@SuppressWarnings("serial")
public class TableView extends JFrame {
	private JButton check;
	public TableView() {
		super("테이블 조회");
		setLayout(new BorderLayout());
		check = new JButton("조회");
		
		TableDAO tDAO = TableDAO.getInstance();
		try {
			List<String> listAllTab = tDAO.selectAllTab();
			if(listAllTab.isEmpty()) {
				System.out.println("데이터가 없습니다");
			}else {
				ButtonGroup buttonGroup = new ButtonGroup();
                JPanel panel = new JPanel();

                for (String tname : listAllTab) {
                    JRadioButton radioButton = new JRadioButton(tname);
                    buttonGroup.add(radioButton);
                    panel.add(radioButton);
                }
                add(panel, BorderLayout.CENTER);
			}
			add(check, BorderLayout.SOUTH);
		TableViewEvent te = new TableViewEvent(this);
		check.addActionListener(te);
		}catch (Exception e) {
			e.printStackTrace();
		}
		setSize(300, 200);
		setVisible(true);
		}
	public JButton getCheck() {
		return check;
	}
	public static void main(String[] args) {
		TableView tv = new TableView();
	}
	
}
