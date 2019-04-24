package de.logic.data;

import de.logic.data.members.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataGame implements Constants, Serializable{
    private Player player;
    private Ship ship;
    private int aliensCount;
    private List<String> logs;
    private String [] journeyTracker;
    private int currentTurn;
    private int dices[];
    private int activeCrewMember;
    
    private List<Alien> newAliens;
    private int activeNewAlien;
   
    public DataGame() {
        logs = new ArrayList <> ();
        journeyTracker = new String[NUM_TURNS];
       
        currentTurn = 0;
        ship = new Ship();
        dices = new int[MAX_DICES];
        
        activeCrewMember = 1;
        activeNewAlien = 1;
        
        newAliens = new ArrayList<>();
        
        for(int i = 0; i < dices.length; i++){
            dices[i] = 0;
        }

    }

    /**Log methods**/
    public void clearLogs() {
        logs.clear();
    }
    
    public void addLog(String msg) {
        logs.add(msg);
    }
    
    public List<String> getLogs() {
        return logs;
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
    
    public int getAliensCount() {
        return aliensCount;
    }

    public void setAliensCount(int aliensCount) {
        this.aliensCount = aliensCount;
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
    
    public int getActiveNewAlien() {
        return activeNewAlien;
    }

    public List<Alien> getNewAliens() {
        return newAliens;
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
               
        if((dieId < 0 || dieId >= MAX_DICES) || (value < 1 || value > 6)){
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
    
    public int getDiceValue(int numDices){
        
        if(numDices > MAX_DICES)
            return 0;
        
        int dicesValue = 0;
                
        for(int i=0; i<numDices; i++)
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
                addLog("Crew Member class is invalid!");
                return false;
        }
        return true;
    }
    
    public boolean selectCrewMemberColor(int crewNumber, int crewMemberColor){
        if(crewMemberColor < 0 || crewMemberColor >= 12){
            addLog("Crew Member color is invalid!");
            return false;
        }

        CrewMember cm = getPlayer().getCrewMember(crewNumber-1);
        if(cm == null){
            addLog("Crew Member selected doesn't exist!");
            return false;
        }
        
        cm.setColor(crewMemberColor-1);
        return true;
    }
   
    public boolean placeCrewMember(int crewNumber, int roomNumber){
        crewNumber--; //ARRAY INDEX = -1 of its number
        
        if(roomNumber < 1 || roomNumber > NUM_ROOMS){
            addLog("Room selected doesn't exist!");
            return false;
        }
        
        CrewMember cm = getPlayer().getCrewMember(crewNumber);
        Room room = getShip().getRoom(roomNumber);
        
        if(cm == null){
            addLog("Crew Member selected doesn't exist!");
            return false;
        }
        
        if(room == null){
            addLog("Room selected doesn't exist!");
            return false;
        }
        
        cm.enterRoom(room);
        return true;
    }
    
    public boolean crewClassNotRepeated(){
        for(int i=0; i<player.getCrew().length-1; i++){
            if(player.getCrewMember(i).getName().equals(player.getCrewMember(i+1).getName())){
                addLog("Crew cannot have same class!");
                return false;
            }
        }
        return true;
    }
    
    public boolean crewColorNotRepeated(){
        for(int i=0; i<player.getCrew().length-1; i++){
            if(player.getCrewMember(i).getColor() == player.getCrewMember(i+1).getColor()){
                addLog("Crew cannot have same color!");
                return false;
            }
        }
        return true;
    }
    
    public void swapActiveCrewMember(){
        if(++activeCrewMember > NUM_CREW_MEMBERS)
            activeCrewMember = 1;
        
        CrewMember cm = this.getPlayer().getCrewMember(activeCrewMember-1);
        
        if(cm instanceof RedShirt && !((RedShirt)cm).isAlive() ){
            swapActiveCrewMember();
        }
    }
    
    public boolean sacrificeCrewMember(){
        CrewMember cm = player.getCrewMember(activeCrewMember-1);
        if(cm instanceof RedShirt && ((RedShirt)cm).isAlive()){
            
            if(cm.isInside()){
                cm.leaveRoom();
            }
            
            ((RedShirt)cm).setAlive(false);
            addHealthToPlayer(5);
            swapActiveCrewMember();
            addLog("You sacrificed Red Shirt and earned 5 health! Good journey comrade Red Shirt!");
            return true;
        }
        
        return false;
    }
    
    /**Aliens methods**/
    
    public void swapActiveNewAlien(){
        if(++activeNewAlien > newAliens.size())
            activeNewAlien = 1;
    }
    
    /**Journey Generation methods**/
    public int getAlienSpawnNumber(String event){
        Pattern p = Pattern.compile("[1-9]+");
        Matcher m = p.matcher(event.trim());
        if(m.find())
            return Integer.parseInt(m.group(0));
        return 0;
    }
    
    public boolean eventIsAlienSpawnSpecial(String event){
        return event.contains("*");
    }
    
    public boolean eventIsRest(String event){
        if(event.trim().compareToIgnoreCase("R") == 0)//Event is 'R'
            return true;
        return false;
    }
    
    public boolean eventIsAlienSpawn(int turn, String event){
        if(event.trim().matches("[1-9]+A[*]?")){//Event is an Alien Spawn - Has valid format
            int numAliens = getAlienSpawnNumber(event);
            if(numAliens >= MIN_SPAWN_ALIENS_TURN[turn] && numAliens <= MAX_SPAWN_ALIENS_TURN[turn])//Number of aliens to spawn is accepted
                return true;
        }
        return false;
    }
    
    public boolean isValid_JourneyTurn(int turn, String event){
        
        if(eventIsRest(event))
            return true;
        
        if(eventIsAlienSpawn(turn, event))
            return true;
        
        addLog("Journey selected is invalid! Please check accepted formats...");
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
        
        //1st - Clear the tracker
        for(int i = 0; i < NUM_TURNS; i++){
            journeyTracker[i] = null;
        }
        
        //2nd - Set 2 Rest Phases (Wont allow R on first or last turn, nor 2 R next to eachother)
        journeyTracker[(int) (Math.random() * (6 - 2)) + 2] = "R";
        journeyTracker[(int) (Math.random() * (12 - 8)) + 8] = "R";
        
        StringBuilder sb;
        
        //3rd - For each empty turn, randomize an event
        for(int i = 0; i < NUM_TURNS; i++){
            sb = new StringBuilder();
            if(journeyTracker[i] == null){
                //Randomize number of aliens to spawn, in between the range of MIN and MAX
                sb.append((int) (Math.random() * (MAX_SPAWN_ALIENS_TURN[i] - MIN_SPAWN_ALIENS_TURN[i])) + MIN_SPAWN_ALIENS_TURN[i]);
                sb.append("A");
                //IF NOT LAST && NEXT NOT NULL && NEXT IS R -> ADD SPECIAL
                if(i < NUM_TURNS-1 && journeyTracker[i+1] != null && journeyTracker[i+1].compareToIgnoreCase("R") == 0)
                    sb.append("*");
                journeyTracker[i] = sb.toString();
            }
        }
    }
    
    public void resetJourneyTracker(){
        for(int i=0; i<NUM_TURNS; i++){
            journeyTracker[i] = DEF_JOURNEY[i];
        }
    }
    
    /**Scanning phase methods**/
    public boolean gameOverConditions(){
        if(currentTurn >= NUM_TURNS){
            addLog("The ship has reached earth! You survived!");
            return true;
        }
        if(player.getHealthTracker() <= 0){
            addLog("Your health has reached 0... You lost!");
            return true;
        }
        if(ship.getHullTracker() <= 0){
            addLog("Your ship integrity has reached 0... You lost!");
            return true;
        }
        
        return false;
    }
    
    public boolean spawnAlien(Room room){
        
        if(room == null){
            addLog("Room selected doesn't exist!");
            return false;
        }
        
        Alien alien = new Alien();
        alien.enterRoom(room);
        
        newAliens.add(alien);
        
        return true;
    }
    
    public boolean spawnAliens(int numAliens){
        
        for(int i = 0; i < numAliens; i++){
            rollDice(2);
            if(!spawnAlien(ship.getRoom(getDiceValue(2)))){
                addLog("Error spawning alien!");
                return false;
            }
            resetDices();
        }
        
        return true;
    }
    
    public boolean placeNewAlien(int alienNumber, int roomNumber){
        alienNumber--; //ARRAY INDEX = -1 of its number
        
        if(roomNumber < 1 || roomNumber > NUM_ROOMS){
            addLog("Room selected doesn't exist!");
            return false;
        }
        
        Alien alien = newAliens.get(alienNumber);
        Room room = getShip().getRoom(roomNumber);
        
        if(alien == null){
            addLog("Alien selected doesn't exist!");
            return false;
        }
        
        if(room == null){
            addLog("Room selected doesn't exist!");
            return false;
        }
        
        alien.enterRoom(room);
        
        return true;
    }
    
    public void clearNewAliens(){
        newAliens.clear();
    }
    
    /**Action points methods**/
    public int getActionPoints(){
        return player.getActionPoints();
    }
    
    public boolean addActionPoints(int quantity){
        if(quantity < 1){
            addLog("Invalid quantity!");
            return false;
        }
        
        player.setActionPoints(getActionPoints() + quantity);
        return true;
    }
    
    public boolean removeActionPoints(int quantity){
        if(quantity < 1){
            addLog("Invalid quantity!");
            return false;
        }
        
        int total = getActionPoints() - quantity;
        
        if(total < 0)
            total = 0;
        
        player.setActionPoints(total);
     
        return true;
    }
    
    public int getAvailableActions_Quant(){
        int i = DEF_ACTIONS.length;
        
        CrewMember cm = player.getCrewMember(activeCrewMember-1);
        
        if(cm instanceof Doctor)
            i++;
        else if(cm instanceof Engineer)
            i++;
        else if(cm instanceof RedShirt && ((RedShirt)cm).isAlive())
            i++;
        
        return i;
    }
    
    public String getAvailableActions(){
        
        CrewMember cm = player.getCrewMember(activeCrewMember-1);
        
        String s = "";
        int i;
        
        for(i = 0; i < DEF_ACTIONS.length; i++){
            
            s+= (i+2) + " - " + DEF_ACTIONS[i] + "(";
            if(i == 0)
                s+= this.getMovementCost();
            else
                s+= DEF_ACTIONS_COST[i];
            
            s+= " AP)" + System.lineSeparator();
            
        }
                
        if(cm instanceof Doctor){
            i++;
            s+= (i+1) + " - Heal One Health (" + this.getHealCost() + " AP)" + System.lineSeparator();
        }
        else if(cm instanceof Engineer){
            i++;
            s+= (i+1) + " - Fix One Hull (" + this.getFixHullCost() + " AP)" + System.lineSeparator();
        }
        else if(cm instanceof RedShirt && ((RedShirt)cm).isAlive() == true){
            i++;
            s+= (i+1) + " - Sacrifice for 5 health (0 AP)" + System.lineSeparator();
        }
        
        return s;
    }
    
    public void resetActionPoints(){
        
        getPlayer().setActionPoints(DEF_ACTION_POINTS);
        
        for (CrewMember cm : this.getPlayer().getCrew()) {
            if(cm instanceof Commander){
                addActionPoints(6-getActionPoints());
                break;
            }
        }   
    }
    
    /**Health Tracker methods**/
    public int getHealthTracker(){
        return player.getHealthTracker();
    }
    
    public boolean addHealthToPlayer(int quantity){
        if(quantity < 1){
            addLog("Invalid quantity!");
            return false;
        }
        
        player.setHealthTracker(getHealthTracker() + quantity);
        return true;
    }
    
    public boolean removeHealthFromPlayer(int quantity){
        if(quantity < 1){
            addLog("Invalid quantity!");
            return false;
        }
        
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
        if(quantity < 1){
            addLog("Invalid quantity!");
            return false;
        }
        
        ship.setHullTracker(getHullTracker() + quantity);
        return true;
    }
    
    public boolean removeHealthFromHull(int quantity){
        if(quantity < 1){
            addLog("Invalid quantity!");
            return false;
        }
        
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
       if(quantity < 1){
            addLog("Invalid quantity!");
            return false;
        }
       
       if(getOrganicTrapTokens() == MAX_TRAPS_ORGANIC){
            addLog("Cannot own more organic traps, max reached!");
            return false;
        }

       player.setOrganicTrapTokens(getOrganicTrapTokens() + quantity);
       return true;
   }

   public boolean removeOrganicTrapTokens(int quantity){
       if(quantity < 1){
            addLog("Invalid quantity!");
            return false;
        }

       int total = getOrganicTrapTokens() - quantity;

       if(total < 0)
           total = 0;

       player.setOrganicTrapTokens(total);

       return true;
   }
   
   public boolean addParticleTrapTokens(int quantity){
        if(quantity < 1){
            addLog("Invalid quantity!");
            return false;
        }
        
        if(this.getParticleTrapTokens() == MAX_TRAPS_PARTICLE){//Already has maxed allowed traps
            addLog("Cannot own more particle traps, max reached!");
            return false;
        }
        
        player.setParticleTrapTokens(getParticleTrapTokens() + quantity);
        return true;
    }
    
    public boolean removeParticleTrapTokens(int quantity){
        if(quantity < 1){
            addLog("Invalid quantity!");
            return false;
        }
        
        int total = getParticleTrapTokens() - quantity;
        
        if(total < 0)
            total = 0;
        
        player.setParticleTrapTokens(total);
     
        return true;
    }
    
    /**Actions methods**/
    //Usar este metodo no UI para mostrar o custo do movimento
    public int getMovementCost(){
        CrewMember cm = player.getCrewMember(activeCrewMember-1);
        
        int freeMoves = cm.getMovement() - DEF_COST_MOVE;
        
        if(freeMoves <= 0){
            return DEF_COST_MOVE;
        }else if(cm.getMovementsBeforeFree() > 0 && cm.getMovementsBeforeFree() <= freeMoves){
            return 0; //Quando o Navigation Officer move-se 1 vez pode mover-se mais 1 vez de graça.
        }
        
        return DEF_COST_MOVE;
    }
    
    public int getHealCost(){
        CrewMember cm = player.getCrewMember(activeCrewMember-1);
        
        if(cm instanceof Doctor && cm.getRoom().getName().equalsIgnoreCase("SickBay") && !((Doctor)cm).hasHealedForFree()){
            return 0;
        }
        
        return DEF_COST_HEAL;
    }
    
    public int getFixHullCost(){
        CrewMember cm = player.getCrewMember(activeCrewMember-1);
        
        if(cm instanceof Engineer && cm.getRoom().getName().equalsIgnoreCase("Engineering") && !((Engineer)cm).hasFixedForFree()){
            return 0;
        }
        
        return DEF_COST_FIX_HULL;
    }
    
    public boolean moveActiveCrewMember(int roomNumber){
        
        int cost = getMovementCost();
        CrewMember cm = player.getCrewMember(activeCrewMember-1);
        
        if(roomNumber < 1 || roomNumber > NUM_ROOMS){
            addLog("Room selected doesn't exist!");
            return false;
        }
        
        if(cost > 0 && getActionPoints() < DEF_COST_MOVE){
            addLog("Not enough AP (Action Points)!");
            return false;
        }
   
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
        
        if(roomToMove == null || roomToMove.getIsSealed()){
            addLog("Cannot move to selected Room! Please check if sealed or too far...");
            return false;
        }
        
        int freeMoves = cm.getMovement() - DEF_COST_MOVE;
        
        
        if(getMovementCost() > 0)
            removeActionPoints(getMovementCost());
        
        cm.setMovementsBeforeFree(cm.getMovementsBeforeFree() + 1);
        
        if(cm.getMovementsBeforeFree() > freeMoves){
            cm.setMovementsBeforeFree(0);
        }
        
        cm.enterRoom(roomToMove);
        
        addLog(cm.getName() + " moved to " + roomToMove.toString());
        
        return true;
    }
    

    public int attackAliens(int roomNumber){
        CrewMember cm = player.getCrewMember(activeCrewMember-1);
        
        if(getActionPoints() < DEF_COST_ATTACK || roomNumber > NUM_ROOMS)
            return 0;
        
        Room roomToAttack = null;
        
        if(cm instanceof ScienceOfficer){
            for(Room room : ship.getRoom(cm.getRoom().getId()).getClosestRooms()){
                if(room.getId() == roomNumber && !room.getIsSealed()){
                    roomToAttack = room;
                }
            }
        }else{
            roomToAttack = ship.getRoom(cm.getRoom().getId());
        }
        
        if(roomToAttack == null){
            addLog("Cannot attack selected Room! Please check if sealed or too far...");
            return 0;
        }
        
        if(roomToAttack.getAliensInside().size() < 1){
            addLog("There aren't any aliens in the room to attack!");
        }
            
        
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
        
        addLog(cm.getName() + " killed " + totalKills + " alien(s). You earned " + totalKills + " IP (Inspiration Points).");
        
        return totalKills;
    }
    
    public boolean healPlayer(){
        CrewMember cm = player.getCrewMember(activeCrewMember-1);
        
        if(cm instanceof Doctor){
            if(cm.getRoom().getName().equalsIgnoreCase("SickBay") && !((Doctor)cm).hasHealedForFree()){
                addHealthToPlayer(1);
                ((Doctor)cm).setHasHealedForFree(true); //TODO: inicio do turno colocar a false
                addLog("Player was healed by 1 health!");
                return true;
            }else{
                if(getActionPoints() < DEF_COST_HEAL){
                    addLog("Not enough AP (Action Points)!");
                    return false;
                }
                removeActionPoints(DEF_COST_HEAL);
                addHealthToPlayer(1);
                addLog("Player was healed by 1 health!");
                return true;
            }
        }
        
        addLog("Selected crew member cannot execute this action!");
        return false;
    }
    
    public boolean fixHullTracker(){
        CrewMember cm = player.getCrewMember(activeCrewMember-1);

        if(cm instanceof Engineer){
            if(cm.getRoom().getName().equalsIgnoreCase("Engineering") && !((Engineer)cm).hasFixedForFree()){
                addHealthToHull(1);
                ((Engineer)cm).setHasFixedForFree(true); //TODO: inicio do turno colocar a false
                addLog("Ship's hull was fixed by 1 health!");
                return true;
            }else{
                if(getActionPoints() < DEF_COST_FIX_HULL){
                    addLog("Not enough AP (Action Points)!");
                    return false;
                }
                removeActionPoints(DEF_COST_FIX_HULL);
                addHealthToHull(1);
                addLog("Ship's hull was fixed by 1 health!");
                return true;
            }
        }
        
        addLog("Selected crew member cannot execute this action!");
        return false;
    }
    
    public boolean placeTrap(Trap trap){
        CrewMember cm = player.getCrewMember(activeCrewMember-1);
        
        if(getActionPoints() < DEF_COST_TRAP_ORGANIC){
            addLog("Not enough AP (Action Points)!");
            return false;
        }
        
        Room room = ship.getRoom(cm.getRoom().getId());
        if(room == null){
            addLog("Selected room doesn't exist!");
            return false;
        }
        
        if(room.getTrapInside() != null){
            addLog("Selected room already has a trap!");
            return false;
        }
        
        if(!cm.getRoom().equals(room)){
            addLog("Selected crew member isn't inside the selected room!");
            return false;
        }
        
        if(trap instanceof OrganicDetonator && getOrganicTrapTokens() > 0){
            room.setTrapInside(new OrganicDetonator(this));
            removeOrganicTrapTokens(1);
            addLog("Organic Detonator was planted in " + room.toString());
        }
        else if (trap instanceof ParticleDispenser && getParticleTrapTokens() > 0){
            room.setTrapInside(new ParticleDispenser(this));
            removeParticleTrapTokens(1);
            addLog("Particle Dispenser was planted in " + room.toString());
        }
        else{
            addLog("Invalid trap!");
            return false;
        }
       
        removeActionPoints(DEF_COST_TRAP_ORGANIC);
        
        return true;
    }
    
    public boolean detonateParticleDispenser(int roomNumber){

        if(roomNumber < 1 || roomNumber > NUM_ROOMS){
            addLog("Selected room doesn't exist!");
            return false;
        }
        
        if(getActionPoints() < DEF_COST_DETONATE_TRAP_PARTICLE){
            addLog("Not enough AP (Action Points)!");
            return false;
        }
        
        Room roomToBoom = null;
        
        if(ship.getRoom(roomNumber).getTrapInside() instanceof ParticleDispenser){
            roomToBoom = ship.getRoom(roomNumber);
        }
        
        if(roomToBoom == null){
            addLog("Selected room doesn't have a Particle Dispenser");
            return false;
        }
        
        int nAliens = roomToBoom.getAliensInside().size();
        roomToBoom.removeAllAliens();
        
        if(roomToBoom.getMembersInside().size() > 0){
            removeHealthFromPlayer(player.getHealthTracker());
        }
        
        removeActionPoints(DEF_COST_DETONATE_TRAP_PARTICLE);
        roomToBoom.removeTrap();
        
        addLog("Particle Dispenser detonated with success!" + " You killed " + nAliens + " aliens");
        
        return true;
    }
    
    public boolean sealRoom(int roomNumber){

        if(roomNumber < 1 || roomNumber > NUM_ROOMS){
            addLog("Selected room doesn't exist!");
            return false;
        }
        
        if(getActionPoints() < DEF_COST_SEAL_ROOM){
            addLog("Not enough AP (Action Points)!");
            return false;
        }
        
        Room room = ship.getRoom(roomNumber);
        if(room == null){
            addLog("Selected room doesn't exist!");
            return false;
        }else if(!room.getCanBeSealed()){
            addLog("Selected room cannot be sealed!");
            return false;
        }else if(room.getIsSealed()){
            addLog("Selected room is already sealed!");
            return false;
        }
        else if(room.getAliensInside().size() > 0){
            addLog("Selected room cannot be sealed: There are aliens inside!");
            return false;
        }
        else if(room.getMembersInside().size() > 0){
            addLog("Selected room cannot be sealed: There are crew members inside!");
            return false;
        }
        
        addLog(room.toString() + " was sealed with success!");
        
        removeActionPoints(DEF_COST_SEAL_ROOM);
        room.setSealed(true);
       
        return true;
    }

    /**Inspiration points methods**/
    public int getInspirationPoints(){
        return player.getInspirationPoints();
    }
    
    public boolean addInspirationPoints(int quantity){
        if(quantity < 1){
            addLog("Invalid quantity!");
            return false;
        }
        
        player.setInspirationPoints(getInspirationPoints() + quantity);
        
        return true;
    }
    
    public boolean removeInspirationPoints(int quantity){
        if(quantity < 1){
            addLog("Invalid quantity!");
            return false;
        }
        
        int total = getInspirationPoints() - quantity;
        
        if(total < 0)
            total = 0;
        
        player.setInspirationPoints(total);
     
        return true;
    }
    
    public void startupSpecials(){
        
        getPlayer().setInspirationPoints(DEF_INSPIRATION_POINTS);
        
        for (CrewMember cm : this.getPlayer().getCrew()) {
            if(cm instanceof MoralOfficer){
                addInspirationPoints(5-getInspirationPoints());
            }
            else if(cm instanceof ShuttlePilot){
                int extraHealth = 4;
                do{
                    addHealthToPlayer(1);
                    extraHealth--;
                    
                }while(extraHealth > 0);
            }
        }  
    }
    
    /**Inspiration methods**/
    public boolean IP_addHealthPoint(){
        
        int quant = 1;
        
         if(player.getInspirationPoints() < DEF_COST_I_ADD_HEALTH){
            addLog("Not enough IP (Inspiration Points)!");
            return false;
        }
        
        CrewMember cm = player.getCrewMember(this.getActiveCrewMember()-1);
        
        //Doctor can add 2 health for 1 IP if in rest phase
        if(cm instanceof Doctor){
            quant = 2;
            if(!addHealthToPlayer(quant)){
                quant = 1;
                if(!addHealthToPlayer(quant)){
                    addLog("Error adding health to player!");
                    return false;
                }
            }
                
            
        }else{
            if(!addHealthToHull(quant)){
                addLog("Error adding health to player!");
                return false;
            }
        }
        
        addLog("Player was healed by " + quant + " health!");
        removeInspirationPoints(DEF_COST_I_REPAIR_HULL);
        
        return true;
    }
    
    public boolean IP_repairHull(){
        
        int quant = 1;
        
        if(player.getInspirationPoints() < DEF_COST_I_REPAIR_HULL){
            addLog("Not enough IP (Inspiration Points)!");
            return false;
        }
        
        CrewMember cm = player.getCrewMember(this.getActiveCrewMember()-1);
        
        //Egineer can add 2 health for 1 IP if in rest phase
        if(cm instanceof Engineer){
            quant = 2;
            if(!addHealthToHull(quant)){
                quant = 1;
                if(!addHealthToHull(quant)){
                    addLog("Error adding health to hull!");
                    return false;
                }
            } 
        }else{
            if(!addHealthToHull(quant)){
                addLog("Error adding health to hull!");
                return false;
            }
        }
       
        removeInspirationPoints(DEF_COST_I_REPAIR_HULL);
        addLog("Ship's hull was fixed by " + quant + " health!");
        
        return true;
    }
    
    public boolean IP_buildOrganicDetonator(){
        if(player.getInspirationPoints() < DEF_COST_I_BUILD_TRAP_ORGANIC){
            addLog("Not enough IP (Inspiration Points)!");
            return false;
        }
        
        addOrganicTrapTokens(1);
        removeInspirationPoints(DEF_COST_I_BUILD_TRAP_ORGANIC);
        addLog("1 'Organic Detonator' Token was added to your inventory!");
        
        return true;
    }
    
    public boolean IP_addMovement(int crewNumber){
        if(player.getInspirationPoints() < DEF_COST_I_ADD_MOVEMENT){
            addLog("Not enough IP (Inspiration Points)!");
            return false;
        }
        
        CrewMember cm = player.getCrewMember(crewNumber-1);
        if(cm == null){
            addLog("Selected crew member doesn't exist!");
            return false;
        }
        
        if(!cm.setMovement(cm.getMovement() + 1)){
            addLog("Selected crew member movement is already maxed!");
            return false;
        }
        
        
        removeInspirationPoints(DEF_COST_I_ADD_MOVEMENT);
        addLog(cm.getName() + " has now " + cm.getMovement() + " movement!");
        
        return true;
    }
    
    public boolean IP_buildParticleDesperser(){
        if(player.getInspirationPoints() < DEF_COST_I_BUILD_TRAP_PARTICLE){
            addLog("Not enough IP (Inspiration Points)!");
            return false;
        }
        
        if(!addParticleTrapTokens(1)){
            //Already has logs inside addParticleTrapTokens method
            return false;
        }
        
        removeInspirationPoints(DEF_COST_I_BUILD_TRAP_PARTICLE);
        addLog("1 'Particle Dispenser' Token was added to your inventory!");
        
        return true;
    }
    
    public boolean IP_addSealedRoomToken(){
        if(player.getInspirationPoints() < DEF_COST_I_ADD_SEALED_TOKEN){
            addLog("Not enough IP (Inspiration Points)!");
            return false;
        }
        
        player.setRoomSealTokens(player.getRoomSealTokens() + 1);
        
        removeInspirationPoints(DEF_COST_I_ADD_SEALED_TOKEN);
        addLog("1 'Seal Room' Token was added to your inventory!");
        
        return true;
    }
    
    public boolean IP_addAttackDie(int crewNumber){
        if(player.getInspirationPoints() < DEF_COST_I_ADD_ATTACK_DIE){
            addLog("Not enough IP (Inspiration Points)!");
            return false;
        }
        
        CrewMember cm = player.getCrewMember(crewNumber-1);
        if(cm == null){
            addLog("Selected crew member doesn't exist!");
            return false;
        }
        
        if(!cm.setAttack(cm.getAttack() + 1)){
            addLog("Selected crew member attack is already maxed!");
            return false;
        }
        
        removeInspirationPoints(DEF_COST_I_ADD_ATTACK_DIE);
        addLog(cm.getName() + " has now " + cm.getAttack() + " attack!");
        
        return true;
    }
    
    public boolean IP_addValueToAttackDie(){
       if(player.getInspirationPoints() < DEF_COST_I_ADD_VALUE_ATTACK_DIE){
            addLog("Not enough IP (Inspiration Points)!");
            return false;
        }
        
        if(!player.setAttackBuff(player.getAttackBuff()+1)){
            addLog("Attack buff is already maxed!");
            return false;
        }
        
        removeInspirationPoints(DEF_COST_I_ADD_VALUE_ATTACK_DIE);
        addLog("Minimum roll to attack an alien is now " + player.getAttackBuff() + "+");
        
        return true;
    }
    
    public boolean moveAliens(){
               
        Room room;
        
        int[] movedAliensCount = new int[NUM_ROOMS];
        int[] killedAliensCount = new int[NUM_ROOMS];
        int playerHealthLoss=0;
        int hullIntegrityLoss=0;
        int inspirationPointsEarned = 0;
        
        for(Alien alien:ship.getAllAliens()){
            
            //CHECK ORGANIC TRAP IN ROOM
            room = alien.getRoom();
            
            if(room.getTrapInside() != null && room.getTrapInside() instanceof OrganicDetonator){
                room.removeAlienFromRoom(alien);
                room.removeTrap();
                ship.getAllAliens().remove(alien);
                addInspirationPoints(1);
                killedAliensCount[room.getId()-1]++;
                inspirationPointsEarned++;
            }
            else{
                 room = alien.getRoom().chooseClosestRoom_Priority();
            
                //MOVE ALIEN

                if(room != null){
                    alien.enterRoom(room);
                    movedAliensCount[room.getId()-1]++;
                }


                //CHECK FOR TRAPS
                if(alien.getRoom().getTrapInside() != null && alien.getRoom().getTrapInside() instanceof OrganicDetonator){
                    alien.getRoom().removeAlienFromRoom(alien);
                    alien.getRoom().removeTrap();
                    addInspirationPoints(1);
                    killedAliensCount[alien.getRoom().getId()-1]++;
                    inspirationPointsEarned++;
                }
                //CHECK FOR CREW MEMBERS
                else if(!alien.getRoom().getMembersInside().isEmpty()){
                    if(rollDie(1) >= 5){
                        removeHealthFromPlayer(1);
                        playerHealthLoss++;
                    }

                    resetDices();
                }
                //CHECK FOR EMPTY ROOM
                else if(alien.getRoom().getMembersInside().isEmpty()){
                    if(rollDie(1) >= 5){
                        removeHealthFromHull(1);
                        hullIntegrityLoss++;
                    }

                    resetDices();
                }
            } 
        }
        
        //Add Log for Moved Alien Count
        for(int i = 0; i < NUM_ROOMS; i++){
            if(movedAliensCount[i] > 0){
                if(movedAliensCount[i] == 1)
                    addLog("An alien has moved to " + ship.getRoom(i+1));
                else
                    addLog(movedAliensCount[i] + " aliens have moved to " + ship.getRoom(i+1));  
            }
            if(killedAliensCount[i] > 0){
                if(killedAliensCount[i] == 1)
                    addLog("An alien has BOOMED in " + ship.getRoom(i+1) + ", due to an organic detonator!");
                else
                    addLog(killedAliensCount[i] + " aliens have BOOMED in" + ship.getRoom(i+1) + ", due to an organic detonator!");
            }
        }
        if(playerHealthLoss > 0){
            if(playerHealthLoss == 1)
                addLog("An alien attacked you! You lost 1 health!");
            else
                addLog(playerHealthLoss + " aliens attacked you! You lost " + playerHealthLoss + " health!");
        }
        if(hullIntegrityLoss > 0){
            if(hullIntegrityLoss == 1)
                addLog("An alien attacked the ship! Ship's hull lost 1 health!");
            else
                addLog(hullIntegrityLoss + " aliens attacked the ship! Ship's hull lost " + hullIntegrityLoss + " health!");
        }
        if(inspirationPointsEarned > 0){
            if(inspirationPointsEarned == 1)
                addLog("You earned 1 Inspiration Point (IP)!");
            else
                addLog("You earned " + inspirationPointsEarned + "Inspiration Points (IPs)!");
        }
        
        return true;
    }
    
    /**Object methods**/
    @Override
    public String toString()
    {
        String s;
        
        //s = "Destination Earth, playing as " + this.getPlayer().getName() + System.lineSeparator();
        s= "Turn: " + getCurrentTurn() + ", Hull Integrity: " + getShip().getHullTracker() + System.lineSeparator();
        s+= "IP: " + getPlayer().getInspirationPoints() + ", AP: " + getPlayer().getActionPoints() + ", Health: " + getPlayer().getHealthTracker() + System.lineSeparator();
        s+= diceToString() + System.lineSeparator();
        
        return s;
    }
}
