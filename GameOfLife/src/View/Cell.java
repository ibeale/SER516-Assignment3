package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import Controller.CellStateController;

/**
 * The Cell class binds the state of a cell(alive or not), its position on grid
 * and background color.
 */
public class Cell extends JButton implements ActionListener {
	ImageIcon yellowBG;
	boolean isAlive;
	int row;
	int col;
	CellStateController controller = CellStateController.getInstance();

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

	public boolean getCellState() {
		return isAlive;
	}

	public void updateCellState(boolean newState) {
		isAlive = newState;
		if (isAlive) {
			setIcon(yellowBG);
		} else {
			setIcon(null);
		}
	}

	public void actionPerformed(ActionEvent e) {
		boolean newState = controller.onCellClicked(row, col);
		updateCellState(newState);
	}
}