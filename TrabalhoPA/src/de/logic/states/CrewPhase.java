/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.logic.states;

import de.logic.data.DataGame;

/**
 *
 * @author Tiago
 */
public class CrewPhase extends StateAdapter{
    //1 - check AP
    //2 - moveMember, atackAlien, healMember, fixHull, setTrap, detonateParticleDispenser, sealRoom
    //3 - rollDice
    //4 - nextPhase

    public CrewPhase(DataGame game) {
        super(game);
    }
  
}
