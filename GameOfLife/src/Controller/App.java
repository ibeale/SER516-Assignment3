package Controller;

import Model.State;
import View.Cell;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import View.Cell;

public class App extends JFrame {
	private JFrame frame;
	Cell[][] buttons = new Cell[25][25];
	private JButton start;
	private JButton pause;

	public static void main(String args[]) {
		new App();
	}

	public App() {
		buttons = new Cell[500][500];

		frame = new JFrame("Minesweeper");
		frame.setSize(500, 500);
		frame.setLayout(new BorderLayout());

		initializeButtonPanel();
		initializeGrid();

		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		State model = new State();
	}

	private void initializeGrid() {
		Container grid = new Container();
		grid.setLayout(new GridLayout(25, 25));
		for (int i = 0; i < 25; i++) {
			for (int j = 0; j < 25; j++) {
				buttons[i][j] = new Cell();
				grid.add(buttons[i][j]);
			}
		}
		frame.add(grid, BorderLayout.CENTER);
	}

	private void initializeButtonPanel() {
		JPanel buttonPanel = new JPanel();

		start = new JButton("Start");
		pause = new JButton("Pause");

		// start.addActionListener(actionListener);
		// pause.addActionListener(actionListener);

		buttonPanel.add(start);
		buttonPanel.add(pause);
		frame.add(buttonPanel, BorderLayout.SOUTH);
	}
}