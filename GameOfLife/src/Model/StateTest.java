package Model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class StateTest {

    private boolean isBoardEmpty(State thisState){
        for(boolean[] col : thisState.getCurrentState()){
            for(boolean cell : col){
                if(cell == true){
                    return false;
                }
            }
        }
        return true;
    }
    
    @Test
    public void testToggleSingleCellFalsetoTrue() {
        State testState = State.getInstance();
        testState.resetState();
        testState.toggleSingleCell(0,1);
        assertEquals(true, testState.getCellState(0,1));
    }
    
    @Test
    public void testToggleSingleCellTruetoFalse(){
        State testState = State.getInstance();
        testState.resetState();
        testState.toggleSingleCell(2,5);
        assertEquals(true, testState.getCellState(2,5));
        testState.toggleSingleCell(2,5);
        assertEquals(false, testState.getCellState(2,5));
    }

    @Test
    public void testSolitary(){
        State testState = State.getInstance();
        testState.resetState();
        testState.toggleSingleCell(1, 1);
        testState.toggleSingleCell(0,1);
        assertEquals(State.Conditions.Solitary, testState.getConditionofCell(0, 1));
    }

    @Test
    public void testOverPopulated(){
        State testState = State.getInstance();
        testState.resetState(); 
        testState.toggleSingleCell(0, 0);
        testState.toggleSingleCell(0, 1);
        testState.toggleSingleCell(1, 0);
        testState.toggleSingleCell(1,1);
        testState.toggleSingleCell(0, 2);
        assertEquals(State.Conditions.Overpopulated, testState.getConditionofCell(0, 1));
    }

    @Test
    public void testSurvives(){
        State testState = State.getInstance();
        testState.resetState(); 
        testState.toggleSingleCell(0, 0);
        testState.toggleSingleCell(0, 1);
        testState.toggleSingleCell(1, 0);
        testState.toggleSingleCell(1,1);
        testState.toggleSingleCell(0, 2);
        assertEquals( State.Conditions.Survives, testState.getConditionofCell(0, 0));
        assertEquals( State.Conditions.Survives, testState.getConditionofCell(0, 2));
    }

    @Test
    public void testPopulated(){
        State testState = State.getInstance();
        testState.resetState(); 
        testState.toggleSingleCell(0, 0);
        testState.toggleSingleCell(0, 1);
        testState.toggleSingleCell(1, 0);
        testState.toggleSingleCell(1,1);
        testState.toggleSingleCell(0, 2);
        assertEquals(State.Conditions.Populate, testState.getConditionofCell(1, 2));
    }

    @Test
    public void testNoChange(){
        State testState = State.getInstance();
        testState.resetState();
        assertEquals(State.Conditions.NoChange, testState.getConditionofCell(0, 0));
    }

    @Test
    public void testIncrementState(){
        State testState = State.getInstance();
        testState.resetState(); 
        testState.toggleSingleCell(0, 0);
        testState.toggleSingleCell(0, 1);
        testState.toggleSingleCell(1, 0);
        testState.toggleSingleCell(1,1);
        testState.toggleSingleCell(0, 2);
        testState.incrementState();
        assertEquals(true, testState.getCellState(0, 0)); //survives
        assertEquals(true, testState.getCellState(1, 0)); //survives
        assertEquals(true, testState.getCellState(0, 2)); //survives
        assertEquals(false, testState.getCellState(0, 1)); //overpopulated
        assertEquals(false, testState.getCellState(1, 1)); //overpopulated
        assertEquals(true, testState.getCellState(1, 2)); //newly populated
        testState.incrementState();
        assertEquals(true, isBoardEmpty(testState)); // everything is solitary and should die
    }
}
