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
    public void currentState(){
        System.out.println("PlaceTrap");
    }
}
