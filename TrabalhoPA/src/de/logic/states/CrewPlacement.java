package de.logic.states;

import de.logic.data.DataGame;

public class CrewPlacement extends StateAdapter{
    
    public CrewPlacement(DataGame game){
        super(game);
    }
    
    @Override
    public IStates rollDice(){
        return new DiceRolling(this.getGame(), this, 2);
    }
    
    @Override
    public IStates placeCrewMember(int crewNumber, int roomNumber){
        this.getGame().placeCrewMember(crewNumber, roomNumber);
        
        return this;
    }
    
    @Override
    public IStates initializeCrewMemberPlacement(){
        if(this.getGame().getPlayer().hasAllMembersOnBoard())
            return new JourneyPhase(this.getGame());
        else
            return this;//Add Log - Missing crew member(s) location(s)
    }
}