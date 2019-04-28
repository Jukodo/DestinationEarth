package de.logic.data;

import java.io.Serializable;

public abstract class Trap implements Serializable{
    
    private DataGame dataGame;
    private int cost;

    public Trap(DataGame dataGame, int cost) {
        this.dataGame = dataGame;
        this.cost = cost;
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
    
    
}
