package de.logic.states;

import de.logic.data.DataGame;
import de.logic.data.Trap;
import de.logic.data.members.Doctor;
import de.logic.data.members.Engineer;

public class CrewPhase extends StateAdapter{
 
    public CrewPhase(DataGame game) {
        super(game);
    }
    
    @Override
    public IStates swapCrewMember(){
        this.getGame().swapActiveCrewMember();
        return new CrewPhase(this.getGame());
    }
    
    @Override 
    public IStates moveCrewMember(int room){
        return new MoveCrewMember(this.getGame());
    }
    
    @Override 
    public IStates attackAliens(int room){
        return new DiceRolling(this.getGame(), new AttackAliens(this.getGame()), this.getGame().getPlayer().getCrewMember(this.getGame().getActiveCrewMember()-1).getAttack());
    }
    
    @Override 
    public IStates placeTrap(Trap trap){
        return new PlaceTrap(this.getGame());
    }
    
    @Override 
    public IStates detonateParticleDispenser(int room){
        return new DetonateParticleDispenser(this.getGame());
    }
    
    @Override 
    public IStates sealRoom(int room){
        return new SealRoom(this.getGame());
    }
    
    @Override 
    public boolean healPlayer(){
        
        if(this.getGame().getPlayer().getCrewMember(this.getGame().getActiveCrewMember()-1) instanceof Doctor){
            return this.getGame().healPlayer();
        }
        
        return false;
    }
    
    @Override 
    public boolean fixHull(){
        
        if(this.getGame().getPlayer().getCrewMember(this.getGame().getActiveCrewMember()-1) instanceof Engineer){
            return this.getGame().fixHullTracker();
        }
        
        return false;
    }
    
    @Override 
    public boolean sacrifice(){
        
        if(this.getGame().getPlayer().haveAlive_RedShirt()){
            return this.getGame().sacrificeCrewMember();
        }
        
        return false;
    }
  
    @Override
    public IStates leaveCrewPhase(){
        if(this.getGame().gameOverConditions()){
            return new GameOver(this.getGame());
        }
        
        if(!this.getGame().moveAliens())
            return this;
        
        if(this.getGame().gameOverConditions()){
            return new GameOver(this.getGame());
        }
        
        return new JourneyPhase(this.getGame());
    }
  
}
