package de.logic.states;

import de.logic.data.DataGame;

public class DiceRolling extends StateAdapter{
    
    private IStates returnState;
    private int quantityOfDice;
    
    public DiceRolling(DataGame game, IStates returnState, int quantityOfDice) {
        super(game);
        
        if(quantityOfDice < 1 || quantityOfDice > MAX_DICES)
            quantityOfDice = 2;
        
        this.returnState = returnState;
        this.quantityOfDice = quantityOfDice;
    }
    
    
    @Override
    public int getQuantityOfDiceToRoll() {
        return quantityOfDice;
    }
    
    @Override
    public IStates rollDice(){
        this.getGame().rollDice(quantityOfDice);
        
        return returnState;
    }
    
    @Override
    public IStates setDieRoll(int dieToRoll, int value){
        this.getGame().rollDie(dieToRoll, value);
        return new DiceRolling(this.getGame(), returnState, quantityOfDice);
    }
    
    @Override
    public IStates confirmRoll(){
        return returnState;
    }
    
    @Override
    public int currentState(){
        return STATE_DICE_ROLLING;
    }
}
