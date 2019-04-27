package de.logic.states;

import de.logic.data.DataGame;

public class AlienPhase extends StateAdapter{
    
    public AlienPhase(DataGame game) {
        super(game);
    }
    
    @Override
    public IStates moveAliens(){
        if(!this.getGame().moveAliens())
            return this;
        
        if(this.getGame().gameOverConditions()){
            return new GameOver(this.getGame());
        }
        
        return new JourneyPhase(this.getGame());
    }
}
