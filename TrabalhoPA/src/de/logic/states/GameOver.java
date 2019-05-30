package de.logic.states;

import de.logic.data.DataGame;

public class GameOver extends StateAdapter{
  
    public GameOver(DataGame game) {
        super(game);
    }
    
    @Override
    public IStates playAgain(){
        return new Beginning(this.getGame());
    }
    
    @Override
    public void currentState(){
        System.out.println("GameOver");
    }
}
