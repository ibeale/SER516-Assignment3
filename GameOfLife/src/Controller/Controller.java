package Controller;

import Model.State;
import View.Cell;

public class Controller {
    private State model;
    private Cell view;
    private static Controller instance;

    private Controller(State model){
        this.model = model;
    }

    public void onStart(){

    }

    public void onPause(){

    }

    public static Controller getInstance(State model){
        if(instance == null){
            instance = new Controller(model);
        }
        return instance;
    }
}
