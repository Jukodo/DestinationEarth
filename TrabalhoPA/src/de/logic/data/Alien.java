package de.logic.data;

public class Alien{
    
    private Room room;

    public Alien() {
        
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
    
    public void leaveRoom(){
        this.room.removeAlienFromRoom(this);
        this.room = null;
    }
    
    public void enterRoom(Room room){
        
        if(room == null)
            return;
        
        if(isInside())
            leaveRoom();
        
        this.room = room;
        room.setAlienInside(this);
    }
    
    public boolean isInside(){
        return this.room != null;
    }
}
