package de;

import de.logic.data.DataGame;
import de.logic.data.Trap;
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
    
    public int getOrganicTrapTokens(){
        return dataGame.getOrganicTrapTokens();
    }
    
    public int getParticleTrapTokens(){
        return dataGame.getParticleTrapTokens();
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
    
    public void leaveCrewPhase(){
        setState(getState().leaveCrewPhase());
    }
    
    //RestPhase
    public void leaveRestPhase(){
        setState(getState().leaveRestPhase());
    }
    
    //CrewPhase
    public void executeAction(int action){
        setState(getState().executeAction(action));
    }
    
    //Action states
    public void moveCrewMember(int room){
        setState(getState().moveCrewMember(room));
    }
    
    public void attackAliens(int room){
        setState(getState().attackAliens(room));
    }
    
    public void placeTrap(int room, Trap trap){
        setState(getState().placeTrap(room, trap));
    }
    
    public void detonateParticleDispenser(int room){
        setState(getState().detonateParticleDispenser(room));
    }
    
    public void sealRoom(int room){
        setState(getState().sealRoom(room));
    }
 
    //AlienPhase
    public void moveAliens(){
        setState(getState().moveAliens());
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
