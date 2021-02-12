package Model;

import java.util.Arrays;

public class State {
    private static State instance;

    //arbitrary width and heigh, decided by the team.
    private int _width = 15;
    private int _height = 15;
    private boolean[][] _currentState;
    
    private State(){
        this._currentState = new boolean[this._width][this._height];
        for(boolean[] column : this._currentState){
            Arrays.fill(column, false);
        }
    }

    public static State getInstance(){
        if(instance == null){
            instance = new State();
        }
        return instance;
    }

    //Sets the status of a single cell, and returns the new status.
    public boolean toggleSingleCell(int x, int y){
        this._currentState[x][y] = !this._currentState[x][y];
        return(this._currentState[x][y]);
    }
}
