package Model;

import java.util.Arrays;

import View.Cell;

/**
 * The Singleton model to store, get, update and calculate all Cell states based
 * on current state.
 */
public class State {
	private static State instance;

	private static int _width = 5;
	private static int _height = 5;
	private boolean isIncremented = false;
	private Cell[][] buttons = new Cell[_height][_width];
	private boolean[][] _currentState;
	private boolean[][] _originalState;

	public enum Conditions {
		Overpopulated, Solitary, Survives, Populate, NoChange
	}

	public boolean[][] getCurrentState() {
		return this._currentState;
	}

	public void setCellButtons(Cell[][] buttons) {
		this.buttons = buttons;
	}

	public Cell[][] getCellButtons() {
		return this.buttons;
	}

	private State() {
		this._currentState = new boolean[_width][_height];
		for (boolean[] column : this._currentState) {
			Arrays.fill(column, false);
		}
	}

	public static int getWidth() {
		return _width;
	}

	public static int getHeight() {
		return _height;
	}

	public static State getInstance() {
		if (instance == null) {
			instance = new State();
		}
		return instance;
	}

	public void resetState() {
		if (isIncremented == false) {
			instance = new State();
		} else {
			this._currentState = this._originalState;
		}
	}

	public boolean toggleSingleCell(int x, int y) {
		this._currentState[x][y] = !this._currentState[x][y];
		return (this._currentState[x][y]);
	}

	public boolean getCellState(int x, int y) {
		return (this._currentState[x][y]);
	}

	/**
	 * function to calculate the number of adjacent cells for a given cell
	 */
	private int getNumAdjacent(int x, int y) {
		int count = 0;
		int[] directions = { -1, 0, 1 };
		for (int xDir : directions) {
			for (int yDir : directions) {
				int xCoord = x + xDir;
				int yCoord = y + yDir;
				if (!(xDir == 0 && yDir == 0) && (xCoord >= 0 && xCoord < _width) && (yCoord >= 0 && yCoord < _height)
						&& getCellState(xCoord, yCoord)) {
					count += 1;
				}
			}
		}
		return count;

	}

	/**
	 * The function checks the current neighbors of a cell and names it to be one of
	 * these - Solitary if adjacent cells are not more than 1 and cell is alive
	 * Overpopulated if adjacent cells are atleast 4 and cell is alive Survives if
	 * adjacent cells are either 2 or 3 and cell is alive Populate if adjacent cells
	 * are 3 and cell is not alive
	 */
	public Conditions getConditionofCell(int x, int y) {
		int numAdjacent = getNumAdjacent(x, y);
		boolean cellState = getCellState(x, y);
		if (cellState && numAdjacent <= 1) {
			return Conditions.Solitary;
		} else if (cellState && numAdjacent >= 4) {
			return Conditions.Overpopulated;
		} else if (cellState && (numAdjacent == 2 || numAdjacent == 3)) {
			return Conditions.Survives;
		} else if (!cellState && numAdjacent == 3) {
			return Conditions.Populate;
		} else {
			return Conditions.NoChange;
		}
	}

	/**
	 * The function calculates one next state at a time.
	 */
	public void incrementState() {
		// Store the original state
		if (isIncremented == false) {
			this._originalState = this._currentState;
			isIncremented = true;
		}

		boolean[][] newState = new boolean[_width][_height];
		for (boolean[] column : newState) {
			Arrays.fill(column, false);
		}

		for (int x = 0; x < _width; x++) {
			for (int y = 0; y < _height; y++) {
				Conditions curCondition = this.getConditionofCell(x, y);
				switch (curCondition) {
				case NoChange:
					newState[x][y] = this._currentState[x][y];
					break;
				case Overpopulated:
					newState[x][y] = false;
					break;
				case Populate:
					newState[x][y] = true;
					break;
				case Solitary:
					newState[x][y] = false;
					break;
				case Survives:
					newState[x][y] = true;
					break;
				default:
					break;
				}
			}
		}
		this._currentState = newState;
	}
}
