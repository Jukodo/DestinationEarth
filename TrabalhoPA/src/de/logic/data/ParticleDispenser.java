package de.logic.data;

import java.io.Serializable;

public class ParticleDispenser extends Trap implements Constants, Serializable{

    public ParticleDispenser(DataGame dataGame) {
        super(dataGame, DEF_COST_A_TRAP_PARTICLE);
    }

    @Override
    public String toString() {
        return "Particle Dispenser";
    }
    
    
}
