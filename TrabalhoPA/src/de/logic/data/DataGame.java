package de.logic.data;

import de.logic.data.members.*;
import java.util.ArrayList;
import java.util.List;

public class DataGame implements Constants{
    private Player player;
    private Ship ship;
    private List <Alien> aliensList;
    private List<String> logs;
    private String [] journeyTracker;
    private int numTrapsOrganic;
    private int numTrapsParticle;
    private int currentTurn;
    private int dices[];
    private int quantityOfDiceToRoll;

    public DataGame() {
        aliensList = new ArrayList <> ();
        logs = new ArrayList <> ();
        journeyTracker = new String[NUM_TURNS];
        numTrapsOrganic = MAX_TRAPS_ORGANIC;
        numTrapsParticle = MAX_TRAPS_PARTICLE;
        currentTurn = 0;
        ship = new Ship();
        dices = new int[MAX_DICES];
        
        for(int i = 0; i < dices.length; i++){
            dices[i] = 0;
        }

    }

    /**Getters and Setters**/
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public List<Alien> getAliensList() {
        return aliensList;
    }

    public void setAliensList(List<Alien> aliensList) {
        this.aliensList = aliensList;
    }

    public List<String> getLogs() {
        return logs;
    }

    public void setLogs(List<String> logs) {
        this.logs = logs;
    }

    public String[] getJourneyTracker() {
        return journeyTracker;
    }

    public void setJourneyTracker(String[] journeyTracker) {
        this.journeyTracker = journeyTracker;
    }

    public int getNumTrapsOrganic() {
        return numTrapsOrganic;
    }

    public void setNumTrapsOrganic(int numTrapsOrganic) {
        this.numTrapsOrganic = numTrapsOrganic;
    }

    public int getNumTrapsParticle() {
        return numTrapsParticle;
    }

    public void setNumTrapsParticle(int numTrapsParticle) {
        this.numTrapsParticle = numTrapsParticle;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(int currentTurn) {
        this.currentTurn = currentTurn;
    }
    
    public int[] getDices() {
        return dices;
    }

    public void setDices(int[] dices) {
        this.dices = dices;
    }
    
  
    /**Methods**/
    public void nextTurn(){
        setCurrentTurn(getCurrentTurn() + 1);
    }
    
    public int rollDie(int dieId){
        
        int random = 0;
        
        if(dieId < 0 || dieId >= MAX_DICES){
            return 0;
        }
        
       dices[dieId] = random = (int) (Math.random() * (6 - 1)) + 1;
       
       return random;
    }
    
    public int rollDie(int dieId, int value){
               
        if((dieId < 0 || dieId >= MAX_DICES) && (value < 1 || value > 6)){
            return 0;
        }
        
       dices[dieId] = value;
       
       return value;
    }
    
    public void rollDice(int quantityOfDice){
        
        if(quantityOfDice > MAX_DICES){
            return;
        }
        
        for(int i = 0; i < quantityOfDice; i++){
            rollDie(i);
        }
    }
    
    public int getDieValue(int dieId){
        
        if(dieId < 0 || dieId >= MAX_DICES){
            return -1;
        }
        
        return dices[dieId];
    }
        
    public boolean selectCrewMember(int crewNumber, int crewType){
        crewNumber--; //ARRAY INDEX = -1 of its number
        
        int color=-1;//KEEP THE COLOR OF THE CLASS BEFORE (IF BEING CHANGED INSTEAD OF CREATED FOR THE FIRST TIME)
        CrewMember oldMember = getPlayer().getCrewMember(crewNumber);
        if(oldMember != null)
            color = oldMember.getColor();
            
        switch(crewType){
            case 1:
                getPlayer().setCrewMember(crewNumber, new Captain(this, color));
                break;
            case 2:
                getPlayer().setCrewMember(crewNumber, new Commander(this, color));
                break;
            case 3:
                getPlayer().setCrewMember(crewNumber, new CommsOfficer(this, color));
                break;
            case 4:
                getPlayer().setCrewMember(crewNumber, new Doctor(this, color));
                break;
            case 5:
                getPlayer().setCrewMember(crewNumber, new Engineer(this, color));
                break;
            case 6:
                getPlayer().setCrewMember(crewNumber, new MoralOfficer(this, color));
                break;
            case 7:
                getPlayer().setCrewMember(crewNumber, new NavigationOfficer(this, color));
                break;
            case 8:
                getPlayer().setCrewMember(crewNumber, new RedShirt(this, color));
                break;
            case 9:
                getPlayer().setCrewMember(crewNumber, new ScienceOfficer(this, color));
                break;
            case 10:
                getPlayer().setCrewMember(crewNumber, new SecurityOfficer(this, color));
                break;
            case 11:
                getPlayer().setCrewMember(crewNumber, new ShuttlePilot(this, color));
                break;
            case 12:
                getPlayer().setCrewMember(crewNumber, new TransporterChief(this, color));
                break;
            default:
                return false;
        }
        return true;
    }
    
    public boolean selectCrewMemberColor(int crewNumber, int crewMemberColor){
        //Exception ColorException = null;
        if(crewMemberColor < 0 || crewMemberColor >= 12)
            return false;
            //throw ColorException;
        CrewMember cm = getPlayer().getCrewMember(crewNumber-1);
        if(cm == null)
            return false;
        cm.setColor(crewMemberColor-1);
        return true;
    }
    
    public String diceToString(){
        String s = "";
        for(int i = 0; i < dices.length; i++){
           s += "Die "+(i+1)+": [" + getDieValue(i)+ "] ";
        }
        
       return s;
    }
    
    @Override
    public String toString()
    {
        String s;
        
        //s = "Destination Earth, playing as " + this.getPlayer().getName() + System.lineSeparator();
        s = "Turn: " + getCurrentTurn() + System.lineSeparator();
        s+= diceToString();
        s+= System.lineSeparator();
        //s += "Die 1: [" + getDieValue(0)+ "] Die 2: [" + getDieValue(1) + "] Die 3: [" + getDieValue(2) + "]" + System.lineSeparator(); 
        
        return s;
    }
    
    public boolean crewClassNotRepeated(){
        for(int i=0; i<player.getCrew().length-1; i++){
            if(player.getCrewMember(i).getName().equals(player.getCrewMember(i+1).getName()))
                return false;
        }
        return true;
    }
    
    public boolean crewColorNotRepeated(){
        for(int i=0; i<player.getCrew().length-1; i++){
            if(player.getCrewMember(i).getColor() == player.getCrewMember(i+1).getColor())
                return false;
        }
        return true;
    }
}
