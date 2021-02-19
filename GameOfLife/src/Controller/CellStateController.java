package Controller;

import Model.State;
import View.Cell;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The Controller class to update cell states based on taken actions - Start,
 * Pause, Increment or Click a cell
 */
public class CellStateController {
	private State model;
	private Timer timer;

	private static CellStateController instance;

	private CellStateController(State model) {
		this.model = model;
	}

	public boolean onCellClicked(int x, int y) {
		boolean result = model.toggleSingleCell(x, y);
		return result;
	}

	/**
	 * This function iterates through cells, calculates the new state and update the
	 * view
	 */
	public void onIncrement() {
		model.incrementState();
		updateCell();
	}

	/**
	 * This function runs the onIncrement method on a timer with a regular interval
	 * of 1000ms
	 */
	public void onStart() {
		// timer
		// ----(999ms)----(1ms) --------1
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				CellStateController.getInstance().onIncrement();
			}
		};
		timer = new Timer();
		timer.schedule(timerTask, 0, 500);
	}

	/**
	 * This function halts the onIncrement method timer.
	 */
	public void onPause() {
		timer.cancel();
	}

	public static CellStateController getInstance() {
		if (instance == null) {
			State model = State.getInstance();
			instance = new CellStateController(model);
		}
		return instance;
	}

	/**
	 * This function resets the state of cells to initial state before starting the
	 * game.
	 */
	public void onReset() {
		model.resetState();
		timer.cancel();
		updateCell();
	}

	public void updateCell() {
		Cell[][] buttons = model.getCellButtons();
		boolean[][] newState = model.getCurrentState();

		for (int x = 0; x < newState.length; x++) {
			for (int y = 0; y < newState[x].length; y++) {
				buttons[x][y].updateCellState(newState[x][y]);
			}
		}
		model.setCellButtons(buttons);
	}
}
