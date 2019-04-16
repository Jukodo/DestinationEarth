package de.logic.data.members;

import static de.logic.data.Constants.*;
import de.logic.data.DataGame;
import de.logic.data.Room;

public abstract class CrewMember {
    
    private int movement;
    private int attack;
    int color;
    private DataGame dataGame;
    private Room room;
    private int movementsBeforeFree;
    
    //Doesn't recieve color
    public CrewMember(DataGame dataGame, int movement, int attack){
        this.dataGame = dataGame;
        this.movement = movement;
        this.attack = attack;
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
    }

    abstract public String getName();
    
    /**Getters and Setters**/
    public int getMovement() {
        return movement;
    }

    public boolean setMovement(int movement) {
        if(this.movement == MAX_MOVEMENT)//Already has maxed out movement
            return false;
        
        if(movement > MAX_MOVEMENT)
            movement = MAX_MOVEMENT;
        
        this.movement = movement;
        
        return true;
    }

    public int getAttack() {
        return attack;
    }

    public boolean setAttack(int attack) {
        if(attack > MAX_ATTACK)
            return false;
        this.attack = attack;
        return true;
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
    
    public Room getRoom(){
        return room;
    }
    
    public void setRoom(Room room){
        this.room = room;
    }
    
    public void leaveRoom(){
        this.room.removeMemberFromRoom(this);
        this.room = null;
    }
    
    public void enterRoom(Room room){
        
        if(isInside())
            leaveRoom();
        
        this.room = room;
        room.setMemberInside(this);
    }
    
    public boolean isInside() {
        return room != null;
    }

    public int getMovementsBeforeFree() {
        return movementsBeforeFree;
    }

    public void setMovementsBeforeFree(int movementsBeforeFree) {
        this.movementsBeforeFree = movementsBeforeFree;
    }
    
    
  
}
