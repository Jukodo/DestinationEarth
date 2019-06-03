package de.logic.data;
import static de.logic.data.Constants.*;
import de.logic.data.members.*;
import java.io.Serializable;

public class Player implements Serializable{
    private DataGame game;
    private String name;
    private CrewMember[] crew;
    private int healthTracker;
    private int inspirationPoints;
    private int actionPoints;
    private int roomSealTokens;
    private int organicTrapTokens;
    private int particleTrapTokens;
    private int attackBuff;

    public Player(DataGame game, String name, int healthTracker, int inspirationPoints, int actionPoints) {
        this.game = game;
        this.name = name;
        this.healthTracker = healthTracker;
        this.inspirationPoints = inspirationPoints;
        this.actionPoints = actionPoints;
        this.roomSealTokens = MAX_SEALED_ROOMS;
        this.organicTrapTokens = MAX_TRAPS_ORGANIC;
        this.particleTrapTokens = MAX_TRAPS_PARTICLE;
        this.attackBuff = 0;
        
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
  
    public int getHealthTracker() {
        return healthTracker;
    }

    public boolean setHealthTracker(int healthTracker) {
        if(healthTracker > MAX_HEALTH)
            healthTracker = MAX_HEALTH;
        this.healthTracker = healthTracker;
        return true;
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

    public int getRoomSealTokens() {
        return roomSealTokens;
    }

    public void setRoomSealTokens(int roomSealTokens) {
        this.roomSealTokens = roomSealTokens;
    }

    public int getOrganicTrapTokens() {
        return organicTrapTokens;
    }

    public boolean setOrganicTrapTokens(int organicTrapTokens) {
        
        if(organicTrapTokens > MAX_TRAPS_ORGANIC)
            organicTrapTokens = MAX_TRAPS_ORGANIC;
        
        this.organicTrapTokens = organicTrapTokens;
        
        return true;
    }

    public int getParticleTrapTokens() {
        return particleTrapTokens;
    }

    public boolean setParticleTrapTokens(int particleTrapTokens) {
        
        if(particleTrapTokens > MAX_TRAPS_PARTICLE)
            particleTrapTokens = MAX_TRAPS_PARTICLE;
        
        this.particleTrapTokens = particleTrapTokens;
        
        return true;
    }

    public int getAttackBuff() {
        return attackBuff;
    }

    public boolean setAttackBuff(int attackBuff) {
        if(this.attackBuff == MAX_ATTACK_BUFF)//Already has maxed out HP
            return false;
        
        if(attackBuff > MAX_ATTACK_BUFF)
            attackBuff = MAX_ATTACK_BUFF;
        
        this.attackBuff = attackBuff;
        
        return true;
    }
    
    
    /**Methods**/
    public boolean hasAllMembers(){
        for(int i = 0; i < NUM_CREW_MEMBERS; i++){
            if(this.crew[i] == null){
                game.addLog("Your crew is not complete! Please fill your crew...");
                return false;
            }
        }
        return true;
    }
    
    public boolean hasAllMembersOnBoard(){
        for(int i = 0; i < NUM_CREW_MEMBERS; i++){
            if(!this.crew[i].isInside()){
                game.addLog("Your crew members are not inside! Please select a room for each member...");
                return false;
            }
        }
        return true;
    }
    public boolean have_RedShirt(boolean active, boolean alive){
        if(active){
            if(crew[game.getActiveCrewMember()] instanceof RedShirt)
                return true;
            else
                return false;
        }else{
            for(CrewMember cm:getCrew()){
                if(alive){
                    if(cm instanceof RedShirt && ((RedShirt)cm).isAlive())
                        return true;
                }else{
                    if(cm instanceof RedShirt)
                        return true;
                }
            }
            return false;
        }
    }
    
    public boolean have_CommsOfficer(boolean active){
        if(active){
            if(crew[game.getActiveCrewMember()] instanceof CommsOfficer)
                return true;
            else
                return false;
        }else{
            for(CrewMember cm:getCrew()){
                if(cm instanceof CommsOfficer)
                    return true;
            }
            return false;
        }
    }
    
    public boolean have_Doctor(boolean active){
        if(active){
            if(crew[game.getActiveCrewMember()] instanceof Doctor)
                return true;
            else
                return false;
        }else{
            for(CrewMember cm:getCrew()){
                if(cm instanceof Doctor)
                    return true;
            }
            return false;
        }
    }
    
    public boolean have_Engineer(boolean active){
        if(active){
            if(crew[game.getActiveCrewMember()] instanceof Engineer)
                return true;
            else
                return false;
        }else{
            for(CrewMember cm:getCrew()){
                if(cm instanceof Engineer)
                    return true;
            }
            return false;
        }
    }
    
    public boolean have_ScienceOfficer(boolean active){
        if(active){
            if(crew[game.getActiveCrewMember()] instanceof ScienceOfficer)
                return true;
            else
                return false;
        }else{
            for(CrewMember cm:getCrew()){
                if(cm instanceof ScienceOfficer)
                    return true;
            }
            return false;
        }
    }
}
