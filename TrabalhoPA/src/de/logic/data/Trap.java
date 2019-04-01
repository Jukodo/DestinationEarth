/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.logic.data;

/**
 *
 * @author Tiago
 */
public abstract class Trap {
    
    private DataGame dataGame;
    private int cost;

    public Trap(DataGame dataGame, int cost) {
        this.dataGame = dataGame;
        this.cost = cost;
    }
    
    public abstract void activate();

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
