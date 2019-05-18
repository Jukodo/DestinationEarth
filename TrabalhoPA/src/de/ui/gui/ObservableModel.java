package de.ui.gui;

import de.DestinationEarth;
import de.logic.data.members.CrewMember;
import java.beans.PropertyChangeSupport;

public class ObservableModel extends PropertyChangeSupport{
    
    private DestinationEarth game;
    
    public ObservableModel(DestinationEarth game){
        super(game);
        this.game = game;
    }
    
    //Getters
    public CrewMember[] getCrewMembers(){
        return game.getPlayer().getCrew();
    }
    
    
    //Methods
    public void changeActive(int index){
        game.swapActiveCrewMember(index);
    }
    
    public void changeCrewMember(int index){
        //Chamar o selectCrewMember
        
        //Desenhar informações na tab do info
    }
}
