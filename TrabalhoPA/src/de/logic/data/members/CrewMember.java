package de.logic.data.members;

import de.logic.data.DataGame;

public abstract class CrewMember {
    
    private int movement;
    private int attack;
    int color;
    boolean inside;
    private DataGame dataGame;
    
    
    //Doesn't recieve color
    public CrewMember(DataGame dataGame, int movement, int attack){
        this.dataGame = dataGame;
        this.movement = movement;
        this.attack = attack;
        this.inside = false;
    }
    
    //Recieve color
    public CrewMember(DataGame dataGame, int movement, int attack, int color){
        this.dataGame = dataGame;
        this.movement = movement;
        this.attack = attack;
        this.color = color;
        this.inside = false;
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
    
    public boolean isInside() {
        return inside;
    }

    public void setInside(boolean inside) {
        this.inside = inside;
    }

    public DataGame getDataGame() {
        return dataGame;
    }

    public void setDataGame(DataGame dataGame) {
        this.dataGame = dataGame;
    }
  
}
