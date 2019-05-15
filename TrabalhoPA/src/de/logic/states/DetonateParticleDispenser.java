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
public class DetonateParticleDispenser extends StateAdapter{
    
    public DetonateParticleDispenser(DataGame game) {
        super(game);
    }
    
    @Override
    public IStates AP_detonateParticleDispenser(int room){
        
        this.getGame().detonateParticleDispenser(room);
        
        return new CrewPhase(this.getGame());
    }
    
}
