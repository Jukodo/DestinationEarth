package de.logic.data.members;

import de.logic.data.DataGame;
import java.io.Serializable;

public class Doctor extends CrewMember implements Serializable{

    private boolean hasHealedForFree;
    
    public Doctor(DataGame dataGame) {
        super(dataGame, 1, 1);
        hasHealedForFree = false;
    }
    
    public Doctor(DataGame dataGame, int color) {
        super(dataGame, 1, 1, color);
        hasHealedForFree = false;
    }

    public boolean hasHealedForFree() {
        return hasHealedForFree;
    }

    public void setHasHealedForFree(boolean hasHealedForFree) {
        this.hasHealedForFree = hasHealedForFree;
    }
    
    @Override
    public String getName() {
        return "Doctor";
    }
}
