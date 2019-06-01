package de.logic.states;

import de.logic.data.DataGame;

public class JourneySelection extends StateAdapter{
    
    public JourneySelection(DataGame game) {
        super(game);
    }
    
    @Override
    public boolean generateJourney_ByChoice(int turn, String choice){
        
        if(!this.getGame().editJourney_Choice(turn, choice)){
            return false;
        }
        
        return true;
    }
    
    @Override
    public IStates generateJourney_ByRandom(){
        
        this.getGame().editJourney_Random();
        
        return this;
    }
    
    @Override
    public IStates generateJourney_ByDefault(){
        
        this.getGame().resetJourneyTracker();
        
        return this;
    }
    
    @Override
    public IStates confirmJourneySelection(){
        
        if(this.getGame().isValid_JourneyTracker())
            return new JourneyPhase(this.getGame());
        else
            System.out.println("!isValid_JourneyTracker");
            //Add Log - Journey is invalid
        return this;
    }
    
    @Override
    public void currentState(){
        System.out.println("JourneySelection");
    }
}
