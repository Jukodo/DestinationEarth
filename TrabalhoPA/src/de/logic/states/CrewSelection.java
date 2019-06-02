package de.logic.states;

import de.logic.data.DataGame;
import javafx.scene.paint.Color;

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
    public IStates selectCrewMemberColor(int crewNumber, Color crewMemberColor){
        this.getGame().selectCrewMemberColor(crewNumber, crewMemberColor);
        return this;
    }
    
    @Override
    public IStates confirmCrewMemberSelection(){
        if(!this.getGame().getPlayer().hasAllMembers()){
            System.out.println("!hasAllMembers");
            return this;
        }else if(!this.getGame().crewClassNotRepeated()){
            System.out.println("!crewClassNotRepeated");
            return this;
        }else if(!this.getGame().crewColorNotRepeated()){
            System.out.println("!crewColorNotRepeated");
            return this;
        }else{
            System.out.println("Swapping to CP");
            return new CrewPlacement(this.getGame());
        }
    }
    
    @Override
    public int currentState(){
        return STATE_CREW_SELECTION;
    }
}
