package Controller;

import Model.State;
import View.Cell;
import Model.State.Conditions;

public class Controller {
    private State model;

    private static Controller instance;

    private Controller(State model) {
        this.model = model;
    }

    public boolean onCellClicked(int x, int y) {
        boolean result = model.toggleSingleCell(x, y);
        return result;
    }

    public void onIncrement(Cell[][] buttons) {
        // iterate over each cell, fetch new state, communicate back to view
        model.incrementState();
        boolean[][] newState = model.getCurrentState();
        for (int x = 0; x < newState.length; x++) {
            for (int y = 0; y < newState[x].length; y++) {
                // System.out.print("Old: " + buttons[x][y].getCellState()+ " setting to "+newState[x][y]);
                buttons[x][y].updateCellState(newState[x][y]);
                // System.out.print("    New: " + buttons[x][y].getCellState());
            }
            System.out.println("");
        }
    }

    public void onStart(Cell[][] buttons) {

    }

    public void onPause() {

    }

    public static Controller getInstance() {
        // if(width < 1 || height < 1){
        // throw new java.lang.Error("Need height and width");
        // }

        if (instance == null) {
            State model = State.getInstance();
            System.out.println("Instantiating model");
            instance = new Controller(model);
        }
        return instance;
    }
}
