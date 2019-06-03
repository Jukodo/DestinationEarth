package de.logic.data;

import java.io.Serializable;

public class ParticleDispenser extends Trap implements Constants, Serializable{

    public ParticleDispenser(DataGame dataGame, Room room) {
        super(dataGame, DEF_COST_A_TRAP_PARTICLE, room);
    }

    @Override
    public String toString() {
        return "Particle Dispenser";
    }
    
    @Override
    public boolean detonate(int roomNumber){
        
        if(roomNumber != getRoom().getId()){
            getDataGame().addLog("Selected room doesn't have a Particle Dispenser");
            return false;
        }
        
        Room roomToBoom = getRoom();
        
        if(roomToBoom == null){
            getDataGame().addLog("Selected room doesn't have a Particle Dispenser");
            return false;
        }
        
        int nAliens = roomToBoom.getAliensInside().size();
        
        if(nAliens > 0){
            roomToBoom.removeAllAliens();
            getDataGame().setAliensCount(getDataGame().getAliensCount() - nAliens);
            //aliensCount = aliensCount - nAliens;
        }
        
        
        if(roomToBoom.getMembersInside().size() > 0){
            getDataGame().removeHealthFromPlayer(getDataGame().getPlayer().getHealthTracker());
        }
        
        getDataGame().removeActionPoints(DEF_COST_A_DETONATE_TRAP_PARTICLE);
        roomToBoom.removeTrap();
        
        getDataGame().addLog("Particle Dispenser detonated with success!" + " You killed " + nAliens + " aliens");
        
        return true;
    }

    @Override
    public int getType() {
       return PARTICLE_TRAP;
    }
}
