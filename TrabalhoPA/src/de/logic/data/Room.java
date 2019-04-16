package de.logic.data;

import de.logic.data.members.CrewMember;
import java.util.ArrayList;
import java.util.List;

public class Room {
    private static int totalRooms = 0;
    private final int id;
    private boolean isSealed;
    private boolean canBeSealed;
    private String name;
    private List <Room> closestRooms;
    private List <Alien> aliensInside;
    private List <CrewMember> membersInside;
    private Trap trapInside;

    public Room() {
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

    @Override
    public String toString() {
        return "Room #" + getId() + " - " + getName();
    }
    
    
}
