package de.logic.states;

import de.logic.data.Trap;
import java.io.Serializable;

public interface IStates extends Serializable {
    
    //Beginning
    IStates start(String playerName);
 
    //CrewSelection
    IStates selectCrewMember(int crewNumber, int crewType);
    IStates selectCrewMemberColor(int crewNumber, int crewMemberColor);
    IStates confirmCrewMemberSelection();
    
    //CrewPlacement
    IStates placeCrewMember(int crewNumber, int roomNumber);
    IStates confirmCrewMemberPlacement();
    
    //JourneySelection
    IStates generateJourney_ByChoice(int turn, String choice);
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
    IStates AP_placeTrap(Trap trap);
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
    IStates swapActiveCrewMember();
    IStates rollDice();
    IStates setDieRoll(int dieToRoll, int value);
    IStates confirmRoll();
    int getQuantityOfDiceToRoll();
    
    
    //IStates saveGame();
    //IStates loadGame();
    
    IStates quit();
}
