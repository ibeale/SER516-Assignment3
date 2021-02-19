package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * The Controller class to redirect controls based on taken actions - Start,
 * Pause, Increment or Click a cell. This class is closely bound to the View
 */
public class GameControlListener implements ActionListener {

	CellStateController controller;
	JButton startBtn; JButton pauseBtn;

	public GameControlListener() {
		controller = CellStateController.getInstance();
	}
	
	public void setButtonReferences(JButton start, JButton pause) {
		this.startBtn=start;
		this.pauseBtn=pause;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		switch (action) {
		case "Start":
			controller.onStart();
			startBtn.setEnabled(false);
			pauseBtn.setEnabled(true);
			break;
		case "Increment":
			controller.onIncrement();
			break;
		case "Pause":
			controller.onPause();
			startBtn.setEnabled(true);
			pauseBtn.setEnabled(false);
			break;
		case "Reset":
			controller.onReset();
			startBtn.setEnabled(true);
			pauseBtn.setEnabled(true);
			break;
		default:
			break;
		}
	}

}
