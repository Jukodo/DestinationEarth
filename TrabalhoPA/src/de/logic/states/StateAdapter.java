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
    public IStates start(String playerName) {
        return this;
    }

    @Override
    public IStates selectCrewMember(int crewNumber, int crewType) {
        return this;
    }

    @Override
    public IStates selectCrewMemberColor(int crewNumber, int crewNumberColor) {
        return this;
    }

    @Override
    public IStates confirmCrewMemberSelection() {
        return this;
    }
    
    @Override
    public IStates placeCrewMember(int crewNumber, int roomNumber){
        return this;
    }
    
    @Override
    public IStates confirmCrewMemberPlacement() {
        return this;
    }
    
    @Override
    public IStates generateJourney_ByChoice(int turn, String choice){
        return this;
    }
    
    @Override
    public IStates generateJourney_ByRandom(){
        return this;
    }
    
    @Override
    public IStates generateJourney_ByDefault(){
        return this;
    }
    
    @Override
    public IStates confirmJourneySelection() {
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
    public IStates swapCrewMember() {
        return this;
    }

    @Override
    public IStates rollDice() {
        return this;
    }

    @Override
    public IStates setDieRoll(int dieToRoll, int value) {
        return this;
    }
    
    @Override
    public int getQuantityOfDiceToRoll(){
        return 0;
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
