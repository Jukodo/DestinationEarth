package de.logic.data;

import de.logic.data.members.CrewMember;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
    
    public Room chooseClosestRoom(){
        if(!this.getMembersInside().isEmpty())
            return null;
        
        return chooseClosestRoom_Algorithm();
    }
    
    public Room chooseClosestRoom_Algorithm(){
        Queue<Room> toBeSeen = new LinkedList<>();
        List<Room> alreadySeen = new ArrayList<>();
        
        Room auxRoom = null;
        toBeSeen.add(this);
        
        while(alreadySeen.size() < NUM_ROOMS){
            auxRoom = toBeSeen.poll();
            alreadySeen.add(auxRoom);
            
            if(!auxRoom.getMembersInside().isEmpty() && !auxRoom.getIsSealed())
                break;
                
            for(Room neighbor:auxRoom.getClosestRooms()){
                if(!alreadySeen.contains(neighbor)){
                    toBeSeen.add(neighbor);
                }
            }
        }
        
        if(alreadySeen.size() == NUM_ROOMS)
            return null;
        
        while(!alreadySeen.isEmpty()){
            for(int i = 0; i < alreadySeen.size(); i++){
                if(auxRoom.getClosestRooms().contains(alreadySeen.get(i))){
                    if(alreadySeen.get(i) == this){
                        return auxRoom;
                    }
                    auxRoom = alreadySeen.get(i);
                    break;
                }
            }
        }
        
        return null;
    }

    @Override
    public String toString() {
        return "Room #" + getId() + " - " + getName();
    }
    
    
}
