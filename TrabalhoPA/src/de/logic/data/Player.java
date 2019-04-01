/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.logic.data;
import de.logic.data.members.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tiago
 */
public class Player {
    private String name;
    private List<CrewMember> crew;
    int healthTracker;
    int inspirationPoints;
    int abilityPoints;

    public Player(String name, int healthTracker, int inspirationPoints, int abilityPoints) {
        this.name = name;
        this.healthTracker = healthTracker;
        this.inspirationPoints = inspirationPoints;
        this.abilityPoints = abilityPoints;
        
       crew = new ArrayList<>();
    }

    /**Getters and Setters**/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CrewMember> getCrew() {
        return crew;
    }

    public void setCrew(List<CrewMember> crew) {
        this.crew = crew;
    }

    public int getHealthTracker() {
        return healthTracker;
    }

    public void setHealthTracker(int healthTracker) {
        this.healthTracker = healthTracker;
    }

    public int getInspirationPoints() {
        return inspirationPoints;
    }

    public void setInspirationPoints(int inspirationPoints) {
        this.inspirationPoints = inspirationPoints;
    }

    public int getAbilityPoints() {
        return abilityPoints;
    }

    public void setAbilityPoints(int abilityPoints) {
        this.abilityPoints = abilityPoints;
    }
    
    
    
}
