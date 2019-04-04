/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.logic.states;

import de.logic.data.DataGame;

/**
 *
 * @author Tiago
 */
public class ScanningPhase extends StateAdapter{
    
    public ScanningPhase(DataGame game) {
        super(game);

    }
    
    @Override
    public IStates scanTurn(){
        
        if(this.getGame().getCurrentTurn() == NUM_TURNS){
            return new GameOver(this.getGame());
        }
        else if(this.getGame().getJourneyTracker()[this.getGame().getCurrentTurn()].compareToIgnoreCase("R") == 0){
            return new RestPhase(this.getGame());
        }
        
        return new CrewPhase(this.getGame());
    }
    
}
