package de.logic.states;

import de.logic.data.DataGame;

public class PlaceTrap extends StateAdapter{
    
    public PlaceTrap(DataGame game) {
        super(game);
    }
    
    @Override
    public IStates AP_placeTrap(int trapType){
        this.getGame().placeTrap(trapType);
        return new CrewPhase(this.getGame());
    }
    
    @Override
    public int currentState(){
        return STATE_PLACE_TRAP;
    }
    
     @Override
    public IStates cancelAction(){
        return new CrewPhase(this.getGame());
    }
}
