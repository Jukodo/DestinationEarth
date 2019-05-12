package de.logic.data;

import de.logic.data.members.CrewMember;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Room implements Serializable, Constants{
    private DataGame game;
    private static int totalRooms = 0;
    private final int id;
    private boolean isSealed;
    private boolean canBeSealed;
    private String name;
    private List <Room> closestRooms;
    private List <Alien> aliensInside;
    private List <CrewMember> membersInside;
    private Trap trapInside;

    public Room(DataGame game) {
        this.game = game;
        closestRooms = new ArrayList<>();
        aliensInside = new ArrayList<>();
        membersInside = new ArrayList<>();
       
        id = ++totalRooms;
        isSealed = false;
        canBeSealed = false;
    }

     /**Getters and Setters**/
    public static int getTotalRooms() {
        return totalRooms;
    }
    
    public int getId() {
        return id;
    }

    public boolean getIsSealed() {
        return isSealed;
    }

    public void setSealed(boolean isSealed) {
        this.isSealed = isSealed;
    }
    
    public boolean getCanBeSealed(){
        return canBeSealed;
    }
    
    public void setCanBeSealed(boolean canBeSealed){
        this.canBeSealed = canBeSealed;
    }

    public List<Room> getClosestRooms() {
        return closestRooms;
    }

    public void setClosestRooms(List<Room> closestRooms) {
        this.closestRooms = closestRooms;
    }
    
    public void setClosestRoom(Room closestRoom) {
        this.closestRooms.add(closestRoom);
    }

    public List<Alien> getAliensInside() {
        return aliensInside;
    }

    public void setAliensInside(List<Alien> aliensInside) {
        this.aliensInside = aliensInside;
    }
    
    public void setAlienInside(Alien alienInside){
        this.aliensInside.add(alienInside);
    }

    public List<CrewMember> getMembersInside() {
        return membersInside;
    }

    public void setMembersInside(List<CrewMember> membersInside) {
        this.membersInside = membersInside;
    }
    
    public void setMemberInside(CrewMember memberInside){
        this.membersInside.add(memberInside);
    }

    public Trap getTrapInside() {
        return trapInside;
    }

    public void setTrapInside(Trap trapInside) {
        this.trapInside = trapInside;
    } 

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void resetTotalRooms(){
        totalRooms = 0;
    }
    
    /**Methods**/
    public boolean removeRandomAlienFromRoom(){
        
        if(aliensInside.size() > 0){
            for(Alien alien : aliensInside){
                aliensInside.remove(alien);
                return true;
            }
        }
        
        return false;
    }
    
    public boolean removeAlienFromRoom(Alien alien){
        return aliensInside.remove(alien);
    }
    
    public boolean removeMemberFromRoom(CrewMember member){
        return membersInside.remove(member);
    }
    
    public void removeAllAliens(){
        aliensInside.clear();
    }
    
    public void removeTrap(){
        trapInside = null;
    }
    
    /*public Room _chooseClosestRoom_Priority(List <Room> roomsSeen, List <Room> path, int pathSize){
        //Phase 1 - Check if current room is what we are looking for
        //Phase 2 - Check if any of the closest rooms are what we are looking for
        //Phase 3 - Check if the closest rooms of the closest rooms are what we are looking for
        //Phase 4 - Repeat phase 2 and 3 for every closest room
        
        //If already searched for the NUM_ROOMS existing rooms (to prevent infinite loop)
        if(pathSize >= NUM_ROOMS)
            return null;
        
        //Phase 1 - Only supposed to be true on first room (If the current room already has a crew member)
        if(!this.getMembersInside().isEmpty()){
            return this;
        }
        
        pathSize++;
        
        //Phase 2
        for(Room room:closestRooms){
            //Check if current (closest) room has already been checked
            if(roomsSeen.get(room.getId()) != null)
                continue;

            roomsSeen.add(room.getId(), room);

            //If there are crew members inside and the room is NOT sealed
            if(!room.getMembersInside().isEmpty() && !room.getIsSealed()){
                path.add(pathSize, room);
                return room;
            }
        }

        //Phase 3
        for(Room r:closestRooms){
            for(Room room:r.getClosestRooms()){
                //Check if current room has already been checked
                if(roomsSeen.get(room.getId()) != null)
                    continue;

                roomsSeen.add(room.getId(), room);

                //If there are crew members inside and the room is NOT sealed
                if(!room.getMembersInside().isEmpty() && !room.getIsSealed()){

                    return room;
                }else if(pathSize > 1){
                    return null;
                }
            }
        }

        //Phase 4
        for(Room room:closestRooms){
            if(roomsSeen.get(room.getId()) != null)
                room._chooseClosestRoom_Priority(roomsSeen, path, pathSize);
        }

        return null;
    }*/
    
    public Room chooseClosestRoom(List <Room> nextClosestRooms){
        HashMap<Integer, Room> roomsSeen = new HashMap<>();
        
        if(this.getMembersInside().isEmpty())
            return chooseClosestRoom_Algorithm(nextClosestRooms, roomsSeen, 0);
        
        game.addLog("Alien already inside a room with a crew member!");
        return null;
    }
    
    public Room chooseClosestRoom_Algorithm(List <Room> nextClosestRooms, HashMap<Integer, Room> roomsSeen, int pathSize){
        if(++pathSize > 12)
            return null;
        
        System.out.println("pathSize increased: " + pathSize);
        
        for(Room room:nextClosestRooms){
            if(roomsSeen.get(room.getId()) != null){
                System.out.println("room: " + room.getId() + " already inside");
                continue;
            }
            
            System.out.println("roomsSeen added: " + room.getId());
            roomsSeen.put(room.getId(), room);

            if(!room.getMembersInside().isEmpty() && !room.getIsSealed()){
                System.out.println("room found: " + room.getId());
                return room;
            }
        }
        
        List <Room> aux_closestRooms = new ArrayList<>();
        for(Room room:nextClosestRooms){
            for(Room _room:room.getClosestRooms()){
                if(roomsSeen.get(_room.getId()) != null){
                    System.out.println("room: " + _room.getId() + " already inside");
                    continue;
                }
                aux_closestRooms.add(_room);
            }
        }
        
        System.out.println("next phase: " + pathSize + " auxClosestRooms size: " + aux_closestRooms.size());
        
        return chooseClosestRoom_Algorithm(aux_closestRooms, roomsSeen, pathSize);
    }

    @Override
    public String toString() {
        return "Room #" + getId() + " - " + getName();
    }
    
    
}
