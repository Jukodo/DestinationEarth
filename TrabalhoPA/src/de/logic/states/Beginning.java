/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.logic.states;

import de.logic.data.DataGame;
import de.logic.data.Player;

/**
 *
 * @author Tiago
 */
public class Beginning extends StateAdapter{
    
    public Beginning(DataGame game) {
        super(game);
    }
    
    @Override
    public IStates start(String playerName){
        this.getGame().setPlayer(new Player(playerName, DEF_HEALTH_TRACKER, DEF_INSPIRATION_POINTS, DEF_ABILITY_POINTS));
        //return new DiceRolling(this.getGame(), this, 2);
        return new CrewSelection(this.getGame());
    }
    
}
