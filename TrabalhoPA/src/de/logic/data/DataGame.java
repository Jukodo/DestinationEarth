/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.logic.data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tiago
 */
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

    public DataGame() {
        aliensList = new ArrayList <> ();
        logs = new ArrayList <> ();
        journeyTracker = new String[NUM_TURNS];
        numTrapsOrganic = MAX_TRAPS_ORGANIC;
        numTrapsParticle = MAX_TRAPS_PARTICLE;
        currentTurn = 0;
        ship = new Ship();
        dices = new int[MAX_DICES];
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
        
        for(int i = 0; i < dices.length; i++){
            rollDie(i);
        }
    }
}
