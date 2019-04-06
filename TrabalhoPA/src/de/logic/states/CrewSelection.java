package de.logic.states;

import de.logic.data.DataGame;
import de.logic.data.members.*;

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
    public IStates initializeCrewMembers(){
        if(this.getGame().getPlayer().hasAllMembers())
            return new CrewPlacement(this.getGame());
        else
            return this;//Add Log
    }
   //1 - Choose 2 crew members
   //2 - Choose color for each crew member
   //3 - Lock in (Go to CrewPlacement)
}
