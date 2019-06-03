package de.logic.data;

import java.io.Serializable;

public abstract class Trap implements Serializable{
    
    private DataGame dataGame;
    private int cost;
    private Room room;

    public Trap(DataGame dataGame, int cost, Room room) {
        this.dataGame = dataGame;
        this.cost = cost;
        this.room = room;
    }

    public DataGame getDataGame() {
        return dataGame;
    }

    public void setDataGame(DataGame dataGame) {
        this.dataGame = dataGame;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
    
    public Room getRoom(){
        return room;
    }
    
    public void setRoom(Room room){
        this.room = room;
    }
    
    public boolean detonate(int roomNumber){
        return false;
    }
    
  
}
