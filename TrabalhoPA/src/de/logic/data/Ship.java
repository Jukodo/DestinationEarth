/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.logic.data;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Tiago
 */
public class Ship implements Constants{
    private HashMap<Integer, Room> rooms;
    private int hullTracker;

    public Ship() {
        
        hullTracker = DEF_HULL_TRACKER;
        rooms = new HashMap<>();
        
        for(int i = 1; i <= NUM_ROOMS; i++){
            rooms.put(i, new Room());
        }
        
        for(int i = 1; i < NUM_ROOMS+1; i++){
            switch(i){
                case 1:
                    rooms.get(i).setName("Bridge");
                    rooms.get(i).setClosestRoom(rooms.get(5));
                    rooms.get(i).setClosestRoom(rooms.get(8));
                    rooms.get(i).setCanBeSealed(false);
                    break;
                case 2:
                    rooms.get(i).setName("Sick Bay");
                    rooms.get(i).setClosestRoom(rooms.get(6));
                    rooms.get(i).setClosestRoom(rooms.get(7));
                    rooms.get(i).setClosestRoom(rooms.get(8));
                    rooms.get(i).setCanBeSealed(false);
                    break;
                case 3:
                    rooms.get(i).setName("Brig");
                    rooms.get(i).setClosestRoom(rooms.get(5));
                    rooms.get(i).setClosestRoom(rooms.get(9));
                    rooms.get(i).setCanBeSealed(true);
                    break;
                case 4:
                    rooms.get(i).setName("Crew Quarters");
                    rooms.get(i).setClosestRoom(rooms.get(8));
                    rooms.get(i).setClosestRoom(rooms.get(11));
                    rooms.get(i).setCanBeSealed(true);
                    break;
                case 5:
                    rooms.get(i).setName("Conference Room");
                    rooms.get(i).setClosestRoom(rooms.get(1));
                    rooms.get(i).setClosestRoom(rooms.get(3));
                    rooms.get(i).setClosestRoom(rooms.get(8));
                    rooms.get(i).setClosestRoom(rooms.get(10));
                    rooms.get(i).setCanBeSealed(false);
                    break;
                case 6:
                    rooms.get(i).setName("Shuttle Bay");
                    rooms.get(i).setClosestRoom(rooms.get(2));
                    rooms.get(i).setClosestRoom(rooms.get(10));
                    rooms.get(i).setCanBeSealed(false);
                    break;
                case 7:
                    rooms.get(i).setName("Weapons Bay");
                    rooms.get(i).setClosestRoom(rooms.get(2));
                    rooms.get(i).setClosestRoom(rooms.get(11));
                    rooms.get(i).setCanBeSealed(true);
                    break;
                case 8:
                    rooms.get(i).setName("Mess Hall");
                    rooms.get(i).setClosestRoom(rooms.get(1));
                    rooms.get(i).setClosestRoom(rooms.get(2));
                    rooms.get(i).setClosestRoom(rooms.get(4));
                    rooms.get(i).setClosestRoom(rooms.get(5));
                    rooms.get(i).setCanBeSealed(false);
                    break;
                case 9:
                    rooms.get(i).setName("Engineering");
                    rooms.get(i).setClosestRoom(rooms.get(3));
                    rooms.get(i).setClosestRoom(rooms.get(12));
                    rooms.get(i).setCanBeSealed(true);
                    break;
                case 10:
                    rooms.get(i).setName("Astrometrics");
                    rooms.get(i).setClosestRoom(rooms.get(5));
                    rooms.get(i).setClosestRoom(rooms.get(6));
                    rooms.get(i).setClosestRoom(rooms.get(12));
                    rooms.get(i).setCanBeSealed(false);
                    break;
                case 11:
                    rooms.get(i).setName("Holodeck");
                    rooms.get(i).setClosestRoom(rooms.get(4));
                    rooms.get(i).setClosestRoom(rooms.get(7));
                    rooms.get(i).setCanBeSealed(true);
                    break;
                case 12:
                    rooms.get(i).setName("Hydroponics");
                    rooms.get(i).setClosestRoom(rooms.get(9));
                    rooms.get(i).setClosestRoom(rooms.get(10));
                    rooms.get(i).setCanBeSealed(true);
                    break;
            }
        }
    }

    /**Getters and Setters**/
    public HashMap<Integer, Room> getRooms() {
        return rooms;
    }
    
    public Room getRoom(int index) {
        return rooms.get(index);
    }

    public int getHullTracker() {
        return hullTracker;
    }

    public void setHullTracker(int hullTracker) {
        this.hullTracker = hullTracker;
    }
    
    
    
}
