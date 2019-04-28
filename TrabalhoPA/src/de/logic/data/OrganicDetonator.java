package de.logic.data;

import java.io.Serializable;

public class OrganicDetonator extends Trap implements Constants, Serializable{

    public OrganicDetonator(DataGame dataGame) {
        super(dataGame, DEF_COST_A_TRAP_PARTICLE);
    }

    @Override
    public String toString() {
        return "Organic Detonator";
    }
    
    
    
}
