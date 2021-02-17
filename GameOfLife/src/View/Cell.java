package View;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Controller.*;

public class Cell extends JButton implements ActionListener {
	ImageIcon yellowBG;
	// byte value = 0;
	boolean isAlive;
	int row;
	int col;
	Controller controller = Controller.getInstance();

	public Cell() {
		yellowBG = new ImageIcon(this.getClass().getResource("yellowBG.jpg"));
		this.isAlive = false;
		this.addActionListener(this);
	}

	public Cell(int row, int col) {
		yellowBG = new ImageIcon(this.getClass().getResource("yellowBG.jpg"));
		this.isAlive = false;
		this.row = row;
		this.col = col;
		this.addActionListener(this);
	}

	public boolean getCellState(){
		return isAlive;
	}

	public void updateCellState(boolean newState){
		isAlive = newState;
		if(isAlive){
			setIcon(yellowBG);
		}else{
			setIcon(null);
		}
	}

	public void actionPerformed(ActionEvent e) {
		isAlive = controller.onCellClicked(col, row);
		updateCellState(isAlive);
		// value++;
		// value %= 2;
		// switch (value) {
		// case 0:
		// 	setIcon(null);
		// 	isAlive = false;
		// 	break;
		// case 1:
		// 	setIcon(yellowBG);
		// 	isAlive = true;
		// 	break;
		// }
	}
}