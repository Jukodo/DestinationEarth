package de.logic.states;

import de.logic.data.DataGame;
import de.logic.data.Trap;

public class PlaceTrap extends StateAdapter{
    
    public PlaceTrap(DataGame game) {
        super(game);
    }
    
    @Override
    public IStates AP_placeTrap(int trapType){
        this.getGame().placeTrap(trapType);
        return new CrewPhase(this.getGame());
    }
    
}
