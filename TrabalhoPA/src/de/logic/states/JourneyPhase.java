package de.logic.states;

import de.logic.data.DataGame;

public class JourneyPhase extends StateAdapter{
    
    public JourneyPhase(DataGame game){
        super(game);
    }
    
    @Override
    public IStates nextTurn(){
        this.getGame().resetActionPoints();
        this.getGame().nextTurn();
        return new ScanningPhase(this.getGame());
    }
    
    @Override
    public void currentState(){
        System.out.println("JourneyPhase");
    }
}
