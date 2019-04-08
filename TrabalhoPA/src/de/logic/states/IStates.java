/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.logic.states;

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
    IStates initializeCrewMembers();
    
    //CrewPlacement
    IStates placeCrewMember(int crewNumber, int roomNumber);
    
    //JourneyPhase
    IStates nextTurn();
    
    //ScanningPhase
    IStates scanTurn();
    
    //RestPhase
    IStates spendInspirationPoints();
    
    //CrewPhase
    IStates spendAbilityPoints();
    
    //AlienPhase
    IStates moveAllAliens();
    
    //General
    IStates swapCrewMember();
    IStates rollDice();
    IStates setDieRoll(int dieToRoll, int value);
    int getQuantityOfDiceToRoll();
    
    
    IStates saveGame();
    IStates loadGame();
    
    IStates quit();
}
