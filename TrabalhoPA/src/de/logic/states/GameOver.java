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
    public int currentState(){
        return STATE_GAME_OVER;
    }
}
