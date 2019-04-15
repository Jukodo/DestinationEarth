package de.logic.data.members;

import de.logic.data.DataGame;

public class Doctor extends CrewMember {

    private boolean hasHealedForFree;
    
    public Doctor(DataGame dataGame) {
        super(dataGame, 1, 1);
        hasHealedForFree = false;
    }
    
    public Doctor(DataGame dataGame, int color) {
        super(dataGame, 1, 1, color);
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
