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
    public void currentState(){
        System.out.println("AttackAliens");
    }
    
}
