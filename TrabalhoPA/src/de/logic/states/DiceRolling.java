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
public class DiceRolling extends StateAdapter{
    
    private IStates previousState;
    
    public DiceRolling(DataGame game, IStates previousState) {
        super(game);
        this.previousState = previousState;
    }
    
    @Override
    public IStates rollDice(IStates previousState, int quantityOfDice){
        this.getGame().rollDice(quantityOfDice);
        return previousState;
    }
    
    @Override
    public IStates setRollValue(IStates previousState, int dieToRoll, int value){
        this.getGame().rollDie(dieToRoll, value);
        return previousState;
    }
    
}
