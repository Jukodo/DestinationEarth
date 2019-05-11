package de;

import de.logic.data.Alien;
import de.logic.data.DataGame;
import de.logic.data.Player;
import de.logic.data.Ship;
import de.logic.data.Trap;
import de.logic.states.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

public class DestinationEarth implements Serializable{
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
    
    /**Data game methods - Gets**/
    
    public List<String> getLogs(){
        return dataGame.getLogs();
    }
    
    public int getOrganicTrapTokens(){
        return dataGame.getOrganicTrapTokens();
    }
    
    public int getParticleTrapTokens(){
        return dataGame.getParticleTrapTokens();
    }
    
    public Player getPlayer(){
        return dataGame.getPlayer();
    }
    
    public Ship getShip(){
        return dataGame.getShip();
    }
    
    public List<Alien> getNewAliens(){
        return dataGame.getNewAliens();
    }
    
    public int getActiveCrewMember(){
        return dataGame.getActiveCrewMember();
    }
    
    public int getAvailableActions_Quant(){
        return dataGame.getAvailableActions_Quant();
    }
    
    public int getAvailableInspirations_Quant(){
        return dataGame.getAvailableInspirations_Quant();
    }
    
    public int getDiceValue(int numDices){
        return dataGame.getDiceValue(numDices);
    }
    
    public int getActiveNewAlien(){
        return dataGame.getActiveNewAlien();
    }
    
    public String getJourneyTrackerTurn(int turn){
        return dataGame.getJourneyTrackerTurn(turn);
    }
    
    public boolean getTurnScanned(){
        return dataGame.getTurnScanned();
    }
    
    /**Data game methods - Functions**/
    public void swapActiveCrewMember(){
        dataGame.swapActiveCrewMember();
    }
    
    public void swapActiveNewAlien(){
        dataGame.swapActiveNewAlien();
    }
    
    public void clearLogs(){
        dataGame.clearLogs();
    }
    
    public boolean isValid_JourneyTurn(int turn, String event){
        return dataGame.isValid_JourneyTurn(turn, event);
    }
    
    public boolean sacrificeCrewMember(){
        return dataGame.sacrificeCrewMember();
    }
    
    public boolean activeIsDoctor(){
        return dataGame.activeIsDoctor();
    }
    
    public boolean activeIsEngineer(){
        return dataGame.activeIsEngineer();
    }
    
    /**Data game methods - To Strings**/
    public String crewMemberInfoToString(){
        return dataGame.crewMemberInfoToString();
    }
    
    public String newAliensInfoToString(){
        return dataGame.newAliensInfoToString();
    }
    
    public String currentJourneyToString(){
        return dataGame.currentJourneyToString();
    }
    
    public String activeCrewMemberInfoToString(){
        return dataGame.activeCrewMemberInfoToString();
    }
    
    public String trapTokensToString(){
        return dataGame.trapTokensToString();
    }
    
    public String sealTokensToString(){
        return dataGame.sealTokensToString();
    }
    
    public String diceToString(){
        return dataGame.diceToString();
    }
    
    public String getAvailableActions(){
        return dataGame.getAvailableActions();
    }
    
    public String getAvailableInspirations(){
        return dataGame.getAvailableInspirations();
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
    public void IP_addHealthPoint(){
        setState(getState().IP_addHealthPoint());
    }
    
    public void IP_repairHull(){
        setState(getState().IP_repairHull());
    }
    
    public void IP_buildOrganicDetonator(){
        setState(getState().IP_buildOrganicDetonator());
    }
    
    public void IP_addMovement(int activeCrewMember){
        setState(getState().IP_addMovement(activeCrewMember));
    }
    
    public void IP_buildParticleDesperser(){
        setState(getState().IP_buildParticleDesperser());
    }
    
    public void IP_addSealedRoomToken(){
        setState(getState().IP_addSealedRoomToken());
    }
    
    public void IP_addAttackDie(int activeCrewMember){
        setState(getState().IP_addAttackDie(activeCrewMember));
    }
    
    public void IP_addValueToAttackDie(){
        setState(getState().IP_addValueToAttackDie());
    }
    
    public void leaveRestPhase(){
        setState(getState().leaveRestPhase());
    }
    
    //CrewPhase
    public void moveCrewMember(int room){
        setState(getState().moveCrewMember(room));
    }
    
    public void attackAliens(int room){
        setState(getState().attackAliens(room));
    }
    
    public void placeTrap(Trap trap){
        setState(getState().placeTrap(trap));
    }
    
    public void detonateParticleDispenser(int room){
        setState(getState().detonateParticleDispenser(room));
    }
    
    public void sealRoom(int room){
        setState(getState().sealRoom(room));
    }
    
    public void healPlayer(){
        getState().healPlayer();
    }
    
    public void fixHull(){
        getState().fixHull();
    }
    
    public void sacrifice(){
        getState().sacrifice();
    }
  
    //AlienPhase
    public void moveAliens(){
        setState(getState().moveAliens());
    }
    
    //AlienPhase
    public void playAgain(){
        setState(getState().playAgain());
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
    
    public void confirmRoll(){
        setState(getState().confirmRoll());
    }
    
    public int getQuantityOfDiceToRoll(){
        return getState().getQuantityOfDiceToRoll();
    }
    
    public void quit(){
        setState(getState().quit());
    }
    
    /**Save and Load**/
    
     public void saveGame() throws FileNotFoundException, IOException{
        try(ObjectOutputStream save = new ObjectOutputStream(new FileOutputStream("SaveFile"))) { 
            save.writeUnshared(this);
            this.getDataGame().addLog("Game saved!");
        }
    }
    
    public DestinationEarth loadGame() throws FileNotFoundException, IOException, ClassNotFoundException{
        
        File saveFile = new File("saveFile");
        
        if(!saveFile.isFile() || !saveFile.canRead()){
            this.getDataGame().addLog("Game loading failed! File not found...");
            return this;
        }
        
        try(ObjectInputStream load = new ObjectInputStream(new FileInputStream(saveFile))) { 
            DestinationEarth loadedGame = (DestinationEarth) load.readUnshared();
            if(loadedGame != null){
                loadedGame.getDataGame().addLog("Game loaded!");
                return loadedGame;
            }else{
                this.getDataGame().addLog("Game loading failed!");
                return this;
            }
        }
    }
}
