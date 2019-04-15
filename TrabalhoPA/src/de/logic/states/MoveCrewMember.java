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
public class MoveCrewMember extends StateAdapter{
        
    public MoveCrewMember(DataGame game) {
        super(game);
    }
 
    @Override
    public IStates spendAbilityPoints(int roomToMove){
        this.getGame().moveActiveCrewMember(roomToMove);
        return new CrewPhase(this.getGame());
    }
    
}
