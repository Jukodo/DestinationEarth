package de.logic.states;

import de.logic.data.DataGame;

public class CrewSelection extends StateAdapter{
    
    public CrewSelection(DataGame game){
        super(game);
    }
    
    /**
     *
     * @param member
     * @return
     */
    @Override
    public IStates selectCrewMember(int member){
       
       return this;
   }
   //1 - Choose 2 crew members
   //2 - Choose color for each crew member
   //3 - Choose room for each crew member (dice or choose)
   //4 - Lock in
}
