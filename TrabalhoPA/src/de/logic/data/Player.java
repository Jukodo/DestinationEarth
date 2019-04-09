package de.logic.data;
import static de.logic.data.Constants.*;
import de.logic.data.members.*;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private CrewMember[] crew;
    int healthTracker;
    int inspirationPoints;
    int actionPoints;

    public Player(String name, int healthTracker, int inspirationPoints, int actionPoints) {
        this.name = name;
        this.healthTracker = healthTracker;
        this.inspirationPoints = inspirationPoints;
        this.actionPoints = actionPoints;
        
       crew = new CrewMember[NUM_CREW_MEMBERS];
    }

    /**Getters and Setters**/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CrewMember[] getCrew() {
        return crew;
    }

    public void setCrew(CrewMember[] crew) {
        this.crew = crew;
    }

    public CrewMember getCrewMember(int index) {
        return crew[index];
    }

    public void setCrewMember(int index, CrewMember crewMember) {
        this.crew[index] = crewMember;
    }
    
    public boolean hasAllMembers(){
        for(int i = 0; i < NUM_CREW_MEMBERS; i++){
            if(this.crew[i] == null)
                return false;
        }
        return true;
    }
    
    public boolean hasAllMembersOnBoard(){
        for(int i = 0; i < NUM_CREW_MEMBERS; i++){
            if(!this.crew[i].isInside())
                return false;
        }
        return true;
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

    public int getActionPoints() {
        return actionPoints;
    }

    public void setActionPoints(int actionPoints) {
        this.actionPoints = actionPoints;
    }
    
    
    
}
