package info.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import info.VO.InfoVO;
import info.dao.InfoDAO;
import info.design.InfoDesgin;

public class InfoDesignEvent extends WindowAdapter implements ActionListener, MouseListener {
	private JTextField jtfNum, jtfName, jtfAge, jtfInputDate;
	private JComboBox<String>jcbImg;
	InfoDesgin id;
	int count = 0;
	private JList<String> jList;
	private DefaultListModel<String> listModel;

	public InfoDesignEvent(InfoDesgin id) {
		this.id = id;
		jtfNum = id.getJtfNum();
		jtfName = id.getJtfName();
		jtfAge = id.getJtfAge();
		jcbImg = id.getJcbImg();
		jtfInputDate = id.getJtfInputDate();
		jList = id.getInfoList();
		listModel = id.getListModel();
		
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == id.getJbtnAdd()) {
			add();
		}
		if (ae.getSource() == id.getJbtnChange()) {
			try {
				update();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (ae.getSource() == id.getJbtnQuit()) {
			quit();
		}
		
		if (ae.getSource() == id.getJbtnDelete()) {
			try {
				delete();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void add() {
		count++;
		id.setJtfNum(count);
		
		Date sysDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = dateFormat.format(sysDate);


		id.setJtfInputDate(formattedDate);
		//리스트에 저장할 변수 선언
		String tempData = id.getJtfNum().getText() + "," + id.getJtfName().getText() + ","
				+id.getJcbImg().getSelectedItem().toString()+"," + Integer.parseInt(id.getJtfAge().getText())+","+ formattedDate;
		
		InfoVO info = new InfoVO();
		//쿼리문에 필요한 값을 받기 위해 info 객체 만들고 값 설정
		//1.번호 출력
		info.setNum(Integer.parseInt(id.getJtfNum().getText()));
		//2.이름 출력
		info.setName(id.getJtfName().getText());
		//3.이미지 출력
		info.setImg(id.getJcbImg().getSelectedItem().toString());
		//4.나이 출력
		info.setAge(Integer.parseInt(id.getJtfAge().getText()));
		//5. 날짜 출력
		info.setInputdate(formattedDate);
		
		//JList를 사용하기 위한 DLM 생성 id.getListModel을 통해, id의 ListModel의 값을 가져옴
		DefaultListModel<String> listModel = id.getListModel();
		   
		//화면에 출력하기 위해, tempData를 List에 넣고, id.setListModel을 통해 (listModel)의 값을 출력 
		listModel.addElement(tempData);
		id.setListModel(listModel);
		
		
		InfoDAO iDAO = InfoDAO.getInstance();
		try {
			iDAO.insertInfo(info);
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
	public void quit() {
		id.dispose();
	}
	public void setTextField() {
		String [] tempData = id.getInfoList().getSelectedValue().split(",");
		String imgIndex = tempData[2].substring(3, 4);
		
		jtfNum.setText(tempData[0]);
		jtfName.setText(tempData[1]);
		jcbImg.setSelectedIndex(Integer.parseInt(imgIndex)-1);
		jtfAge.setText(tempData[3]);
		jtfInputDate.setText(tempData[4]);
	}
	
	public void update() throws SQLException {
		int num = Integer.parseInt(id.getJtfNum().getText());
		String name = jtfName.getText();
		String img = jcbImg.getSelectedItem().toString();
		int age = Integer.parseInt(jtfAge.getText());
		String date = jtfInputDate.getText();
				
		String temp = num + "," + name + "," + img + "," + age + "," + date;
		
		InfoVO iVO = new InfoVO(num, age, name, img, null);
		
		InfoDAO iDAO = InfoDAO.getInstance();
		int cnt = iDAO.updateInfo(iVO);
		
		//JList에 수정사항 적용
		listModel.setElementAt(temp, num-1);
		JOptionPane.showMessageDialog(null, cnt +  "건 변경되었습니다.");
	}
	
	public void delete() throws SQLException{
		int num = Integer.parseInt(id.getJtfNum().getText());
		InfoDAO iDAO = InfoDAO.getInstance();
		int cnt = iDAO.deleteInfo(num);
		
		listModel.removeElementAt(num-1);
		JOptionPane.showMessageDialog(null, cnt +"건 변경되었습니다.");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==jList) {
			setTextField();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
}
