package de.logic.data.members;

import static de.logic.data.Constants.DEF_COST_A_FIX_HULL;
import de.logic.data.DataGame;
import java.io.Serializable;

public class Engineer extends CrewMember implements Serializable{
    
    private boolean hasFixedForFree;
    
    public Engineer(DataGame dataGame) {
        super(dataGame, 1, 1);
        hasFixedForFree = false;
    }
    
    public Engineer(DataGame dataGame, int color) {
        super(dataGame, 1, 1, color);
        hasFixedForFree = false;
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
    
    @Override
    public boolean special(){
        if(this.getRoom().getName().equalsIgnoreCase("Engineering") && !this.hasFixedForFree()){
            if(!getDataGame().addHealthToHull(1))
                return false;
            this.setHasFixedForFree(true); //TODO: inicio do turno colocar a false
            getDataGame().addLog("Ship's hull was fixed by 1 health!");
            return true; 
        }
        else{
            if(getDataGame().getActionPoints() < DEF_COST_A_FIX_HULL){
                getDataGame().addLog("Not enough AP (Action Points)!");
                return false;
            }
            if(!getDataGame().addHealthToHull(1))
                return false;
            getDataGame().removeActionPoints(DEF_COST_A_FIX_HULL);
            getDataGame().addLog("Ship's hull was fixed by 1 health!");
            return true;
        }
    }
}
