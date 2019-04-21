package de.logic.data;

import java.io.Serializable;

public class OrganicDetonator extends Trap implements Constants, Serializable{

    public OrganicDetonator(DataGame dataGame) {
        super(dataGame, DEF_COST_TRAP_ORGANIC);
    }

    @Override
    public void activate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "Organic Detonator";
    }
    
    
    
}
