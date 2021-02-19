package Controller;

import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;

import Model.State;
import View.Cell;

public class Controller {
    private State model;
    Timer timer;
    
    private static Cell[][] buttons;
    private static Controller instance;

    private Controller(State model) {
        this.model = model;
    }

    public boolean onCellClicked(int x, int y) {
        boolean result = model.toggleSingleCell(x, y);
        return result;
    }

    // iterate over each cell, fetch new state, communicate back to view
    public void onIncrement() {
        model.incrementState();
        boolean[][] newState = model.getCurrentState();

        for (int x = 0; x < newState.length; x++) {
            for (int y = 0; y < newState[x].length; y++) {
                // System.out.print("Old: " + buttons[x][y].getCellState()+ " setting to
                // "+newState[x][y]);
                buttons[x][y].updateCellState(newState[x][y]);
                // System.out.print(" New: " + buttons[x][y].getCellState());
            }
            System.out.println("");
        }
    }

    public void onStart(JButton start, JButton pause) {
        // timer
        // ----(999ms)----(1ms) --------1
        start.setEnabled(false);
        pause.setEnabled(true);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Controller.getInstance().onIncrement();
            }
        };
        timer = new Timer();
        timer.schedule(timerTask, 0, 500);

    }

    public void onPause(JButton start, JButton pause) {
        start.setEnabled(true);
        pause.setEnabled(false);
        timer.cancel();
    }

    // public void onResume(){
    //     TimerTask timerTask = new TimerTask() {
    //         @Override
    //         public void run() {
    //             Controller.getInstance().onIncrement();
    //         }
    //     };
    //     timer = new Timer();
    //     timer.schedule(timerTask, 0, 500);
    // }

    public static void setButtons(Cell[][] buttons){
        Controller.buttons = buttons;
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
