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
public class SealRoom extends StateAdapter{
    
    public SealRoom(DataGame game) {
        super(game);
    }
    
    @Override
    public IStates spendAbilityPoints(int roomToSeal){
        this.getGame().sealRoom(roomToSeal);
        return new CrewPhase(this.getGame());
    }
    
}
