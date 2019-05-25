package de.ui.gui;

import de.DestinationEarth;
import de.logic.data.Constants;
import de.logic.data.members.CrewMember;
import java.beans.PropertyChangeSupport;

public class ObservableModel extends PropertyChangeSupport implements Constants{
    
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
    public void closeWindow(){
        firePropertyChange(FPC_CLOSE_WINDOW, null, null);
    }
    
    public void swapScene(int swapTo){
        firePropertyChange(FPC_SWAP_SCENE, swapTo, null);
    }
    
    public void startGame(String playerName){
        //game.start(playerName);
    }
    
    public void changeActive(int index){
        //game.swapActiveCrewMember(index);
    }
    
    public void changeCrewMember(int index){
        //Chamar o selectCrewMember
        
        //Desenhar informações na tab do info
    }
}
