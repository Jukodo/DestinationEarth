package de.logic.states;

import de.logic.data.DataGame;
import de.logic.data.members.*;

public class CrewSelection extends StateAdapter{
    
    public CrewSelection(DataGame game){
        super(game);
    }
    
    @Override
    public IStates selectCrewMember(int crewNumber, int crewType){
        switch(crewType){
            case 1:
                this.getGame().getPlayer().setCrewMember(crewNumber, new Captain(this.getGame()));
                break;
            case 2:
                this.getGame().getPlayer().setCrewMember(crewNumber, new Commander(this.getGame()));
                break;
            case 3:
                this.getGame().getPlayer().setCrewMember(crewNumber, new CommsOfficer(this.getGame()));
                break;
            case 4:
                this.getGame().getPlayer().setCrewMember(crewNumber, new Doctor(this.getGame()));
                break;
            case 5:
                this.getGame().getPlayer().setCrewMember(crewNumber, new Engineer(this.getGame()));
                break;
            case 6:
                this.getGame().getPlayer().setCrewMember(crewNumber, new MoralOfficer(this.getGame()));
                break;
            case 7:
                this.getGame().getPlayer().setCrewMember(crewNumber, new NavigationOfficer(this.getGame()));
                break;
            case 8:
                this.getGame().getPlayer().setCrewMember(crewNumber, new RedShirt(this.getGame()));
                break;
            case 9:
                this.getGame().getPlayer().setCrewMember(crewNumber, new ScienceOfficer(this.getGame()));
                break;
            case 10:
                this.getGame().getPlayer().setCrewMember(crewNumber, new SecurityOfficer(this.getGame()));
                break;
            case 11:
                this.getGame().getPlayer().setCrewMember(crewNumber, new ShuttlePilot(this.getGame()));
                break;
            case 12:
                this.getGame().getPlayer().setCrewMember(crewNumber, new TransporterChief(this.getGame()));
                break;
            default:
                //Add Log
                return this;
        }
        return this;
    }
    
    @Override
    public IStates selectCrewMemberColor(int crewNumber, int crewMemberColor){
        if(crewMemberColor < 0 || crewMemberColor > 11)
            return this;//Add Log
        this.getGame().getPlayer().getCrewMember(crewNumber).setColor(crewMemberColor);
        return this;
    }
    
    @Override
    public IStates initializeCrewMembers(){
        
    }
   //1 - Choose 2 crew members
   //2 - Choose color for each crew member
   //3 - Lock in
}
