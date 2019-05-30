package de.logic.states;

import de.logic.data.DataGame;
import de.logic.data.Player;

public class Beginning extends StateAdapter{
    
    public Beginning(DataGame game) {
        super(game);
    }
    
    @Override
    public IStates start(String playerName){
        
        this.getGame().setPlayer(new Player(this.getGame(), playerName, DEF_HEALTH_TRACKER, DEF_INSPIRATION_POINTS, DEF_ACTION_POINTS));
        
        this.getGame().resetJourneyTracker();
        
        return new CrewSelection(this.getGame());
    }
    
    @Override
    public void currentState(){
        System.out.println("Beginning");
    }
}
