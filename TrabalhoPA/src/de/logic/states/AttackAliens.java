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
public class AttackAliens extends StateAdapter{
    
    public AttackAliens(DataGame game) {
        super(game);
    }
    
    public IStates attackAliens(int room){
        this.getGame().attackAliens(room);
        return new CrewPhase(this.getGame());
    }
    
}
