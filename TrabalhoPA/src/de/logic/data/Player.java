package de.logic.data;
import static de.logic.data.Constants.*;
import de.logic.data.members.*;
import java.io.Serializable;

public class Player implements Serializable{
    private String name;
    private CrewMember[] crew;
    private int healthTracker;
    private int inspirationPoints;
    private int actionPoints;
    private int roomSealTokens;
    private int organicTrapTokens;
    private int particleTrapTokens;
    private int attackBuff;

    public Player(String name, int healthTracker, int inspirationPoints, int actionPoints) {
        this.name = name;
        this.healthTracker = healthTracker;
        this.inspirationPoints = inspirationPoints;
        this.actionPoints = actionPoints;
        this.roomSealTokens = 0;
        this.organicTrapTokens = MAX_TRAPS_ORGANIC;
        this.particleTrapTokens = MAX_TRAPS_PARTICLE;
        
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
    public boolean haveAlive_RedShirt(){
        for(CrewMember cm:getCrew()){
            if(cm instanceof RedShirt && ((RedShirt)cm).isAlive()){
                return true;
            }
        }
        return false;
    }
    
    public boolean have_CommsOfficer(){
        for(CrewMember cm:getCrew()){
            if(cm instanceof CommsOfficer){
                return true;
            }
        }
        return false;
    }
}
