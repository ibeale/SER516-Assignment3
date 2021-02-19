package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The Controller class to redirect controls based on taken actions - Start,
 * Pause, Increment or Click a cell. This class is closely bound to the View
 */
public class GameControlListener implements ActionListener {

	CellStateController controller;

	public GameControlListener() {
		controller = CellStateController.getInstance();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		switch (action) {
		case "Start":
			controller.onStart();
			break;
		case "Increment":
			controller.onIncrement();
			break;
		case "Pause":
			controller.onPause();
			break;
		case "Reset":
			controller.onReset();
			break;
		default:
			break;
		}
	}

}
