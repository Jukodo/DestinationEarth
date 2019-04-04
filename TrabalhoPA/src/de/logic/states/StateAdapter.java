package de.logic.states;

import de.logic.data.DataGame;

public class StateAdapter implements IStates, de.logic.data.Constants{
    private DataGame game;
    
    public StateAdapter(DataGame game){
        this.game = game;
    }
    
    public DataGame getGame(){
        return game;
    }
    
    public void setGame(DataGame game){
        this.game = game;
    }

    @Override
    public IStates start() {
        return this;
    }

    @Override
    public IStates setPlayerName(String name) {
        return this;
    }

    @Override
    public IStates selectCrewMember(int member) {
        return this;
    }

    @Override
    public IStates initializeCrewMembers() {
        return this;
    }

    @Override
    public IStates nextTurn() {
        return this;
    }

    @Override
    public IStates scanTurn() {
        return this;
    }

    @Override
    public IStates spendInspirationPoints() {
        return this;
    }

    @Override
    public IStates spendAbilityPoints() {
        return this;
    }

    @Override
    public IStates moveAllAliens() {
        return this;
    }

    @Override
    public IStates activateCrewMember(int member) {
        return this;
    }

    @Override
    public IStates rollDie(IStates previousState) {
        return this;
    }

    @Override
    public IStates setDieValue(IStates previousState) {
        return this;
    }

    @Override
    public IStates saveGame() {
        return this;
    }

    @Override
    public IStates loadGame() {
        return this;
    }

    @Override
    public IStates quit() {
        return this;
    }

}
