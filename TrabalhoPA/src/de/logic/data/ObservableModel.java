package de.logic.data;

import de.DestinationEarth;
import de.logic.data.members.CrewMember;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    
    public CrewMember getCrewMember(int index){
        return game.getPlayer().getCrewMember(index);
    }
    
    public String[] getJourneyTracker(){
        return game.getJourneyTracker();
    }
    
    public String getJourneyTrackerTurn(int turn){
        return game.getJourneyTrackerTurn(turn);
    }
    
    public HashMap<Integer, Room> getRooms(){
        return game.getShip().getRooms();
    }
    
    public int getActiveCrewMember(){
        return game.getActiveCrewMember();
    }
    
    public String getPlayerName(){
        return game.getPlayer().getName();
    }
    
    public int getCurrentTurn(){
        return game.getDataGame().getCurrentTurn();
    }
    
    public int getAliensCount(){
        return game.getDataGame().getAliensCount();
    }
    
    public int getActionPoints(){
        return game.getPlayer().getActionPoints();
    }
    
    public int getInspirationPoints(){
        return game.getPlayer().getInspirationPoints();
    }
    
    public int getHullTracker(){
        return game.getShip().getHullTracker();
    }
    
    public int getHealthTracker(){
        return game.getPlayer().getHealthTracker();
    }
    
    public List<Room> getPossibleRooms(){
        return game.getPossibleRooms(game.getActiveCrewMember()-1);
    }
    
    public boolean have_CommsOfficer(){
        return game.getPlayer().have_CommsOfficer();
    }
    
    public boolean have_Doctor(){
        return game.getPlayer().have_Doctor();
    }
    
    public boolean have_Engineer(){
        return game.getPlayer().have_Engineer();
    }
    
    public boolean activeIsDoctor(){
        return game.activeIsDoctor();
    }
    
    public boolean activeIsEngineer(){
        return game.activeIsEngineer();
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
                updateActionSelection();
        
                if(currentState() == STATE_MOVE_CREW_MEMBER){
                    updatePossibleRooms(ACTIVE);
                }
            }
        }
    }
    
    public void swapActiveJourneyTurn(int index){
        if(game.getActiveJourneyTurn() == index)
            return;
        
        if(game.swapActiveJourneyTurn(index)){
            firePropertyChange(FPC_JOURNEY_TURN_UPDATE, index, null);
        }
    }
    
    public void setJourney_byChoice(String event){
        if(game.getJourneyTrackerTurn(game.getActiveJourneyTurn()).equals(event))
            return;
        
        if(game.isValid_JourneyTurn(game.getActiveJourneyTurn(), event)){
            if(game.generateJourney_ByChoice(game.getActiveJourneyTurn(), event)){
                firePropertyChange(FPC_JOURNEY_TURN_UPDATE, game.getActiveJourneyTurn(), null);
                firePropertyChange(FPC_JOURNEY_EVENTS_UPDATE, null, null);
            }
        }
    }
    
    public void setJourney_byDefault(){
        game.generateJourney_ByDefault();
        
        firePropertyChange(FPC_JOURNEY_TURN_UPDATE, game.getActiveJourneyTurn(), null);
        firePropertyChange(FPC_JOURNEY_EVENTS_UPDATE, null, null);
    }
    
    public void setJourney_byRandom(){
        game.generateJourney_ByRandom();
        
        firePropertyChange(FPC_JOURNEY_TURN_UPDATE, game.getActiveJourneyTurn(), null);
        firePropertyChange(FPC_JOURNEY_EVENTS_UPDATE, null, null);
    }
    
    public void startGame(String playerName){
        game.start(playerName);
        
        updateGameStats();
        firePropertyChange(FPC_JOURNEY_TURN_UPDATE, game.getActiveJourneyTurn(), null);
        firePropertyChange(FPC_JOURNEY_EVENTS_UPDATE, null, null);
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
        if(game.getPlayer().getCrewMember(game.getActiveCrewMember()-1) == null)
            return;
        
        game.selectCrewMemberColor(game.getActiveCrewMember(), color);
        
        firePropertyChange(FPC_COLOR_SWAPED+(game.getActiveCrewMember()-1), color, null);
    }
    
    public void placeCrewMember(int room){
        game.placeCrewMember(game.getActiveCrewMember(), room);
        
        firePropertyChange(FPC_DISPLAY_SHIP_UPDATE, null, null);
    }
    
    public void cancelAction(){
        game.cancelAction();
        
        updatePossibleRooms(INACTIVE);
        updateActionSelection();
    }
    
    public void AP_moveCrewMember(int room){
        if(game.currentState() == STATE_CREW_PHASE){
            game.AP_moveCrewMember(0);
            updatePossibleRooms(ACTIVE);
        }
        else{
            game.AP_moveCrewMember(room);
            updatePossibleRooms(INACTIVE);
        }
        
        updateGameStats();
        updateShipDisplay();
        firePropertyChange(FPC_ACTION_SELECTION_UPDATE, null, null);
    }
    
    public void AP_attackAlien(){
        System.out.println("AP_attackAlien");
        //game.AP_attackAliens();
    }
    
    public void AP_placeTrap(){
        System.out.println("AP_placeTrap");
        //game.AP_placeTrap();
    }
    
    public void AP_detonateParticleDispenser(){
        System.out.println("AP_detonateParticleDispenser");
        //game.AP_detonateParticleDispenser();
    }
    
    public void AP_sealRoom(){
        System.out.println("AP_sealRoom");
        //game.AP_sealRoom();
    }
    
    public void AP_healPlayer(){
        game.AP_healPlayer();
        
        updateActionSelection();
        updateGameStats();
    }
    
    public void AP_fixHull(){
        game.AP_fixHull();
        
        updateActionSelection();
        updateGameStats();
    }
    
    public void updateGameStats(){
        firePropertyChange(FPC_GAME_STATS_UPDATE, null, null);
    }
    
    public void updateActionSelection(){
        firePropertyChange(FPC_ACTION_SELECTION_UPDATE, null, null);
    }
    
    public void updatePossibleRooms(int toState){
        firePropertyChange(FPC_DISPLAY_POSSIBLE_ROOMS, toState, null);
    }
    
    public void updateShipDisplay(){
        firePropertyChange(FPC_DISPLAY_SHIP_UPDATE, null, null);
    }
    
    public void lockIn(){
        int state = game.currentState();
        
        switch(state){
            case STATE_CREW_SELECTION:
                game.confirmCrewMemberSelection();
                state = game.currentState();
                if(state == STATE_CREW_PLACEMENT)
                    swapScene(SCENE_CREW_PLACEMENT);
                break;
            case STATE_CREW_PLACEMENT:
                game.confirmCrewMemberPlacement();
                state = game.currentState();
                if(state == STATE_JOURNEY_SELECTION)
                    swapScene(SCENE_JOURNEY_SELECTION);
                break;
            case STATE_JOURNEY_SELECTION:
                game.confirmJourneySelection();
                state = game.currentState();
                if(state == STATE_JOURNEY_PHASE){
                    executeEndOf_JourneySelection();
                    swapScene(SCENE_JOURNEY_PHASE);
                }
                break;
            case STATE_JOURNEY_PHASE:
                game.nextTurn();
                executeScanningPhase();
                executeUpdateJourneyDisplay();
                state = game.currentState();
                updateGameStats();
                if(state == STATE_REST_PHASE)
                    swapScene(SCENE_REST_PHASE);
                else if(state == STATE_CREW_PHASE){
                    updateActionSelection();
                    swapScene(SCENE_CREW_PHASE);
                }
                break;
            case STATE_REST_PHASE:
                game.leaveRestPhase();
                state = game.currentState();
                if(state == STATE_JOURNEY_PHASE){
                    updateGameStats();
                    executeAlienPhase();
                    swapScene(SCENE_JOURNEY_PHASE);
                }
                break;
            case STATE_CREW_PHASE:
                game.leaveCrewPhase();
                state = game.currentState();
                if(state == STATE_JOURNEY_PHASE){
                    updateGameStats();
                    executeAlienPhase();
                    swapScene(SCENE_ALIEN_PHASE);
                }
                break;
            default:
                System.out.println("locked in on an unknown state");
                break;
        }
        System.out.println(STATES[game.currentState()]);
    }
    
    private void executeEndOf_JourneySelection(){
        firePropertyChange(FPC_GAME_STARTED, null, null);
    }
    
    private void executeScanningPhase(){
        game.scanTurn();
        game.confirmNewAliensPlacement();

        firePropertyChange(FPC_DISPLAY_SHIP_UPDATE, null, null);
    }
    
    private void executeAlienPhase(){
        firePropertyChange(FPC_DISPLAY_SHIP_UPDATE, null, null);
        updateGameStats();
    }
    
    private void executeUpdateJourneyDisplay(){
        firePropertyChange(FPC_JOURNEY_TURN_UPDATE, game.getDataGame().getCurrentTurn()+1, null);
    }

    //REMOVE LATER
    public int currentState(){
        return game.currentState();
    }
}
