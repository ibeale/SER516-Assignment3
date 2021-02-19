package View;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.GameControlListener;
import Model.State;

/**
 * The Game class initializes Game screen along with a grid of buttons in the
 * play area and control buttons like start, pause etc.,
 */
public class Game extends JFrame {

	private static final int FRAMESIZE = 500;
	private JFrame frame;
	JButton start;
	JButton pause;

	public Game() {

		frame = new JFrame("Game of Life");
		frame.setSize(FRAMESIZE, FRAMESIZE);
		frame.setLayout(new BorderLayout());

		initializeButtonPanel();
		initializeGrid();

		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

	private void initializeGrid() {
		Container grid = new Container();
		int CELLSPEROW = Model.State.getWidth();
		int ROWS = Model.State.getHeight();
		grid.setLayout(new GridLayout(ROWS, CELLSPEROW));
		Cell[][] buttons = State.getInstance().getCellButtons();
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < CELLSPEROW; j++) {
				buttons[i][j] = new Cell(i, j);
				grid.add(buttons[i][j]);
			}
		}
		State.getInstance().setCellButtons(buttons);
		frame.add(grid, BorderLayout.CENTER);
	}

	private void initializeButtonPanel() {

		JButton increment;
		JButton reset;

		JPanel buttonPanel = new JPanel();
		GameControlListener gameControlListener = new GameControlListener();

		start = new JButton("Start");
		start.addActionListener(gameControlListener);

		increment = new JButton("Increment");
		increment.addActionListener(gameControlListener);

		pause = new JButton("Pause");
		pause.addActionListener(gameControlListener);

		reset = new JButton("Reset");
		reset.addActionListener(gameControlListener);
		
		gameControlListener.setButtonReferences(start, pause);

		buttonPanel.add(start);
		buttonPanel.add(increment);
		buttonPanel.add(pause);
		buttonPanel.add(reset);

		frame.add(buttonPanel, BorderLayout.SOUTH);
	}

}