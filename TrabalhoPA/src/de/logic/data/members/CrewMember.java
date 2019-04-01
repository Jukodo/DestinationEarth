/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.logic.data.members;

import de.logic.data.DataGame;

/**
 *
 * @author Tiago
 */
public abstract class CrewMember {
    
    private int movement;
    private int attack;
    int color;
    private DataGame dataGame;  
    
    public CrewMember(DataGame dataGame, int movement, int attack, int color){
        this.dataGame = dataGame;
        this.movement = movement;
        this.attack = attack;
        this.color = color;
    }

    /**Getters and Setters**/
    public int getMovement() {
        return movement;
    }

    public void setMovement(int movement) {
        this.movement = movement;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }
    
    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public DataGame getDataGame() {
        return dataGame;
    }

    public void setDataGame(DataGame dataGame) {
        this.dataGame = dataGame;
    }
  
}
