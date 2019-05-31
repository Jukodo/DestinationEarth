package de.logic.data.members;

import static de.logic.data.Constants.*;
import de.logic.data.DataGame;
import de.logic.data.OrganicDetonator;
import de.logic.data.ParticleDispenser;
import de.logic.data.Room;
import de.logic.data.Trap;
import java.io.Serializable;
import javafx.scene.paint.Color;

public abstract class CrewMember implements Serializable{
    
    private int movement;
    private int attack;
    int color;
    private DataGame dataGame;
    private Room room;
    private int movementsBeforeFree;
    private Color customColor;
    
    //Doesn't recieve color
    public CrewMember(DataGame dataGame, int movement, int attack){
        this.dataGame = dataGame;
        this.movement = movement;
        this.attack = attack;
        this.movementsBeforeFree = 0;
    }
    
    //Recieve color
    public CrewMember(DataGame dataGame, int movement, int attack, int color){
        this.dataGame = dataGame;
        this.movement = movement;
        this.attack = attack;
        if(color < -1 || color > 11)
            color = 0;
        this.color = color;
    }

    abstract public String getName();
    
    /**Getters and Setters**/
    public int getMovement() {
        return movement;
    }

    public boolean setMovement(int movement) {
        if(this.movement == MAX_MOVEMENT)//Already has maxed out movement
            return false;
        
        if(movement > MAX_MOVEMENT)
            movement = MAX_MOVEMENT;
        
        this.movement = movement;
        
        return true;
    }

    public int getAttack() {
        return attack;
    }

    public boolean setAttack(int attack) {
        if(attack > MAX_ATTACK)
            return false;
        this.attack = attack;
        return true;
    }
    
    public int getColor() {
        return color;
    }
    
    public Color getCustomColor() {
        return customColor;
    }

    public void setColor(int color) {
        this.color = color;
    }
    
    public void setCustomColor(Color color){
        this.customColor = color;
    }

    public DataGame getDataGame() {
        return dataGame;
    }

    public void setDataGame(DataGame dataGame) {
        this.dataGame = dataGame;
    }
    
    public Room getRoom(){
        return room;
    }
    
    public void setRoom(Room room){
        this.room = room;
    }
    
    public void leaveRoom(){
        this.room.removeMemberFromRoom(this);
        this.room = null;
    }
    
    public void enterRoom(Room room){
        
        if(isInside())
            leaveRoom();
        
        this.room = room;
        room.setMemberInside(this);
    }
    
    public boolean isInside() {
        return room != null;
    }

    public int getMovementsBeforeFree() {
        return movementsBeforeFree;
    }

    public void setMovementsBeforeFree(int movementsBeforeFree) {
        this.movementsBeforeFree = movementsBeforeFree;
    }
    
    public boolean move(int roomNumber){
    
        int cost = dataGame.getMovementCost();
        
        if(roomNumber < 1 || roomNumber > NUM_ROOMS){
            dataGame.addLog("Room selected doesn't exist!");
            return false;
        }
        
        if(cost > 0 && dataGame.getActionPoints() < DEF_COST_A_MOVE){
            dataGame.addLog("Not enough AP (Action Points)!");
            return false;
        }
        
        Room roomToMove = null;
        
        for(Room room : dataGame.getShip().getRoom(this.getRoom().getId()).getClosestRooms()){
            if(room.getId() == roomNumber){
                roomToMove = room;
            }
        }
        
        if(roomToMove == null || roomToMove.getIsSealed()){
            dataGame.addLog("Cannot move to selected Room! Please check if sealed or too far...");
            return false;
        }
        
        int freeMoves = this.getMovement() - DEF_COST_A_MOVE;
        
        if(dataGame.getMovementCost() > 0)
            dataGame.removeActionPoints(dataGame.getMovementCost());
        
        this.setMovementsBeforeFree(this.getMovementsBeforeFree() + 1);
        
        if(this.getMovementsBeforeFree() > freeMoves){
            this.setMovementsBeforeFree(0);
        }
  
        this.enterRoom(roomToMove);
        
        dataGame.addLog(this.getName() + " moved to " + roomToMove.toString());
        
        
        return true;
    }
    
    public int attack(int roomNumber){
        
        if(dataGame.getActionPoints() < DEF_COST_A_ATTACK)
            return 0;
        
        Room roomToAttack = null;
        
        roomToAttack = dataGame.getShip().getRoom(this.getRoom().getId());
        
        if(roomToAttack == null){
            dataGame.addLog("Cannot attack selected Room! Please check if sealed or too far...");
            return 0;
        }
        
        if(roomToAttack.getAliensInside().size() < 1){
            dataGame.addLog("There aren't any aliens in the room to attack!");
            return 0;
        }
    
        int totalKills = 0;
        
        dataGame.removeActionPoints(1);
        
        for(int i = 0; i < this.getAttack(); i++){
     
            if(dataGame.getDices()[i] >= MIN_ROLL_ATTACK - dataGame.getPlayer().getAttackBuff()){
                if(roomToAttack.removeRandomAlienFromRoom()){
                    dataGame.decreaseAliensCount();
                    totalKills++;
                    dataGame.addInspirationPoints(1);
                }
            }
        }
        
        dataGame.addLog(this.getName() + " killed " + totalKills + " alien(s). You earned " + totalKills + " IP (Inspiration Points).");
        
        return totalKills;
    }
    
    public boolean special(){
        return false;
    }
    
    public boolean placeTrap(int trapType){
        
        if(dataGame.getActionPoints() < DEF_COST_A_TRAP_ORGANIC){
            dataGame.addLog("Not enough AP (Action Points)!");
            return false;
        }
        
        Room room = dataGame.getShip().getRoom(this.getRoom().getId());
        
        if(room == null){
            dataGame.addLog("Selected room doesn't exist!");
            return false;
        }
        
        if(room.getTrapInside() != null){
            dataGame.addLog("Selected room already has a trap!");
            return false;
        }
        
        if(!this.getRoom().equals(room)){
            dataGame.addLog("Selected crew member isn't inside the selected room!");
            return false;
        }
        
        Trap trap;
        
        if(trapType == ORGANIC_TRAP){
            room.setTrapInside(new OrganicDetonator(dataGame, this.getRoom()));
            dataGame.removeOrganicTrapTokens(1);
            dataGame.addLog("Organic Detonator was planted in " + room.toString());
        }else if(trapType == PARTICLE_TRAP){
            room.setTrapInside(new ParticleDispenser(dataGame, this.getRoom()));
            dataGame.removeParticleTrapTokens(1);
            dataGame.addLog("Particle Dispenser was planted in " + room.toString());
        }
        else{
            dataGame.addLog("Invalid trap!");
            return false;
        }
       
        dataGame.removeActionPoints(DEF_COST_A_TRAP_ORGANIC);
        
        return true;
    }
    
    public boolean detonateParticleDispenser(int roomNumber){

        if(roomNumber < 1 || roomNumber > NUM_ROOMS){
            dataGame.addLog("Selected room doesn't exist!");
            return false;
        }
        
        if(dataGame.getActionPoints() < DEF_COST_A_DETONATE_TRAP_PARTICLE){
            dataGame.addLog("Not enough AP (Action Points)!");
            return false;
        }
        
        if(dataGame.getShip().getRoom(roomNumber).getTrapInside() == null){
            dataGame.addLog("Selected room doesn't have a Particle Dispenser");
            return false;
        }
            
        
        return dataGame.getShip().getRoom(roomNumber).getTrapInside().detonate(roomNumber);
    }
    
    public boolean sealRoom(int roomNumber){
        if(roomNumber < 1 || roomNumber > NUM_ROOMS){
            dataGame.addLog("Selected room doesn't exist!");
            return false;
        }
        
        if(dataGame.getActionPoints() < DEF_COST_A_SEAL_ROOM){
            dataGame.addLog("Not enough AP (Action Points)!");
            return false;
        }
        
        if(dataGame.getPlayer().getRoomSealTokens() < 1){
            dataGame.addLog("Not enough 'Seal Room' Tokens!");
            return false;
        }
        
        Room room = dataGame.getShip().getRoom(roomNumber);
        if(room == null){
            dataGame.addLog("Selected room doesn't exist!");
            return false;
        }else if(!room.getCanBeSealed()){
            dataGame.addLog("Selected room cannot be sealed!");
            return false;
        }else if(room.getIsSealed()){
            dataGame.addLog("Selected room is already sealed!");
            return false;
        }
        else if(room.getAliensInside().size() > 0){
            dataGame.addLog("Selected room cannot be sealed: There are aliens inside!");
            return false;
        }
        else if(room.getMembersInside().size() > 0){
            dataGame.addLog("Selected room cannot be sealed: There are crew members inside!");
            return false;
        }
        
        dataGame.addLog(room.toString() + " was sealed with success!");
        
        dataGame.removeActionPoints(DEF_COST_A_SEAL_ROOM);
        room.setSealed(true);
        dataGame.removeSealedTokens(1);
       
        return true;
    }
}
