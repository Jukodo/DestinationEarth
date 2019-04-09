package de.logic.states;

import de.logic.data.DataGame;
import de.logic.data.members.*;

public class CrewPlacement extends StateAdapter{
    
    public CrewPlacement(DataGame game){
        super(game);
    }
    
    @Override
    public IStates rollDice(){
        return new DiceRolling(this.getGame(), this,2);
    }
    
    @Override
    public IStates placeCrewMember(int crewNumber, int roomNumber){
        
        if(!this.getGame().placeCrewMember(crewNumber, roomNumber))
            return this;//Couldnt place crewMember
        
        if(this.getGame().getPlayer().hasAllMembersOnBoard())
            return new JourneyPhase(this.getGame());
        else
            return this;//Add Log - Missing crew member(s) location(s)
    }
}