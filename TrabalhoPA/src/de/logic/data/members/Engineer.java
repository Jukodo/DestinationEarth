package de.logic.data.members;

import de.logic.data.DataGame;

public class Engineer extends CrewMember{
    
    private boolean hasFixedForFree;
    
    public Engineer(DataGame dataGame) {
        super(dataGame, 1, 1);
        hasFixedForFree = false;
    }
    
    public Engineer(DataGame dataGame, int color) {
        super(dataGame, 1, 1, color);
    }
    
    public boolean hasFixedForFree(){
        return hasFixedForFree;
    }
    
    public void setHasFixedForFree(boolean value){
        hasFixedForFree = value;
    }

    @Override
    public String getName() {
        return "Engineer";
    }
}
