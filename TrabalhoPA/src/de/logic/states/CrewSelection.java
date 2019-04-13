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
        
        /*try(this.getGame().selectCrewMemberColor(crewNumber, crewMemberColor)){
            
            
        }catch(ColorException e){
            
        }*/
        if(!this.getGame().selectCrewMemberColor(crewNumber, crewMemberColor)){
            //Add Log
        }
        return this;
    }
    
    @Override
    public IStates initializeCrewMemberSelection(){
        if(!this.getGame().getPlayer().hasAllMembers()){
            return this;//Add Log - Missing crew elements
        }else if(!this.getGame().crewClassNotRepeated()){
            return this;//Add Log - Crew class not unique
        }else if(!this.getGame().crewColorNotRepeated()){
            return this;//Add Log - Crew color not unique
        }else{
            return new CrewPlacement(this.getGame());
        }
    }
   //1 - Choose 2 crew members
   //2 - Choose color for each crew member
   //3 - Lock in (Go to CrewPlacement)
}
