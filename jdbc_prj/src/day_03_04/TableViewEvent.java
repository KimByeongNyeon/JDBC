package day_03_04;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TableViewEvent extends WindowAdapter implements ActionListener{
	private TableView tv;
	public TableViewEvent(TableView tv) {
		this.tv = tv;
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		tv.dispose();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == tv.getCheck()) {
			check();
		}
	}
	
	public void check() {
		TableDAO tDAO = TableDAO.getInstance();
		try {
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
