package de.logic.states;

import de.logic.data.DataGame;

public class AttackAliens extends StateAdapter{
    
    public AttackAliens(DataGame game) {
        super(game);
    }
    
    @Override
    public IStates AP_attackAliens(int room){
        this.getGame().attackAliens(room);
        return new CrewPhase(this.getGame());
    }
    
    @Override
    public int currentState(){
        return STATE_ATTACK_ALIENS;
    }
    
    @Override
    public boolean have_ScienceOfficer(boolean active){
        return getGame().getPlayer().have_ScienceOfficer(active);
    }
    
    @Override
    public String getShipToString(){
        return getGame().getShip().toString();
    }
    
    @Override
    public IStates cancelAction(){
        return new CrewPhase(this.getGame());
    }
    
}
