package de.logic.data.members;

import static de.logic.data.Constants.CAPTAIN_ROLL_ATTACK;
import static de.logic.data.Constants.DEF_COST_A_ATTACK;
import static de.logic.data.Constants.MIN_ROLL_ATTACK;
import de.logic.data.DataGame;
import de.logic.data.Room;
import java.io.Serializable;

public class Captain extends CrewMember implements Serializable{
    
    public Captain(DataGame dataGame) {
        super(dataGame, 1, 1);
    }
    
    public Captain(DataGame dataGame, int color) {
        super(dataGame, 1, 1, color);
    }

    @Override
    public String getName() {
        return "Captain";
    }
    
    @Override
    public int attack(int roomNumber){
        
        if(getDataGame().getActionPoints() < DEF_COST_A_ATTACK)
            return 0;
        
        Room roomToAttack = null;
        
        roomToAttack = getDataGame().getShip().getRoom(this.getRoom().getId());
        
        if(roomToAttack == null){
            getDataGame().addLog("Cannot attack selected Room! Please check if sealed or too far...");
            return 0;
        }
        
        if(roomToAttack.getAliensInside().size() < 1){
            getDataGame().addLog("There aren't any aliens in the room to attack!");
            return 0;
        }
    
        int totalKills = 0;
        
        getDataGame().removeActionPoints(1);
        
        for(int i = 0; i < this.getAttack(); i++){
            //Se o roll for 5+
            if(getDataGame().getDices()[i] >= CAPTAIN_ROLL_ATTACK - getDataGame().getPlayer().getAttackBuff()){
                if(roomToAttack.removeRandomAlienFromRoom()){
                    getDataGame().decreaseAliensCount();
                    totalKills++;
                    getDataGame().addInspirationPoints(1);
                }
            }
        }
        
        getDataGame().addLog(this.getName() + " killed " + totalKills + " alien(s). You earned " + totalKills + " IP (Inspiration Points).");
        
        return totalKills;
    }
}
