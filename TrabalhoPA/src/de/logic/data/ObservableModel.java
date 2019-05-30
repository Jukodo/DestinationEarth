package de.logic.data;

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
    
    public void startGame(String playerName){
        game.currentState();
        game.start(playerName);
        System.out.println("Game Started");
        game.currentState();
    }
    
    public void changeActive(int index){
        //game.swapActiveCrewMember(index);
    }
    
    public void changeCrewMember(int index){
        //Chamar o selectCrewMember
        
        //Desenhar informações na tab do info
    }
}
