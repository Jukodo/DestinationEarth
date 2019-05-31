package de.logic.data;

import de.DestinationEarth;
import de.logic.data.members.CrewMember;
import de.logic.states.*;
import de.logic.states.IStates;
import java.beans.PropertyChangeSupport;
import javafx.scene.paint.Color;

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
    
    public String[] getJourneyTracker(){
        System.out.println(game.getJourneyTracker()[1]);
        
        return game.getJourneyTracker();
    }
    
    
    //Methods
    public void closeWindow(){
        firePropertyChange(FPC_CLOSE_WINDOW, null, null);
    }
    
    public void swapScene(int swapTo){
        firePropertyChange(FPC_SWAP_SCENE, swapTo, null);
    }
    
    public void swapActiveCrewMember(int index){
        if(game.getActiveCrewMember()-1 == index)
            return;
        
        if(game.swapActiveCrewMember(index)){
            for(int i = 0; i < NUM_CREW_MEMBERS; i++){
                if(game.getActiveCrewMember()-1 == i){
                    firePropertyChange(FPC_CREW_TAB+i, ACTIVE, null);
                }else{
                    firePropertyChange(FPC_CREW_TAB+i, INACTIVE, null);
                }
            }
        }
    }
    
    public void swapActiveJourneyTurn(int index){
        if(game.getActiveJourneyTurn() == index)
            return;
        
        if(game.swapActiveJourneyTurn(index)){
            for(int i = 1; i <= NUM_TURNS; i++){
                if(game.getActiveJourneyTurn() == i){
                    firePropertyChange(FPC_JOURNEY_DISPLAY, i+1, ACTIVE);
                }else{
                    firePropertyChange(FPC_JOURNEY_DISPLAY, i+1, INACTIVE);
                }
            }
        }
    }
    
    public void startGame(String playerName){
        game.currentState();
        game.start(playerName);
        System.out.println("Game Started");
        game.currentState();
    }
    
    public void changeCrewMember(int type){
        game.selectCrewMember(game.getActiveCrewMember(), type);
        
        for(int i = 1; i <= CREWMEMBER_TYPES.length; i++){
            if(i == type)
                firePropertyChange(FPC_CLASS_SWAPED_LIST, i+1, ACTIVE);
            else
                firePropertyChange(FPC_CLASS_SWAPED_LIST, i+1, INACTIVE);
        }
        
        firePropertyChange(FPC_CLASS_SWAPED_INFO, type, null);
        firePropertyChange(FPC_CLASS_SWAPED_BAR+(game.getActiveCrewMember()-1), type, null);
        
        //TEST System.out.println(game.getDataGame().crewMemberInfoToString());
    }
    
    public void changeCrewMemberColor(Color color){
        game.selectCrewMemberColor(game.getActiveCrewMember(), color);
        
        firePropertyChange(FPC_COLOR_SWAPED+(game.getActiveCrewMember()-1), color, null);
    }
    
    public void lockIn(){
        IStates state = game.getState();
        
        if(state instanceof CrewSelection){
            game.confirmCrewMemberSelection();
            state = game.getState();
            if(state instanceof CrewPlacement){
                System.out.println("Changing state");
                swapScene(SCENE_CREWPLACEMENT);
                return;
            }
        }
    }
}
