package de.logic.data;

import java.io.Serializable;

public class OrganicDetonator extends Trap implements Constants, Serializable{

    public OrganicDetonator(DataGame dataGame, Room room) {
        super(dataGame, DEF_COST_A_TRAP_PARTICLE, room);
    }

    @Override
    public String toString() {
        return "Organic Detonator";
    }   
    
}
