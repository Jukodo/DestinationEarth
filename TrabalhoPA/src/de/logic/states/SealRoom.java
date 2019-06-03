package de.logic.states;

import de.logic.data.DataGame;

public class SealRoom extends StateAdapter{
    
    public SealRoom(DataGame game) {
        super(game);
    }
    
    @Override
    public IStates AP_sealRoom(int roomToSeal){
        this.getGame().sealRoom(roomToSeal);
        return new CrewPhase(this.getGame());
    }
    
    @Override
    public String getShipToString(){
        return getGame().getShip().toString();
    }
    
    @Override
    public int currentState(){
        return STATE_SEAL_ROOM;
    }
    
    @Override
    public IStates cancelAction(){
        return new CrewPhase(this.getGame());
    }
}
