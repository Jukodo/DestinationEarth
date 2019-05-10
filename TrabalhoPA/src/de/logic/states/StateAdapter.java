package de.logic.states;

import de.logic.data.DataGame;
import de.logic.data.Trap;

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
    public IStates placeNewAlien(int alienNumber, int roomNumber){
        return this;
    }
    
    @Override
    public IStates confirmNewAliensPlacement(){
        return this;
    }
    
    @Override
    public IStates executeAction(int action){
        return this;
    }
    
    @Override
    public IStates leaveCrewPhase() {
        return this;
    }

    @Override
    public IStates IP_addHealthPoint(){
        return this;
    }
    
    @Override
    public IStates IP_repairHull(){
        return this;
    }
    
    @Override
    public IStates IP_buildOrganicDetonator(){
        return this;
    }
    
    @Override
    public IStates IP_addMovement(int activeCrewMember){
        return this;
    }
    
    @Override
    public IStates IP_buildParticleDesperser(){
        return this;
    }
    
    @Override
    public IStates IP_addSealedRoomToken(){
        return this;
    }
    
    @Override
    public IStates IP_addAttackDie(int activeCrewMember){
        return this;
    }
    
    @Override
    public IStates IP_addValueToAttackDie(){
        return this;
    }
    
    @Override
    public IStates leaveRestPhase() {
        return this;
    }

    @Override
    public IStates moveCrewMember(int room) {
        return this;
    }
    
     @Override
    public IStates attackAliens(int room){
        return this;
    }
 
    @Override
    public IStates placeTrap(Trap trap){
        return this;
    }
    
    @Override
    public IStates detonateParticleDispenser(int room){
        return this;
    }
    
    @Override
    public IStates sealRoom(int room) {
        return this;
    }
    
    @Override
    public IStates moveAliens() {
        return this;
    }
    
    @Override
    public IStates playAgain(){
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
    public IStates confirmRoll() {
        return this;
    }
    
    @Override
    public int getQuantityOfDiceToRoll(){
        return 0;
    }

    /*
    @Override
    public IStates saveGame() {
        return this;
    }

    @Override
    public IStates loadGame() {
        return this;
    }*/

    @Override
    public IStates quit() {
        return this;
    }

}
