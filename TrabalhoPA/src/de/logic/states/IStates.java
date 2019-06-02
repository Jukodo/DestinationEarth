package de.logic.states;

import java.io.Serializable;
import javafx.scene.paint.Color;

public interface IStates extends Serializable {
    
    //Beginning
    IStates start(String playerName);
 
    //CrewSelection
    IStates selectCrewMember(int crewNumber, int crewType);
    IStates selectCrewMemberColor(int crewNumber, int crewMemberColor);
    IStates selectCrewMemberColor(int crewNumber, Color crewMemberColor);
    IStates confirmCrewMemberSelection();
    
    //CrewPlacement
    IStates placeCrewMember(int crewNumber, int roomNumber);
    IStates confirmCrewMemberPlacement();
    
    //JourneySelection
    boolean generateJourney_ByChoice(int turn, String choice);
    IStates generateJourney_ByRandom();
    IStates generateJourney_ByDefault();
    IStates confirmJourneySelection();
    
    //JourneyPhase
    IStates nextTurn();
    
    //ScanningPhase
    IStates scanTurn();
    IStates placeNewAlien(int alienNumber, int roomNumber);
    IStates confirmNewAliensPlacement();
    
    //RestPhase
    IStates IP_addHealthPoint();
    IStates IP_repairHull();
    IStates IP_buildOrganicDetonator();
    IStates IP_addMovement(int activeCrewMember);
    IStates IP_buildParticleDesperser();
    IStates IP_addSealedRoomToken();
    IStates IP_addAttackDie(int activeCrewMember);
    IStates IP_addValueToAttackDie();
    IStates leaveRestPhase();
    
    //CrewPhase
    IStates AP_moveCrewMember(int room);
    IStates AP_attackAliens(int room);
    IStates AP_placeTrap(int trapType);
    IStates AP_detonateParticleDispenser(int room);
    IStates AP_sealRoom(int room);
    IStates AP_healPlayer();
    IStates AP_fixHull();
    IStates leaveCrewPhase();
    
    //CrewPhase and RestPhase
    IStates sacrificeCrewMember();
    
    //AlienPhase
    IStates moveAliens();
    
    //GameOver
    IStates playAgain();
    
    //General
    int currentState();
    boolean swapActiveCrewMember();
    boolean swapActiveCrewMember(int index);
    boolean swapActiveJourneyTurn(int index);
    IStates rollDice();
    IStates setDieRoll(int dieToRoll, int value);
    IStates confirmRoll();
    int getQuantityOfDiceToRoll();
    
    
    //IStates saveGame();
    //IStates loadGame();
    
    IStates quit();
}
