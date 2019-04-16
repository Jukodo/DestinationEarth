package de;

import de.logic.data.DataGame;
import de.logic.states.*;
import java.util.List;

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
    
    public String diceToString(){
        return dataGame.diceToString();
    }
    
    public String getAvailableActions(){
        return dataGame.getAvailableActions();
    }
    
    @Override
    public String toString()
    {   
        return dataGame.toString();
    }
    
    /**State machine methods**/
    
    //Beginning
    public void start(String playerName){
        setState(getState().start(playerName));
    }
     
    //CrewSelection
    public void selectCrewMember(int crewNumber, int crewType){
        setState(getState().selectCrewMember(crewNumber, crewType));
    }
    
    public void selectCrewMemberColor(int crewNumber, int crewMemberColor){
        setState(getState().selectCrewMemberColor(crewNumber, crewMemberColor));
    }
    
    public void confirmCrewMemberSelection(){
        setState(getState().confirmCrewMemberSelection());
    }
    
    //CrewPlacement
    public void placeCrewMember(int crewNumber, int roomNumber){
        setState(getState().placeCrewMember(crewNumber, roomNumber));
    }
    
    public void confirmCrewMemberPlacement(){
        setState(getState().confirmCrewMemberPlacement());
    }
    
    //JourneySelection
    public void generateJourney_ByChoice(int turn, String choice){
        setState(getState().generateJourney_ByChoice(turn, choice));
    }
    
    public void generateJourney_ByRandom(){
        setState(getState().generateJourney_ByRandom());
    }
    
    public void generateJourney_ByDefault(){
        setState(getState().generateJourney_ByDefault());
    }
    
    public void confirmJourneySelection(){
        setState(getState().confirmJourneySelection());
    }
    
    //JourneyPhase
    public void nextTurn(){
        setState(getState().nextTurn());
    }
    
    //ScanningPhase
    public void scanTurn(){
        setState(getState().scanTurn());
    }
    
    public void placeNewAlien(int alienNumber, int roomNumber){
        setState(getState().placeNewAlien(alienNumber, roomNumber));
    }
    
    public void confirmNewAliensPlacement(){
        setState(getState().confirmNewAliensPlacement());
    }
    
    //RestPhase
    public void leaveRest(){
        setState(getState().leaveRest());
    }
    
    //CrewPhase
    public void executeAction(int action){
        setState(getState().executeAction(action));
    }
    
    //Action states
    public void spendAbilityPoints(){
        setState(getState().spendAbilityPoints());
    }
    
    public void spendAbilityPoints(int value){
        setState(getState().spendAbilityPoints(value));
    }
    
    //AlienPhase
    public void moveAllAliens(){
        setState(getState().moveAllAliens());
    }
    
    //General
    public void swapCrewMember(){
        setState(getState().swapCrewMember());
    }
    
    public void rollDice(){
        setState(getState().rollDice());
    }
    
    public void setRollValue(int dieToRoll, int value){
        setState(getState().setDieRoll(dieToRoll, value));
    }
    
    public int getQuantityOfDiceToRoll(){
        return getState().getQuantityOfDiceToRoll();
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
