package de.logic.data;

import de.DestinationEarth;
import de.logic.data.members.CrewMember;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ObservableModel extends PropertyChangeSupport implements Constants{
    
    private DestinationEarth game;
    private Stage mainWindow;
    
    public ObservableModel(DestinationEarth game, Stage mainWindow){
        super(game);
        this.game = game;
        this.mainWindow = mainWindow;
    }
    
    public ObservableModel(DestinationEarth game){
        super(game);
        this.game = game;
        this.mainWindow = null;
    }
    
    //Getters
    public Stage getMainWindow(){
        return mainWindow;
    }
    
    public CrewMember getCrewMember(int index){
        return game.getCrewMember(index);
    }
    
    public String[] getJourneyTracker(){
        return game.getJourneyTracker();
    }
    
    public String getJourneyTrackerTurn(int turn){
        return game.getJourneyTrackerTurn(turn);
    }
    
    public HashMap<Integer, Room> getRooms(){
        return game.getRooms();
    }
    
    public int getActiveCrewMember(){
        return game.getActiveCrewMember();
    }
    
    public String getPlayerName(){
        return game.getPlayerName();
    }
    
    public int getCurrentTurn(){
        return game.getCurrentTurn();
    }
    
    public int getAliensCount(){
        return game.getAliensCount();
    }
    
    public int getActionPoints(){
        return game.getActionPoints();
    }
    
    public int getInspirationPoints(){
        return game.getInspirationPoints();
    }
    
    public int getHullTracker(){
        return game.getHullTracker();
    }
    
    public int getHealthTracker(){
        return game.getHealthTracker();
    }
    
    public List<Room> getPossibleRooms(){
        return game.getPossibleRooms(game.getActiveCrewMember()-1);
    }
    
     public List<Room> getRooms_ToAttack(){
        return game.getRooms_ToAttack(game.getActiveCrewMember()-1);
    }
     
     public List<Room> getRooms_ToPlaceTrap(){
        return game.getRooms_ToPlaceTrap(game.getActiveCrewMember()-1);
    }
    
    public boolean have_RedShirt(boolean alive){
        return game.have_RedShirt(false, alive);
    }
    
    public boolean have_CommsOfficer(boolean active){
        return game.have_CommsOfficer(active);
    }
    
    public boolean have_Doctor(boolean active){
        return game.have_Doctor(active);
    }
    
    public boolean have_Engineer(boolean active){
        return game.have_Engineer(active);
    }
    
    //Methods
    public void closeWindow(){
        firePropertyChange(FPC_CLOSE_WINDOW, null, null);
    }
    
    public void swapScene(int swapTo){
        firePropertyChange(FPC_SWAP_SCENE, swapTo, null);
    }

    public List<String> getLogs(){
        if(game.getLogs().size() > 0){
            return game.getLogs();
        }
        return null;
    }
    
    public void showLogs(){
        if(getLogs() != null){
            firePropertyChange(FPC_SHOW_LOGS, null, null);
        }
    }
    
    public void clearLogs(){
        game.clearLogs();
    }
    
    public void saveGame(File saveFile){
        game.saveGame(saveFile);
    }
    
    public void loadGame(File loadFile){
        game = game.loadGame(loadFile);
        
        updateGame();
    }
    
    public void replayGame(){
        game.resetTotalRooms();
        game = new DestinationEarth();

        game.playAgain();
        
        firePropertyChange(FPC_REPLAY_GAME, null, null);
        swapScene(SCENE_BEGINNING);
    }
    
    public void rollDice(){
        game.rollDice();
    }
    
    public void swapActiveCrewMember(int index){
        if(game.getActiveCrewMember()-1 == index)
            return;
        
        if(game.swapActiveCrewMember(index)){
            updateMemberBar();
            updateActionSelection();

            if(currentState() == STATE_MOVE_CREW_MEMBER){
                updatePossibleRooms(ACTIVE, AP_MOVE);
            }
            
            if(currentState() == STATE_ATTACK_ALIENS){
                updatePossibleRooms(ACTIVE, AP_ATTACK);
            }
        }
        showLogs();
    }
    
    public void swapActiveJourneyTurn(int index){
        if(game.getActiveJourneyTurn() == index)
            return;
        
        if(game.swapActiveJourneyTurn(index)){
            updateJourneyTurn(index);
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
        showLogs();
    }
    
    public void setJourney_byDefault(){
        game.generateJourney_ByDefault();
        
        firePropertyChange(FPC_JOURNEY_TURN_UPDATE, game.getActiveJourneyTurn(), null);
        firePropertyChange(FPC_JOURNEY_EVENTS_UPDATE, null, null);
        showLogs();
    }
    
    public void setJourney_byRandom(){
        game.generateJourney_ByRandom();
        
        firePropertyChange(FPC_JOURNEY_TURN_UPDATE, game.getActiveJourneyTurn(), null);
        firePropertyChange(FPC_JOURNEY_EVENTS_UPDATE, null, null);
        showLogs();
    }
    
    public void startGame(String playerName){
        game.start(playerName);
        
        updateGameStats();
        firePropertyChange(FPC_JOURNEY_TURN_UPDATE, game.getActiveJourneyTurn(), null);
        firePropertyChange(FPC_JOURNEY_EVENTS_UPDATE, null, null);
        showLogs();
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
        showLogs();
    }
    
    public void changeCrewMemberColor(Color color){
        if(getCrewMember(game.getActiveCrewMember()-1) == null)
            return;
        
        game.selectCrewMemberColor(game.getActiveCrewMember(), color);
        
        firePropertyChange(FPC_COLOR_SWAPED+(game.getActiveCrewMember()-1), color, null);
        showLogs();
    }
    
    public void placeCrewMember(int room){
        game.placeCrewMember(game.getActiveCrewMember(), room);
        
        updateShipDisplay();
        showLogs();
    }
    
    public void cancelAction(){
        game.cancelAction();
        
        updatePossibleRooms(INACTIVE, 0);
        updateActionSelection();
    }
    
    public int getOrganicTrapTokens(){
        return game.getOrganicTrapTokens();
    }
    
    public int getParticleTrapTokens(){
        return game.getParticleTrapTokens();
    }
    
    public void AP_moveCrewMember(int room){
        if(game.currentState() == STATE_CREW_PHASE){
            game.AP_moveCrewMember(0);
            updatePossibleRooms(ACTIVE, AP_MOVE);
        }
        else{
            game.AP_moveCrewMember(room);
            updatePossibleRooms(INACTIVE, 0);
        }
        
        updateGameStats();
        updateShipDisplay();
        firePropertyChange(FPC_ACTION_SELECTION_UPDATE, null, null);
        showLogs();
    }
    
    public void AP_attackAlien(int room){
        System.out.println("AP_attackAlien");
        
        if(game.currentState() == STATE_CREW_PHASE){
            game.AP_attackAliens(0);
            this.rollDice();
            updatePossibleRooms(ACTIVE, AP_ATTACK);
        }
        else{
            game.AP_attackAliens(room);
            updatePossibleRooms(INACTIVE, 0);
        }
        
        updateGameStats();
        updateShipDisplay();
        firePropertyChange(FPC_ACTION_SELECTION_UPDATE, null, null);
        showLogs();
    }
    
    public void AP_placeTrap(int trapType){
        System.out.println("AP_placeTrap");
        
        if(game.currentState() == STATE_CREW_PHASE){
            game.AP_placeTrap(0);
            updatePossibleRooms(ACTIVE, AP_PLACETRAP);
        }
        else{
            game.AP_placeTrap(trapType);
            updatePossibleRooms(INACTIVE, 0);
        }
        
        updateGameStats();
        updateShipDisplay();
        firePropertyChange(FPC_ACTION_SELECTION_UPDATE, null, null);
    }
    
    public void AP_detonateParticleDispenser(){
        System.out.println("AP_detonateParticleDispenser");
        //game.AP_detonateParticleDispenser();
        showLogs();
    }
    
    public void AP_sealRoom(){
        System.out.println("AP_sealRoom");
        //game.AP_sealRoom();
        showLogs();
    }
    
    public void AP_healPlayer(){
        game.AP_healPlayer();
        
        updateActionSelection();
        updateGameStats();
        showLogs();
    }
    
    public void AP_fixHull(){
        game.AP_fixHull();
        
        updateActionSelection();
        updateGameStats();
        showLogs();
    }
    
    public void IP_addHealthPoint(){
        game.IP_addHealthPoint();
        
        updateInspirationSelection();
        updateGameStats();
        showLogs();
    }
    
    public void IP_repairHull(){
        game.IP_repairHull();
        
        updateInspirationSelection();
        updateGameStats();
        showLogs();
    }
    
    public void IP_buildOrganicDetonator(){
        game.IP_buildOrganicDetonator();
        
        updateInspirationSelection();
        updateGameStats();
        showLogs();
    }
    
    public void IP_addMovement(){
        game.IP_addMovement();
        
        updateInspirationSelection();
        updateGameStats();
        showLogs();
    }
    
    public void IP_buildParticleDesperser(){
        game.IP_buildParticleDesperser();
        
        updateInspirationSelection();
        updateGameStats();
        showLogs();
    }
    
    public void IP_addSealedRoomToken(){
        game.IP_addSealedRoomToken();
        
        updateInspirationSelection();
        updateGameStats();
        showLogs();
    }
    
    public void IP_addAttackDie(){
        game.IP_addAttackDie();
        
        updateInspirationSelection();
        updateGameStats();
        showLogs();
    }
    
    public void IP_addValueToAttackDie(){
        game.IP_addValueToAttackDie();
        
        updateInspirationSelection();
        updateGameStats();
        showLogs();
    }
    
    public void sacrificeCrewMember(){
        if(!game.have_RedShirt(false, true))
            return;
        
        game.sacrificeCrewMember();
        
        updateMemberBar();
        updateInspirationSelection();
        updateGameStats();
        showLogs();
    }
    
    public void updateGame(){
        switch(game.currentState()){
            case STATE_BEGINNING:
                swapScene(SCENE_BEGINNING);
                break;
            case STATE_CREW_SELECTION:
                swapScene(SCENE_CREW_SELECTION);
                break;
            case STATE_CREW_PLACEMENT:
                swapScene(SCENE_CREW_PLACEMENT);
                break;
            case STATE_JOURNEY_SELECTION:
                swapScene(SCENE_JOURNEY_SELECTION);
                break;
            case STATE_JOURNEY_PHASE:
                swapScene(SCENE_JOURNEY_PHASE);
                break;
            case STATE_REST_PHASE:
                swapScene(SCENE_REST_PHASE);
                break;
            case STATE_CREW_PHASE:
                swapScene(SCENE_CREW_PHASE);
                break;
        }
        
        updateActionSelection();
        updateGameStats();
        updateInspirationSelection();
        updateMemberBar();
        updatePossibleRooms(INACTIVE, UNKNOWN);
        updateShipDisplay();
        showLogs();
    }
    
    public void updateMemberBar(){
        for(int i = 0; i < NUM_CREW_MEMBERS; i++){
            if(game.getActiveCrewMember()-1 == i){
                firePropertyChange(FPC_CREW_TAB+i, ACTIVE, null);
            }else{
                firePropertyChange(FPC_CREW_TAB+i, INACTIVE, null);
            }
        }
    }
    
    public void updateGameStats(){
        firePropertyChange(FPC_GAME_STATS_UPDATE, null, null);
    }
    
    public void updateJourneyTurn(int index){
        firePropertyChange(FPC_JOURNEY_TURN_UPDATE, index, null);
        firePropertyChange(FPC_JOURNEY_DISPLAY_UPDATE, index, null);
    }
    
    public void updateJourneyDisplay(){
        firePropertyChange(FPC_JOURNEY_DISPLAY_UPDATE, game.getCurrentTurn(), null);
    }
    
    public void updateActionSelection(){
        firePropertyChange(FPC_ACTION_SELECTION_UPDATE, null, null);
    }
    
    public void updateInspirationSelection(){
        firePropertyChange(FPC_INSPIRATION_SELECTION_UPDATE, null, null);
    }
    
    public void updatePossibleRooms(int toState, int action){
        firePropertyChange(FPC_DISPLAY_POSSIBLE_ROOMS, toState, action);
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
                updateJourneyDisplay();
                state = game.currentState();
                updateGameStats();
                if(state == STATE_REST_PHASE){
                    updateInspirationSelection();
                    swapScene(SCENE_REST_PHASE);
                }else if(state == STATE_CREW_PHASE){
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
                    swapScene(SCENE_JOURNEY_PHASE);
                }else if(state == STATE_GAME_OVER){
                    updateGameStats();
                    swapScene(SCENE_GAME_OVER);
                }
                break;
            default:
                System.out.println("locked in on an unknown state");
                break;
        }
        System.out.println(STATES[game.currentState()]);
        showLogs();
    }
    
    private void executeEndOf_JourneySelection(){
        updateJourneyDisplay();
        
        firePropertyChange(FPC_GAME_STARTED, null, null);
    }
    
    private void executeScanningPhase(){
        game.scanTurn();
        game.confirmNewAliensPlacement();

        updateShipDisplay();
    }
    
    private void executeAlienPhase(){
        updateShipDisplay();
    }

    //REMOVE LATER
    public int currentState(){
        return game.currentState();
    }
}
