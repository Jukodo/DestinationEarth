package de.logic.data.members;

import de.logic.data.DataGame;
import de.logic.data.Room;

public abstract class CrewMember {
    
    private int movement;
    private int attack;
    int color;
    boolean inside;
    private DataGame dataGame;
    private Room room;
    private int movementsBeforeFree;
    
    //Doesn't recieve color
    public CrewMember(DataGame dataGame, int movement, int attack){
        this.dataGame = dataGame;
        this.movement = movement;
        this.attack = attack;
        this.inside = false;
        this.movementsBeforeFree = 0;
    }
    
    //Recieve color
    public CrewMember(DataGame dataGame, int movement, int attack, int color){
        this.dataGame = dataGame;
        this.movement = movement;
        this.attack = attack;
        if(color < 0 || color > 11)
            color = 0;
        this.color = color;
        this.inside = false;
    }

    abstract public String getName();
    
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
    
    public Room getRoom(){
        return room;
    }
    
    public void setRoom(Room room){
        this.room = room;
    }

    public int getMovementsBeforeFree() {
        return movementsBeforeFree;
    }

    public void setMovementsBeforeFree(int movementsBeforeFree) {
        this.movementsBeforeFree = movementsBeforeFree;
    }
    
    
  
}
