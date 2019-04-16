package de.logic.states;

import de.logic.data.DataGame;

public class RestPhase extends StateAdapter{
    
    public RestPhase(DataGame game) {
        super(game);
    }
    
    @Override
    public IStates leaveRest(){
        return new CrewPhase(this.getGame());
    }
}
