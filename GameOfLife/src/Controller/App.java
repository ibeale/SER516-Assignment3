package Controller;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Model.State;
import View.Cell;

public class App extends JFrame {
	private static final int CELLSPEROW = 15;
	private static final int FRAMESIZE = 500;
	private JFrame frame;
	Cell[][] buttons = new Cell[CELLSPEROW][CELLSPEROW];
	private JButton start;
	private JButton pause;
	Controller controller;

	public static void main(String args[]) {
		new App();
	}

	public App() {
		buttons = new Cell[CELLSPEROW][CELLSPEROW];

		frame = new JFrame("Game of Life");
		frame.setSize(FRAMESIZE, FRAMESIZE);
		frame.setLayout(new BorderLayout());

		initializeButtonPanel();
		initializeGrid();

		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		State model = State.getInstance();

		controller = Controller.getInstance(model);
	}

	private void initializeGrid() {
		Container grid = new Container();
		grid.setLayout(new GridLayout(CELLSPEROW, CELLSPEROW));
		for (int i = 0; i < CELLSPEROW; i++) {
			for (int j = 0; j < CELLSPEROW; j++) {
				buttons[i][j] = new Cell();
				grid.add(buttons[i][j]);
			}
		}
		frame.add(grid, BorderLayout.CENTER);
	}


	private void initializeButtonPanel() {
		JPanel buttonPanel = new JPanel();

		start = new JButton("Start");
		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//your actions
				controller.onStart();
			}
		});
		pause = new JButton("Pause");
		pause.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//your actions
				controller.onPause();
			}
		});

		// start.addActionListener(actionListener);
		// pause.addActionListener(actionListener);

		buttonPanel.add(start);

		buttonPanel.add(pause);
		frame.add(buttonPanel, BorderLayout.SOUTH);
	}
}