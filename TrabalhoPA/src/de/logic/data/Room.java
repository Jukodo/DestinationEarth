/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.logic.data;

import de.logic.data.members.CrewMember;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tiago
 */
public class Room {
    private static int totalRooms = 0;
    private final int id;
    private boolean sealed;
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
       sealed = false;
    }

     /**Getters and Setters**/
    public static int getTotalRooms() {
        return totalRooms;
    }
    
    public int getId() {
        return id;
    }

    public boolean isSealed() {
        return sealed;
    }

    public void setSealed(boolean sealed) {
        this.sealed = sealed;
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

    public List<CrewMember> getMembersInside() {
        return membersInside;
    }

    public void setMembersInside(List<CrewMember> membersInside) {
        this.membersInside = membersInside;
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
}
