package de.logic.data;

import de.logic.data.members.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataGame implements Constants{
    private Player player;
    private Ship ship;
    private List <Alien> aliensList;
    private List<String> logs;
    private String [] journeyTracker;
    private int currentTurn;
    private int dices[];
    private int activeCrewMember;

    public DataGame() {
        aliensList = new ArrayList <> ();
        logs = new ArrayList <> ();
        journeyTracker = new String[NUM_TURNS];
       
        currentTurn = 0;
        ship = new Ship();
        dices = new int[MAX_DICES];
        
        activeCrewMember = 1;
        
        journeyTracker = DEF_JOURNEY;
        
        for(int i = 0; i < dices.length; i++){
            dices[i] = 0;
        }

    }

    /**Getters and Setters**/
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public List<Alien> getAliensList() {
        return aliensList;
    }

    public void setAliensList(List<Alien> aliensList) {
        this.aliensList = aliensList;
    }

    public List<String> getLogs() {
        return logs;
    }

    public void setLogs(List<String> logs) {
        this.logs = logs;
    }

    public String[] getJourneyTracker() {
        return journeyTracker;
    }

    public void setJourneyTracker(String[] journeyTracker) {
        this.journeyTracker = journeyTracker;
    }
    
    public String getJourneyTrackerTurn(int turn) {
        turn--; //ARRAY INDEX = -1 of its number
        return journeyTracker[turn];
    }
    
    public void setJourneyTrackerTurn(int turn, String choice) {
        turn--; //ARRAY INDEX = -1 of its number
        this.journeyTracker[turn] = choice;
    }
    
    public int getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(int currentTurn) {
        this.currentTurn = currentTurn;
    }
    
    public int[] getDices() {
        return dices;
    }

    public void setDices(int[] dices) {
        this.dices = dices;
    }
    
    public int getActiveCrewMember(){
        return activeCrewMember;
    }
  
    /**Methods**/
    public void nextTurn(){
        setCurrentTurn(getCurrentTurn() + 1);
    }
    
    /**Dice methods**/
    public int rollDie(int dieId){
        
        int random = 0;
        
        if(dieId < 0 || dieId >= MAX_DICES){
            return 0;
        }
        
       dices[dieId] = random = (int) (Math.random() * (6 - 1)) + 1;
       
       return random;
    }
    
    public int rollDie(int dieId, int value){
               
        if((dieId < 0 || dieId >= MAX_DICES) && (value < 1 || value > 6)){
            return 0;
        }
        
       dices[dieId] = value;
       
       return value;
    }
    
    public void rollDice(int quantityOfDice){
        
        if(quantityOfDice > MAX_DICES){
            return;
        }
        
        for(int i = 0; i < quantityOfDice; i++){
            rollDie(i);
        }
    }
    
    public int getDieValue(int dieId){
        
        if(dieId < 0 || dieId >= MAX_DICES){
            return -1;
        }
        
        return dices[dieId];
    }
    
    public String diceToString(){
        String s = "";
        for(int i = 0; i < dices.length; i++){
           s += "Die "+(i+1)+": [" + getDieValue(i)+ "] ";
        }
        
       return s;
    }
    
    public void resetDices(){
        for(int i=0; i < MAX_DICES; i++)
            dices[i] = 0;
    }
    
    public int getDiceValue(){
        int dicesValue = 0;
                
        for(int i=0; i<2; i++)
            dicesValue += dices[i];
        
        return dicesValue;
    }
        
    /**Crew members methods**/
    public boolean selectCrewMember(int crewNumber, int crewType){
        crewNumber--; //ARRAY INDEX = -1 of its number
        
        int color=-1;//KEEP THE COLOR OF THE CLASS BEFORE (IF BEING CHANGED INSTEAD OF CREATED FOR THE FIRST TIME)
        CrewMember oldMember = getPlayer().getCrewMember(crewNumber);
        if(oldMember != null)
            color = oldMember.getColor();
            
        switch(crewType){
            case 1:
                getPlayer().setCrewMember(crewNumber, new Captain(this, color));
                break;
            case 2:
                getPlayer().setCrewMember(crewNumber, new Commander(this, color));
                break;
            case 3:
                getPlayer().setCrewMember(crewNumber, new CommsOfficer(this, color));
                break;
            case 4:
                getPlayer().setCrewMember(crewNumber, new Doctor(this, color));
                break;
            case 5:
                getPlayer().setCrewMember(crewNumber, new Engineer(this, color));
                break;
            case 6:
                getPlayer().setCrewMember(crewNumber, new MoralOfficer(this, color));
                break;
            case 7:
                getPlayer().setCrewMember(crewNumber, new NavigationOfficer(this, color));
                break;
            case 8:
                getPlayer().setCrewMember(crewNumber, new RedShirt(this, color));
                break;
            case 9:
                getPlayer().setCrewMember(crewNumber, new ScienceOfficer(this, color));
                break;
            case 10:
                getPlayer().setCrewMember(crewNumber, new SecurityOfficer(this, color));
                break;
            case 11:
                getPlayer().setCrewMember(crewNumber, new ShuttlePilot(this, color));
                break;
            case 12:
                getPlayer().setCrewMember(crewNumber, new TransporterChief(this, color));
                break;
            default:
                return false;
        }
        return true;
    }
    
    public boolean selectCrewMemberColor(int crewNumber, int crewMemberColor){
        //Exception ColorException = null;
        if(crewMemberColor < 0 || crewMemberColor >= 12)
            return false;
            //throw ColorException;
        CrewMember cm = getPlayer().getCrewMember(crewNumber-1);
        if(cm == null)
            return false;
        cm.setColor(crewMemberColor-1);
        return true;
    }
   
    public boolean placeCrewMember(int crewNumber, int roomNumber){
        crewNumber--; //ARRAY INDEX = -1 of its number
        
        if(roomNumber < 1 || roomNumber > NUM_ROOMS)
            return false;
        
        CrewMember cm = getPlayer().getCrewMember(crewNumber);
        Room room = getShip().getRoom(roomNumber);
        
        if(cm == null)
            return false;
        
        if(room == null)
            return false;
        
        room.setMemberInside(cm);
        return true;
    }
    
    public boolean crewClassNotRepeated(){
        for(int i=0; i<player.getCrew().length-1; i++){
            if(player.getCrewMember(i).getName().equals(player.getCrewMember(i+1).getName()))
                return false;
        }
        return true;
    }
    
    public boolean crewColorNotRepeated(){
        for(int i=0; i<player.getCrew().length-1; i++){
            if(player.getCrewMember(i).getColor() == player.getCrewMember(i+1).getColor())
                return false;
        }
        return true;
    }
    
    public void swapActiveCrewMember(){
        if(++activeCrewMember > NUM_CREW_MEMBERS)
            activeCrewMember = 1;
    }
    
    /**Journey Generation methods**/
    public boolean isValid_JourneyTurn(int turn, String event){
        if(event.trim().compareToIgnoreCase("R") == 0)//Event is 'R'
            return true;
        if(event.trim().matches("[1-9]+A[*]?")){//Event is an Alien Spawn - Has valid format
            Pattern p = Pattern.compile("[1-9]+");
            Matcher m = p.matcher(event.trim());
            if(m.find()){
                int numAliens = Integer.parseInt(m.group(0));
                if(numAliens >= MIN_SPAWN_ALIENS_TURN[turn] && numAliens <= MAX_SPAWN_ALIENS_TURN[turn])//Number of aliens to spawn is accepted
                    return true;
            }
        }
        return false;
    }
    public boolean isValid_JourneyTracker(){
        for(int i = 0; i < NUM_TURNS; i++){
            if(!isValid_JourneyTurn(i, journeyTracker[i]))
                return false;
        }
        return true;
    }
    
    public boolean editJourney_Choice(int turn, String choice){
        
        setJourneyTrackerTurn(turn, choice);
        
        return true;
    }
    
    public void editJourney_Random(){
        
        for(int i = 0; i < NUM_TURNS; i++){
            //ToDo
        }
        
    }
    
    public void resetJourneyToDefault(){
        journeyTracker = new String[NUM_TURNS];
        journeyTracker = DEF_JOURNEY;
    }
    
    /**Action points methods**/
    public int getActionPoints(){
        return player.getActionPoints();
    }
    
    public boolean addActionPoints(int quantity){
        if(quantity < 1)
            return false;
        
        player.setActionPoints(getActionPoints() + quantity);
        return true;
    }
    
    public boolean removeActionPoints(int quantity){
        if(quantity < 1)
            return false;
        
        int total = getActionPoints() - quantity;
        
        if(total < 0)
            total = 0;
        
        player.setActionPoints(total);
     
        return true;
    }
    
    /**Health Tracker methods**/
    public int getHealthTracker(){
        return player.getHealthTracker();
    }
    
    public boolean addHealthToPlayer(int quantity){
        if(quantity < 1)
            return false;
        
        player.setHealthTracker(getHealthTracker() + quantity);
        return true;
    }
    
    public boolean removeHealthFromPlayer(int quantity){
        if(quantity < 1)
            return false;
        
        int total = getHealthTracker() - quantity;
        
        if(total < 0)
            total = 0;
        
        player.setHealthTracker(total);
     
        return true;
    }
    
    /**Hull Tracker methods**/
    public int getHullTracker(){
        return ship.getHullTracker();
    }
    
    public boolean addHealthToHull(int quantity){
        if(quantity < 1)
            return false;
        
        ship.setHullTracker(getHullTracker() + quantity);
        return true;
    }
    
    public boolean removeHealthFromHull(int quantity){
        if(quantity < 1)
            return false;
        
        int total = getHullTracker() - quantity;
        
        if(total < 0)
            total = 0;
        
        ship.setHullTracker(total);
     
        return true;
    }
    
    /**Tokens methods**/
    public int getOrganicTrapTokens(){
        return player.getOrganicTrapTokens();
    }
    
    public int getParticleTrapTokens(){
        return player.getParticleTrapTokens();
    }
    
    public boolean addOrganicTrapTokens(int quantity){
       if(quantity < 1)
           return false;

       player.setOrganicTrapTokens(getOrganicTrapTokens() + quantity);
       return true;
   }

   public boolean removeOrganicTrapTokens(int quantity){
       if(quantity < 1)
           return false;

       int total = getOrganicTrapTokens() - quantity;

       if(total < 0)
           total = 0;

       player.setOrganicTrapTokens(total);

       return true;
   }
   
   public boolean addParticleTrapTokens(int quantity){
        if(quantity < 1)
            return false;
        
        player.setParticleTrapTokens(getParticleTrapTokens() + quantity);
        return true;
    }
    
    public boolean removeParticleTrapTokens(int quantity){
        if(quantity < 1)
            return false;
        
        int total = getParticleTrapTokens() - quantity;
        
        if(total < 0)
            total = 0;
        
        player.setParticleTrapTokens(total);
     
        return true;
    }
    
    /**Actions methods**/
    //Usar este metodo no UI para mostrar o custo do movimento
    public int getMovementCost(){
        CrewMember cm = player.getCrewMember(activeCrewMember);
        
        int freeMoves = cm.getMovement() - DEF_COST_MOVE;
        
        if(freeMoves <= 0){
            return DEF_COST_MOVE;
        }else if(cm.getMovementsBeforeFree() > 0 && cm.getMovementsBeforeFree() <= freeMoves){
            return 0; //Quando o Navigation Officer move-se 1 vez pode mover-se mais 1 vez de graça.
        }
        
        return DEF_COST_MOVE;
    }
    
    public boolean moveActiveCrewMember(int roomNumber){
        CrewMember cm = player.getCrewMember(activeCrewMember);
        
        if(getActionPoints() < DEF_COST_MOVE || roomNumber < 1 || roomNumber > NUM_ROOMS)
            return false;
        
        Room roomToMove = null;
        
        if(cm instanceof TransporterChief){
            roomToMove = ship.getRoom(roomNumber);
        }else{
            for(Room room : ship.getRoom(cm.getRoom().getId()).getClosestRooms()){
                if(room.getId() == roomNumber){
                    roomToMove = room;
                }
            }
        }
        
        if(roomToMove == null || roomToMove.isSealed())
            return false;
        
        int freeMoves = cm.getMovement() - DEF_COST_MOVE;
        
        if(getMovementCost() > 0)
            removeActionPoints(getMovementCost());
        
        cm.setMovementsBeforeFree(cm.getMovementsBeforeFree() + 1);
        
        if(cm.getMovementsBeforeFree() > freeMoves){
            cm.setMovementsBeforeFree(0);
        }
        
        roomToMove.setMemberInside(cm);
        
        return true;
    }
    

    public int attackAliens(int roomNumber){
        CrewMember cm = player.getCrewMember(activeCrewMember);
        
        if(getActionPoints() < DEF_COST_ATTACK || roomNumber > NUM_ROOMS)
            return 0;
        
        Room roomToAttack = null;
        
        if(cm instanceof ScienceOfficer){
            for(Room room : ship.getRoom(cm.getRoom().getId()).getClosestRooms()){
                if(room.getId() == roomNumber && !room.isSealed()){
                    roomToAttack = room;
                }
            }
        }else{
            roomToAttack = ship.getRoom(cm.getRoom().getId());
        }
        
        if(roomToAttack == null)
            return 0;
        
        int totalKills = 0;
        
        removeActionPoints(1);
        
        for(int i = 0; i < cm.getMovement(); i++){
            //Se o roll for 5+ ou se for capitao e o roll for 3+
            if((dices[i] >= MIN_ROLL_ATTACK) || ((cm instanceof Captain) && (dices[i] >= 3))){
                if(roomToAttack.removeRandomAlienFromRoom()){
                    totalKills++;
                    addInspirationPoints(1);
                }
            }
        }
        
        return totalKills;
    }
    
    public boolean healPlayer(){
        CrewMember cm = player.getCrewMember(activeCrewMember);
        
        if(getActionPoints() < DEF_COST_HEAL)
            return false;
        
        if(cm instanceof Doctor){
           removeActionPoints(DEF_COST_HEAL);
           addHealthToPlayer(1);
           return true;
        }
        
        return false;
    }
    
    public boolean fixHullTracker(){
        CrewMember cm = player.getCrewMember(activeCrewMember);

        if(cm instanceof Engineer){
            if(cm.getRoom().getName().equalsIgnoreCase("Engineering") && !((Engineer)cm).hasFixedForFree()){
                addHealthToHull(1);
                ((Engineer)cm).setHasFixedForFree(true); //TODO: inicio do turno colocar a false
                return true;
            }else{
                if(getActionPoints() < DEF_COST_FIX_HULL)
                    return false;
                removeActionPoints(DEF_COST_FIX_HULL);
                addHealthToHull(1);
                return true;
            }
        }
        
        return false;
    }
    
    public boolean placeTrap(int roomNumber, Trap trap){
        CrewMember cm = player.getCrewMember(activeCrewMember);
        
        if(getActionPoints() < DEF_COST_TRAP_ORGANIC)
            return false;
        
        Room room = ship.getRoom(roomNumber);
        if(room == null)
            return false;
        
        if(room.getTrapInside() != null)
            return false;
        
        if(!cm.getRoom().equals(room))
            return false;
        
        if(trap instanceof OrganicDetonator && getOrganicTrapTokens() > 0){
            room.setTrapInside(new OrganicDetonator(this));
            removeOrganicTrapTokens(1);
        }
        else if (trap instanceof ParticleDispenser && getParticleTrapTokens() > 0){
            room.setTrapInside(new ParticleDispenser(this));
            removeParticleTrapTokens(1);
        }
        else
            return false;
       
        removeActionPoints(DEF_COST_TRAP_ORGANIC);
        
        return true;
    }
    
    public boolean detonateParticleDispenser(int roomNumber){
        
        if(getActionPoints() < DEF_COST_DETONATE_TRAP_PARTICLE || roomNumber < 1 || roomNumber > NUM_ROOMS)
            return false;
        
        Room roomToBoom = null;
        
        if(ship.getRoom(roomNumber).getTrapInside() instanceof ParticleDispenser){
            roomToBoom = ship.getRoom(roomNumber);
        }
        
        if(roomToBoom == null)
            return false;
        
        roomToBoom.removeAllAliens();
        
        if(roomToBoom.getMembersInside().size() > 0){
            
            removeHealthFromPlayer(player.getHealthTracker());
        }
        
        
        return true;
       
        
    }
    
    public boolean sealRoom(int roomNumber){
        
        if(getActionPoints() < DEF_COST_SEAL_ROOM)
            return false;
        
        Room room = ship.getRoom(roomNumber);
        if(room == null || !room.canBeSealed() || room.isSealed())
            return false;
        
        room.setSealed(true);
       
        return true;
    }
    
    /**Inspiration points methods**/
    public int getInspirationPoints(){
        return player.getInspirationPoints();
    }
    
    public boolean addInspirationPoints(int quantity){
        if(quantity < 1)
            return false;
        
        player.setInspirationPoints(getInspirationPoints() + quantity);
        
        return true;
    }
    
    public boolean removeInspirationPoints(int quantity){
        if(quantity < 1)
            return false;
        
        int total = getInspirationPoints() - quantity;
        
        if(total < 0)
            total = 0;
        
        player.setInspirationPoints(total);
     
        return true;
    }
    
    /**Inspiration methods**/
    public boolean addHealthPoint(int quantity){
        if(player.getInspirationPoints() < DEF_COST_I_ADD_HEALTH)
            return false;
        
        if(!addHealthToPlayer(quantity))
            return false;
        
        removeInspirationPoints(DEF_COST_I_ADD_HEALTH);
        
        return true;
    }
    
    public boolean repairHull(int quantity){
        if(player.getInspirationPoints() < DEF_COST_I_REPAIR_HULL)
            return false;
        
        if(!addHealthToHull(quantity))
            return false;
        
        removeInspirationPoints(DEF_COST_I_REPAIR_HULL);
        
        return true;
    }
    
    public boolean buildOrganicDetonator(){
        if(player.getInspirationPoints() < DEF_COST_I_BUILD_TRAP_ORGANIC)
            return false;
        
        addOrganicTrapTokens(1);
        removeInspirationPoints(DEF_COST_I_BUILD_TRAP_ORGANIC);
        
        return true;
    }
    
    public boolean addMovement(int quantity, int crewNumber){
        if(quantity < 1)
            return false;
        
        if(player.getInspirationPoints() < DEF_COST_I_ADD_MOVEMENT)
            return false;
        
        CrewMember cm = player.getCrewMember(crewNumber);
        if(cm == null)
            return false;
        
        if(!cm.setMovement(cm.getMovement() + quantity))
            return false;
        
        removeInspirationPoints(DEF_COST_I_ADD_MOVEMENT);
        
        return true;
    }
    
    public boolean buildParticleDesperser(){
        if(player.getInspirationPoints() < DEF_COST_I_BUILD_TRAP_PARTICLE)
            return false;
        
        addParticleTrapTokens(1);
        
        removeInspirationPoints(DEF_COST_I_BUILD_TRAP_PARTICLE);
        
        return true;
    }
    
    public boolean addSealedRoomToken(int quantity){
        if(quantity < 1)
            return false;
        
        if(player.getInspirationPoints() < DEF_COST_I_ADD_SEALED_TOKEN)
            return false;
        
        player.setRoomSealTokens(player.getRoomSealTokens() + quantity);
        
        removeInspirationPoints(DEF_COST_I_ADD_SEALED_TOKEN);
        
        return true;
    }
    
    public boolean addAttackDie(int quantity, int crewNumber){
        if(quantity < 1)
            return false;
        
        if(player.getInspirationPoints() < DEF_COST_I_ADD_ATTACK_DIE)
            return false;
        
        CrewMember cm = player.getCrewMember(crewNumber);
        if(cm == null)
            return false;
        
        if(!cm.setAttack(cm.getAttack() + quantity))
            return false;
        
        removeInspirationPoints(DEF_COST_I_ADD_ATTACK_DIE);
        
        return true;
    }
    
    public boolean addValueToAttackDie(int quantity){
        if(quantity < 1)
            return false;
        
        if(player.getInspirationPoints() < DEF_COST_I_ADD_VALUE_ATTACK_DIE)
            return false;
        
        //???//
        
        removeInspirationPoints(DEF_COST_I_ADD_VALUE_ATTACK_DIE);
        
        return true;
    }
    
    /**Object methods**/
    @Override
    public String toString()
    {
        String s;
        
        s = "Destination Earth, playing as " + this.getPlayer().getName() + System.lineSeparator();
        s = "Turn: " + getCurrentTurn() + System.lineSeparator();
        s+= diceToString();
        s+= System.lineSeparator(); 
        
        return s;
    }
}
