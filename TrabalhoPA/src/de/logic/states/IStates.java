/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.logic.states;

import de.logic.data.Trap;
import java.io.Serializable;

/**
 *
 * @author Tiago
 */
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
    IStates leaveCrewPhase();
    
    //RestPhase
    IStates leaveRestPhase();
    
    //CrewPhase
    IStates executeAction(int action);
    
    //Action states
    IStates moveCrewMember(int room);
    IStates attackAliens(int room);
    IStates placeTrap(int room, Trap trap);
    IStates detonateParticleDispenser(int room);
    IStates sealRoom(int room);
    
    
    //AlienPhase
    IStates moveAliens();
    
    //General
    IStates swapCrewMember();
    IStates rollDice();
    IStates setDieRoll(int dieToRoll, int value);
    int getQuantityOfDiceToRoll();
    
    
    IStates saveGame();
    IStates loadGame();
    
    IStates quit();
}
