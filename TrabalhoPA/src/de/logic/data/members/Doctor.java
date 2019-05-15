package de.logic.data.members;

import static de.logic.data.Constants.DEF_COST_A_HEAL;
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
    
    @Override
    public boolean special(){
        
        if(this.getRoom().getName().equalsIgnoreCase("Sick Bay") && !this.hasHealedForFree()){
            
                if(!getDataGame().addHealthToPlayer(1))
                    return false;
                
                this.setHasHealedForFree(true);
                
                getDataGame().addLog("Player was healed by 1 health!");
                return true;
            }else{
                if(getDataGame().getActionPoints() < DEF_COST_A_HEAL){
                    getDataGame().addLog("Not enough AP (Action Points)!");
                    return false;
                }
                
                if(!getDataGame().addHealthToPlayer(1))
                    return false;

                getDataGame().removeActionPoints(DEF_COST_A_HEAL);
                
                getDataGame().addLog("Player was healed by 1 health!");
                return true;
            }
    }
}
