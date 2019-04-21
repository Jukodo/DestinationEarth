/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.logic.states;

import de.logic.data.DataGame;
import de.logic.data.Trap;

/**
 *
 * @author Tiago
 */
public class PlaceTrap extends StateAdapter{
    
    public PlaceTrap(DataGame game) {
        super(game);
    }
    
    @Override
    public IStates placeTrap(Trap trap){
        this.getGame().placeTrap(trap);
        return new CrewPhase(this.getGame());
    }
    
}
