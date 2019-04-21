package de.logic.data;

import java.io.Serializable;

public class ParticleDispenser extends Trap implements Constants, Serializable{

    public ParticleDispenser(DataGame dataGame) {
        super(dataGame, DEF_COST_TRAP_PARTICLE);
    }

    @Override
    public void activate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "Particle Dispenser";
    }
    
    
}
