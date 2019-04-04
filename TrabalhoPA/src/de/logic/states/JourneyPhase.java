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
public class JourneyPhase extends StateAdapter{
    
    public JourneyPhase(DataGame game) 
    {
        super(game);
    }
    
    @Override
    public IStates nextTurn(){
        this.getGame().nextTurn();
        return new ScanningPhase(this.getGame());
    }
}
