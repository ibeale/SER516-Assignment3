package Model;

import java.util.Arrays;

public class State {
    private static State instance;

    //arbitrary width and heigh, decided by the team.
    private int _width = 15;
    private int _height = 15;
    private boolean[][] _currentState;

    public enum Conditions{
        Overpopulated,
        Solitary,
        Survives,
        Populate,
        NoChange
    }

    public boolean[][] getCurrentState(){
        return this._currentState;
    }
    
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

    public void resetState(){
        instance = new State();
    }

    //Sets the status of a single cell, and returns the new status.
    public boolean toggleSingleCell(int x, int y){
        this._currentState[x][y] = !this._currentState[x][y];
        return(this._currentState[x][y]);
    }

    public boolean getCellState(int x, int y){
        return(this._currentState[x][y]);
    }

    private int getNumAdjacent(int x, int y){
        int count = 0;
        int[] directions = {-1, 0, 1};
        for(int xDir : directions){
            for(int yDir : directions){
                int xCoord = x + xDir;
                int yCoord = y + yDir;
                if(!(xDir == 0 && yDir ==0) && (xCoord >= 0 && xCoord < this._width) && (yCoord >= 0 && yCoord < this._height) && getCellState(xCoord, yCoord)){
                    count += 1;
                }
            }
        }
        return count;

    }

    public Conditions getConditionofCell(int x, int y){
        int numAdjacent = getNumAdjacent(x, y);
        boolean cellState = getCellState(x, y);
        if(cellState && numAdjacent <= 1){
            return Conditions.Solitary;
        }
        else if(cellState && numAdjacent >= 4){
            return Conditions.Overpopulated;
        }
        else if(cellState && (numAdjacent == 2 || numAdjacent == 3)){
            return Conditions.Survives;
        }
        else if(!cellState && numAdjacent == 3){
            return Conditions.Populate;
        }
        else{
            return Conditions.NoChange;
        }
    }

    public void incrementState(){
        boolean[][] newState = new boolean[this._width][this._height];
        for(boolean[] column : newState){
            Arrays.fill(column, false);
        }

        for(int x = 0; x < this._width; x++){
            for(int y = 0; y < this._height; y++){
                Conditions curCondition = this.getConditionofCell(x, y);
                switch(curCondition){
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

