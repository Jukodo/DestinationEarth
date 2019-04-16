/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.logic.states;

import de.logic.data.DataGame;
import de.logic.data.members.Doctor;
import de.logic.data.members.Engineer;

/**
 *
 * @author Tiago
 */
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
    
        if(action == 2){
            return new MoveCrewMember(this.getGame());
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
  
}
