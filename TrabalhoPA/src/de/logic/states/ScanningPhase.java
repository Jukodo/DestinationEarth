package de.logic.states;

import de.logic.data.DataGame;

public class ScanningPhase extends StateAdapter{
    
    public ScanningPhase(DataGame game) {
        super(game);
    }
    
    @Override
    public IStates rollDice(){
        return new DiceRolling(this.getGame(), this, 2);
    }
    
    @Override
    public IStates scanTurn(){
        if(this.getGame().eventIsRest(this.getGame().getJourneyTrackerTurn(this.getGame().getCurrentTurn()))){
            return new RestPhase(this.getGame());
        }
        else if(!this.getGame().eventIsAlienSpawn(this.getGame().getCurrentTurn(), this.getGame().getJourneyTrackerTurn(this.getGame().getCurrentTurn()))){
            //Add Log - Event is invalid
        }
        
        this.getGame().spawnAliens(this.getGame().getAlienSpawnNumber(this.getGame().getJourneyTrackerTurn(this.getGame().getCurrentTurn())));
        
        return this;
    }
    
    @Override
    public IStates placeNewAlien(int alienNumber, int roomNumber){
        if(!this.getGame().placeNewAlien(alienNumber, roomNumber)){
            //Add Log - Couldnt place alien
        }
        return this;
    }
    
    @Override
    public IStates confirmNewAliensPlacement(){
        
        this.getGame().clearNewAliens();
        
        return new CrewPhase(this.getGame());
    }
    
    @Override
    public void currentState(){
        System.out.println("ScanningPhase");
    }
}
