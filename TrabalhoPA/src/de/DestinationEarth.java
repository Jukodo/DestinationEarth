/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de;

import de.logic.data.DataGame;
import de.logic.states.*;
import java.util.List;

/**
 *
 * @author Tiago
 */
public class DestinationEarth {
    private DataGame dataGame;
    private IStates state;
    
    public DestinationEarth(){
        dataGame = new DataGame();
        state = new Beginning(dataGame);
    }

    /**Getters and Setters**/
    public DataGame getDataGame() {
        return dataGame;
    }

    public void setDataGame(DataGame dataGame) {
        this.dataGame = dataGame;
    }

    public IStates getState() {
        return state;
    }

    public void setState(IStates state) {
        this.state = state;
    }
    
    /**Data game methods**/
    
    public List<String> getLogs(){
        return dataGame.getLogs();
    }
    
    @Override
    public String toString()
    {   
        return dataGame.toString();
    }
    
    /**State machine methods**/
    
    //Beginning
    public void start(){
        setState(getState().start());
    }
    
    //
    public void setPlayerName(String name){
        setState(getState().setPlayerName(name));
    }
    
    //CrewSelection
    public void selectCrewMember(int crewNumber, int crewType){
        setState(getState().selectCrewMember(crewNumber, crewType));
    }
    
    public void selectCrewMemberColor(int crewNumber, int crewMemberColor){
        setState(getState().selectCrewMemberColor(crewNumber, crewMemberColor));
    }
    
    public void initializeCrewMembers(){
        setState(getState().initializeCrewMembers());
    }
    
    //CrewPlacement
    public void placeCrewMember(int crewNumber, int roomNumber){
        setState(getState().placeCrewMember(crewNumber, roomNumber));
    }
    
    //JourneyPhase
    public void nextTurn(){
        setState(getState().nextTurn());
    }
    
    //ScanningPhase
    public void scanTurn(){
        setState(getState().scanTurn());
    }
    
    //RestPhase
    public void spendInspirationPoints(){
        setState(getState().spendInspirationPoints());
    }
    
    //CrewPhase
    public void spendAbilityPoints(){
        setState(getState().spendAbilityPoints());
    }
    
    //AlienPhase
    public void moveAllAliens(){
        setState(getState().moveAllAliens());
    }
    
    //General
    public void swapCrewMember(){
        setState(getState().swapCrewMember());
    }
    
    public void rollDice(IStates currentState, int quantityOfDice){
        setState(getState().rollDice(currentState, quantityOfDice));
    }
    
    public void setRollValue(IStates currentState, int dieToRoll, int value){
        setState(getState().setDieRoll(currentState, dieToRoll, value));
    }
    
    public void saveGame(){
        setState(getState().saveGame());
    }
    
    public void loadGame(){
        setState(getState().loadGame());
    }
    
    public void quit(){
        setState(getState().quit());
    }
}
