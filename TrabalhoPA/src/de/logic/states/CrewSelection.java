package de.logic.states;

import de.logic.data.DataGame;

public class CrewSelection extends StateAdapter{
    
    public CrewSelection(DataGame game){
        super(game);
    }
    
    @Override
    public IStates selectCrewMember(int crewNumber, int crewType){
        if(!this.getGame().selectCrewMember(crewNumber, crewType)){
            //Add Log
        }
        return this;
    }
    
    @Override
    public IStates selectCrewMemberColor(int crewNumber, int crewMemberColor){
        this.getGame().selectCrewMemberColor(crewNumber, crewMemberColor);
        return this;
    }
    
    @Override
    public IStates confirmCrewMemberSelection(){
        if(!this.getGame().getPlayer().hasAllMembers()){
            return this;
        }else if(!this.getGame().crewClassNotRepeated()){
            return this;
        }else if(!this.getGame().crewColorNotRepeated()){
            return this;
        }else{
            return new CrewPlacement(this.getGame());
        }
    }
}
