package de.logic.states;

import de.logic.data.DataGame;
import de.logic.data.members.*;

public class CrewPlacement extends StateAdapter{
    
    public CrewPlacement(DataGame game){
        super(game);
    }
    
    @Override
    public IStates placeCrewMember(int crewNumber, int roomNumber){
        
        if(roomNumber < 1 || roomNumber > 12)
            return this;
        
        this.getGame().getShip().getRoom(roomNumber).setMemberInside(this.getGame().getPlayer().getCrewMember(crewNumber));
        
        if(this.getGame().getPlayer().hasAllMembersOnBoard())
            return new JourneyPhase(this.getGame());
        else
            return this;
    }
}