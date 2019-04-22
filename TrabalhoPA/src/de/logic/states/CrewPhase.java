package de.logic.states;

import de.logic.data.DataGame;
import de.logic.data.members.Doctor;
import de.logic.data.members.Engineer;
import de.logic.data.members.ScienceOfficer;

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
    public IStates executeAction(int action){
        
        if(this.getGame().gameOverConditions()){
            return new GameOver(this.getGame());
        }
    
        if(action == 2){
            return new MoveCrewMember(this.getGame());
        }
        else if (action == 4){
            return new PlaceTrap(this.getGame());
        }
        else if(action == 3){
            return new DiceRolling(this.getGame(), new AttackAliens(this.getGame()), this.getGame().getPlayer().getCrewMember(this.getGame().getActiveCrewMember()-1).getAttack());
        }
        else if(action == 5){
            return new DetonateParticleDispenser(this.getGame());
        }
        else if(action == 6){
            return new SealRoom(this.getGame());
        }
        else if(action == 7){
            if(this.getGame().getPlayer().getCrewMember(this.getGame().getActiveCrewMember()-1) instanceof Doctor){
                this.getGame().healPlayer();
            }
            else if(this.getGame().getPlayer().getCrewMember(this.getGame().getActiveCrewMember()-1) instanceof Engineer){
                this.getGame().fixHullTracker();
            }
        }
        
        return this;
    }
    
    @Override
    public IStates leaveCrewPhase(){
        
        if(this.getGame().gameOverConditions()){
            return new GameOver(this.getGame());
        }
        
        return new AlienPhase(this.getGame());
    }
  
}
