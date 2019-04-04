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
    
    IStates start();
    
    IStates setPlayerName(String name);
    
    IStates selectCrewMember(int member);
    IStates initializeCrewMembers();
    
    IStates nextTurn();
    
    IStates scanTurn();
    
    IStates spendInspirationPoints(int upgradeId);
    
    IStates spendAbilityPoints(int aiblityId);
    
    IStates moveAllAliens();
       
    IStates activateCrewMember(int member);
    IStates rollDie(IStates previousState);
    IStates setDieValue(IStates previousState);
    
    IStates saveGame();
    IStates loadGame();
    
    IStates quit();
}
