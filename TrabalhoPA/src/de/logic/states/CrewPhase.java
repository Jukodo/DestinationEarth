package de.logic.states;

import de.logic.data.DataGame;

public class CrewPhase extends StateAdapter{
 
    public CrewPhase(DataGame game) {
        super(game);
    }
    
    @Override 
    public IStates AP_moveCrewMember(int room){
        return new MoveCrewMember(this.getGame());
    }
    
    @Override 
    public IStates AP_attackAliens(int room){
        return new DiceRolling(this.getGame(), new AttackAliens(this.getGame()), this.getGame().getPlayer().getCrewMember(this.getGame().getActiveCrewMember()-1).getAttack());
    }
    
    @Override 
    public IStates AP_placeTrap(int typeTrap){
        return new PlaceTrap(this.getGame());
    }
    
    @Override 
    public IStates AP_detonateParticleDispenser(int room){
        return new DetonateParticleDispenser(this.getGame());
    }
    
    @Override 
    public IStates AP_sealRoom(int room){
        return new SealRoom(this.getGame());
    }
    
    @Override 
    public IStates AP_healPlayer(){
        
        if(this.getGame().activeIsDoctor())
            this.getGame().healPlayer();
            
        return this;
    }
    
    @Override 
    public IStates AP_fixHull(){
        
        if(this.getGame().activeIsEngineer())
            this.getGame().fixHullTracker();
        
        return this;
    }
    
    @Override 
    public IStates sacrificeCrewMember(){
        
        if(this.getGame().getPlayer().haveAlive_RedShirt())
            this.getGame().sacrificeCrewMember();
        
        return this;
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
    
    @Override
    public void currentState(){
        System.out.println("CrewPhase");
    }
  
}
