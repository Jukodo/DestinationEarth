package de.logic.states;

import de.logic.data.DataGame;

public class MoveCrewMember extends StateAdapter{
        
    public MoveCrewMember(DataGame game) {
        super(game);
    }
 
    @Override
    public IStates AP_moveCrewMember(int roomToMove){
        this.getGame().moveActiveCrewMember(roomToMove);
        return new CrewPhase(this.getGame());
    }
    
}
