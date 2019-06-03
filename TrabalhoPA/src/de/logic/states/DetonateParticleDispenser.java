package de.logic.states;

import de.logic.data.DataGame;

public class DetonateParticleDispenser extends StateAdapter{
    
    public DetonateParticleDispenser(DataGame game) {
        super(game);
    }
    
    @Override
    public IStates AP_detonateParticleDispenser(int room){
        
        this.getGame().detonateParticleDispenser(room);
        
        return new CrewPhase(this.getGame());
    }
    
    @Override
    public String getShipToString(){
        return getGame().getShip().toString();
    }
    
    @Override
    public int currentState(){
        return STATE_DETONATE_PARTICLE;
    }
}
