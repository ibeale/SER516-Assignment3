package View;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Cell extends JButton implements ActionListener {
	ImageIcon yellowBG;
	byte value = 0;
	boolean isAlive;
	int row;
	int col;

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

	public void actionPerformed(ActionEvent e) {
		value++;
		value %= 2;
		switch (value) {
		case 0:
			setIcon(null);
			isAlive = false;
			break;
		case 1:
			setIcon(yellowBG);
			isAlive = true;
			break;
		}
	}
}