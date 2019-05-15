package de.logic.data.members;

import static de.logic.data.Constants.DEF_COST_A_MOVE;
import static de.logic.data.Constants.NUM_ROOMS;
import de.logic.data.DataGame;
import de.logic.data.Room;
import java.io.Serializable;

public class TransporterChief extends CrewMember implements Serializable{
    
    public TransporterChief(DataGame dataGame) {
        super(dataGame, 0, 1);
    }
    
    public TransporterChief(DataGame dataGame, int color) {
        super(dataGame, 0, 1, color);
    }

    @Override
    public String getName() {
        return "TransporterChief";
    }
    
    @Override
    public boolean move(int roomNumber){
     int cost = getDataGame().getMovementCost();
        
        if(roomNumber < 1 || roomNumber > NUM_ROOMS){
            getDataGame().addLog("Room selected doesn't exist!");
            return false;
        }
        
        if(cost > 0 && getDataGame().getActionPoints() < DEF_COST_A_MOVE){
            getDataGame().addLog("Not enough AP (Action Points)!");
            return false;
        }
        
        Room roomToMove = null;
        
        roomToMove = getDataGame().getShip().getRoom(roomNumber);
        
        if(roomToMove == null || roomToMove.getIsSealed()){
            getDataGame().addLog("Cannot move to selected Room! Please check if sealed or too far...");
            return false;
        }
        
        int freeMoves = this.getMovement() - DEF_COST_A_MOVE;
        
        if(getDataGame().getMovementCost() > 0)
            getDataGame().removeActionPoints(getDataGame().getMovementCost());
        
        this.setMovementsBeforeFree(this.getMovementsBeforeFree() + 1);
        
        if(this.getMovementsBeforeFree() > freeMoves){
            this.setMovementsBeforeFree(0);
        }
  
        this.enterRoom(roomToMove);
        
        getDataGame().addLog(this.getName() + " moved to " + roomToMove.toString());
        
        
        return true;
    }
}
