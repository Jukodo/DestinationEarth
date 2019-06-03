package de.logic.data;

import de.logic.data.members.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.paint.Color;

public class DataGame implements Constants, Serializable{
    private Player player;
    private Ship ship;
    private int aliensCount;
    private List<String> logs;
    private String [] journeyTracker;
    private int currentTurn;
    private boolean turnScanned;
    private int dices[];
    private int activeCrewMember;
    
    private List<Alien> newAliens;
    private int activeNewAlien;
    private boolean specialSpawn;
    
    private int activeJourneyTurn;
   
    public DataGame() {
        logs = new ArrayList <> ();
        journeyTracker = new String[NUM_TURNS];
       
        currentTurn = 0;
        ship = new Ship(this);
        dices = new int[MAX_DICES];
        
        activeCrewMember = 1;
        activeNewAlien = 1;
        activeJourneyTurn = 1;
        
        newAliens = new ArrayList<>();
        
        specialSpawn = false;
        
        turnScanned = false;
        
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
    
    public int getActiveJourneyTurn() {
        return activeJourneyTurn;
    }

    public List<Alien> getNewAliens() {
        return newAliens;
    }
    
    public boolean getTurnScanned(){
        return turnScanned;
    }
  
    /**Methods**/    
    public void nextTurn(){
        for(int i = 0; i < NUM_CREW_MEMBERS; i++){
            player.getCrewMember(i).setCurrentMovement(player.getCrewMember(i).getMovement());
        }
        
        if(specialSpawn)
            despawnAliens();
        turnScanned = false;
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
               
        if((dieId < 0 || dieId >= MAX_DICES) || (value < 0 || value > 6)){
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
        
        addLog("You rolled " + quantityOfDice + " and got " + getDiceValue(quantityOfDice));
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
        
        int color=0;//KEEP THE COLOR OF THE CLASS BEFORE (IF BEING CHANGED INSTEAD OF CREATED FOR THE FIRST TIME)
        CrewMember oldMember = getPlayer().getCrewMember(crewNumber);
        Color customColor = null;
        if(oldMember != null){
            color = oldMember.getColor();
            if(color == -1)
                customColor = oldMember.getCustomColor();
        }
        
        CrewMember member = null;
            
        switch(crewType){
            case CAPTAIN:
                member = new Captain(this, color);
                break;
            case COMMANDER:
                member = new Commander(this, color);
                break;
            case COOMS_OFFICER:
                member = new CommsOfficer(this, color);
                break;
            case DOCTOR:
                member = new Doctor(this, color);
                break;
            case ENGINEER:
                member = new Engineer(this, color);
                break;
            case MORAL_OFFICER:
                member = new MoralOfficer(this, color);
                break;
            case NAVIGATION_OFFICER:
                member = new NavigationOfficer(this, color);
                break;
            case RED_SHIRT:
                member = new RedShirt(this, color);
                break;
            case SCIENCE_OFFICER:
                member = new ScienceOfficer(this, color);
                break;
            case SECURITY_OFFICER:
                member = new SecurityOfficer(this, color);
                break;
            case SHUTTLE_PILOT:
                member = new ShuttlePilot(this, color);
                break;
            case TRANSPORTER_CHIEF:
                member = new TransporterChief(this, color);
                break;
            default:
                addLog("Crew Member class is invalid!");
                return false;
        }
        
        if(member != null){
            if(customColor != null)
                member.setCustomColor(customColor);
            getPlayer().setCrewMember(crewNumber, member);
        }
        return true;
    }
    
    public boolean selectCrewMemberColor(int crewNumber, int crewMemberColor){
        if(crewMemberColor < 0 || crewMemberColor > 12){
            addLog("Crew Member color is invalid!");
            return false;
        }

        if(getPlayer().getCrewMember(crewNumber-1) == null){
            addLog("Crew Member selected doesn't exist!");
            return false;
        }
        
        getPlayer().getCrewMember(crewNumber-1).setColor(crewMemberColor-1);
        return true;
    }
    
    public boolean selectCrewMemberColor(int crewNumber, Color crewMemberColor){
        if(getPlayer().getCrewMember(crewNumber-1) == null){
            addLog("Crew Member selected doesn't exist!");
            return false;
        }
        
        getPlayer().getCrewMember(crewNumber-1).setColor(-1);
        getPlayer().getCrewMember(crewNumber-1).setCustomColor(crewMemberColor);
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
    
    public int getRequiredMoves(int roomFrom, int roomTo){
        int requiredMoves = 0;
        
        List<Room> layer1 = new ArrayList<>();
        List<Room> layer2 = new ArrayList<>();
        List<Room> seenRooms = new ArrayList<>();

        layer1.addAll(player.getCrewMember(activeCrewMember-1).getRoom().getClosestRooms());

        for(int i = 0; i < player.getCrewMember(activeCrewMember-1).getMovement(); i++){
            requiredMoves++;
            for(Room neighbor:layer1){
                if(!neighbor.getIsSealed() && !seenRooms.contains(neighbor) && neighbor != player.getCrewMember(activeCrewMember-1).getRoom()){
                    if(neighbor.getId() == roomTo)
                        return requiredMoves;
                    seenRooms.add(neighbor);
                    for(Room _neighbor:neighbor.getClosestRooms()){
                        if(!_neighbor.getIsSealed() && !seenRooms.contains(_neighbor)){
                            layer2.add(_neighbor);
                        }
                    }
                }
            }
            if(layer2.isEmpty())
                break;
            layer1 = new ArrayList<>(layer2);
            layer2.clear();
        }
        
        return 0;
    }
    
    public List<Room> getPossibleRooms(int crewMember){
        List<Room> possibleRooms;
        
        if(player.getCrewMember(crewMember) instanceof TransporterChief){
            possibleRooms = new ArrayList<>(ship.getRooms().values());
        }else{
            List<Room> layer1 = new ArrayList<>();
            List<Room> layer2 = new ArrayList<>();
            possibleRooms = new ArrayList<>();

            layer1.addAll(player.getCrewMember(crewMember).getRoom().getClosestRooms());
            
            for(int i = 0; i < player.getCrewMember(crewMember).getCurrentMovement(); i++){
                for(Room neighbor:layer1){
                    if(!neighbor.getIsSealed() && !possibleRooms.contains(neighbor) && neighbor != player.getCrewMember(crewMember).getRoom()){
                        possibleRooms.add(neighbor);
                        for(Room _neighbor:neighbor.getClosestRooms()){
                            if(!_neighbor.getIsSealed() && !possibleRooms.contains(_neighbor)){
                                layer2.add(_neighbor);
                            }
                        }
                    }
                }
                if(layer2.isEmpty())
                    break;
                layer1 = new ArrayList<>(layer2);
                layer2.clear();
            }
        }
            
        return possibleRooms;
    }
    
    public List<Room> getRooms_ToAttack(int crewMember){
        List<Room> possibleRooms;
        
        possibleRooms = new ArrayList<>();
        
        if(player.getCrewMember(crewMember).getRoom().getAliensInside().size() > 0 && !player.getCrewMember(crewMember).getRoom().getIsSealed()){
            possibleRooms.add(player.getCrewMember(crewMember).getRoom());
        }
        
        if(activeIsScienceOfficer()){
    
            
            for(Room neighbor:player.getCrewMember(crewMember).getRoom().getClosestRooms()){
                if(!neighbor.getIsSealed() && neighbor.getAliensInside().size() > 0){
                    possibleRooms.add(neighbor);
                }
            } 
        }
            
        return possibleRooms;
    }
    
    public List<Room> getRooms_ToPlaceTrap(int crewMember){
        List<Room> possibleRooms;
        
        possibleRooms = new ArrayList<>();
        
        if(player.getCrewMember(crewMember).getRoom().getAliensInside().size() > 0 && !player.getCrewMember(crewMember).getRoom().getIsSealed()){
            possibleRooms.add(player.getCrewMember(crewMember).getRoom());
        }
            
        return possibleRooms;
    }
    
    public boolean crewClassNotRepeated(){
        for(int i=0; i<player.getCrew().length-1; i++){
            if(player.getCrewMember(i).getName().equals(player.getCrewMember(i+1).getName())){
                addLog("Your crew has two equal members! Please change one of them...");
                return false;
            }
        }
        return true;
    }
    
    public boolean crewColorNotRepeated(){
        for(int i=0; i<player.getCrew().length-1; i++){
            if(player.getCrewMember(i).getColor() == CUSTOM_COLOR){
                if(player.getCrewMember(i).getCustomColor() == player.getCrewMember(i+1).getCustomColor()){
                    addLog("Your crew has two equal colors! Please change one of them...");
                    return false;
                }
            }else{
                if(player.getCrewMember(i).getColor() == player.getCrewMember(i+1).getColor()){
                    addLog("Your crew has two equal colors! Please change one of them...");
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean swapActiveCrewMember(){
        int tempIndex = activeCrewMember+1;
        if(tempIndex > NUM_CREW_MEMBERS)
            tempIndex = 1;
        
        if(this.getPlayer().getCrewMember(tempIndex-1) instanceof RedShirt && 
                !((RedShirt)this.getPlayer().getCrewMember(tempIndex-1)).isAlive())
            return false;
            
        if(++activeCrewMember > NUM_CREW_MEMBERS)
            activeCrewMember = 1;
        
        return true;
    }
    
    public boolean swapActiveCrewMember(int index){
        int tempIndex = activeCrewMember+1;
        if(tempIndex > NUM_CREW_MEMBERS)
            tempIndex = 1;
        
        if(this.getPlayer().getCrewMember(tempIndex-1) instanceof RedShirt && 
                !((RedShirt)this.getPlayer().getCrewMember(tempIndex-1)).isAlive())
            return false;
            
        if(++activeCrewMember > NUM_CREW_MEMBERS)
            activeCrewMember = 1;
        
        return true;
    }
    
    public boolean swapActiveJourneyTurn(int index){
        activeJourneyTurn = index;
        
        return true;
    }
    
    public boolean sacrificeCrewMember(){
        for(CrewMember cm:player.getCrew()){
            if(cm instanceof RedShirt){
                if(cm.special())
                    return true;
            }
        }
        
        return false;
    }
    
    public boolean activeIsDoctor(){
        CrewMember cm = player.getCrewMember(activeCrewMember-1);
        
        if(cm instanceof Doctor)
            return true;
        
        return false;
    }
    
    public boolean activeIsEngineer(){
        CrewMember cm = player.getCrewMember(activeCrewMember-1);
        
        if(cm instanceof Engineer)
            return true;
        
        return false;
    }
      public boolean activeIsScienceOfficer(){
        CrewMember cm = player.getCrewMember(activeCrewMember-1);
        
        if(cm instanceof ScienceOfficer)
            return true;
        
        return false;
    }
    
    /**Aliens methods**/
    
    public void decreaseAliensCount(){
        aliensCount--;
        
        if(aliensCount < 0)
            aliensCount = 0;
    }
    
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
    
    public boolean eventIsRest(String event){
        if(event.trim().compareToIgnoreCase("R") == 0)//Event is 'R'
            return true;
        return false;
    }
    
    public boolean eventIsAlienSpawn(int turn, String event){
        if(eventIsAlienSpawnSpecial(event))//Despawn aliens after turn ends
            specialSpawn = true;
        
        if(event.trim().matches("[1-9]+A[*]?")){//Event is an Alien Spawn - Has valid format
            int numAliens = getAlienSpawnNumber(event);
            if(numAliens >= MIN_SPAWN_ALIENS_TURN[turn-1] && numAliens <= MAX_SPAWN_ALIENS_TURN[turn-1])//Number of aliens to spawn is accepted
                return true;
        }
        return false;
    }
    
    public boolean eventIsAlienSpawnSpecial(String event){
        return event.contains("*");
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
            if(!isValid_JourneyTurn(i+1, journeyTracker[i]))
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
                sb.append((int) (MIN_SPAWN_ALIENS_TURN[i] + (Math.random() * (MAX_SPAWN_ALIENS_TURN[i] - MIN_SPAWN_ALIENS_TURN[i] + 1))));
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
        
        if(room.getIsSealed()){
            return false;
        }
        
        Alien alien = new Alien();
        alien.enterRoom(room);
        
        newAliens.add(alien);
        aliensCount++;
        
        return true;
    }
    
    public boolean spawnAliens(int numAliens){
        int[] spawnedAliensCount = new int[NUM_ROOMS];
        int[] canceledAliensCount = new int[NUM_ROOMS];
        
        for(int i = 0; i < numAliens; i++){
            rollDice(2);
            if(spawnAlien(ship.getRoom(getDiceValue(2)))){
                spawnedAliensCount[getDiceValue(2)]++;
            }else{
                canceledAliensCount[getDiceValue(2)]++;
            }
            resetDices();
        }
        
        for(int i = 0; i < NUM_ROOMS; i++){
            //Spawned
            if(spawnedAliensCount[i] > 0){
                if(spawnedAliensCount[i] == 1)
                    addLog("An alien has spawned at " + ship.getRoom(i));
                else
                    addLog(spawnedAliensCount[i] + " aliens have spawned at " + ship.getRoom(i));
            }
            //Canceled
            if(canceledAliensCount[i] > 0){
                if(canceledAliensCount[i] == 1)
                    addLog("An alien failed to spawn because " + ship.getRoom(i) + " is sealed");
                else
                    addLog(canceledAliensCount[i] + " aliens failed to spawn because " + ship.getRoom(i) + " is sealed");  
            }
        }
        
        turnScanned = true;
        return true;
    }
    
    public void despawnAliens(){
        for(int i = 1; i <= NUM_ROOMS; i++){
            if(!ship.getRoom(i).getAliensInside().isEmpty()){
                aliensCount = aliensCount - ship.getRoom(i).getAliensInside().size();
                ship.getRoom(i).getAliensInside().clear();
            }
        }
        specialSpawn = false;
    }
    
    public boolean placeNewAlien(int alienNumber, int roomNumber){
        alienNumber--; //ARRAY INDEX = -1 of its number
        resetDices();
        
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
        
        if(room.getIsSealed()){
            addLog("Room selected is sealed!");
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
        if(player.have_RedShirt(false, true))
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
        if(player.have_RedShirt(false, true)){
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
            else if(cm instanceof Doctor){
                ((Doctor)cm).setHasHealedForFree(false);
            }
            else if(cm instanceof Engineer){
                ((Engineer)cm).setHasFixedForFree(false);
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
        
        if(getHealthTracker() >= MAX_HEALTH){
            addLog("Player already has max health!");
            return false;
        }
        
        if(getHealthTracker() + quantity > MAX_HEALTH)//IF 10 + 4 > 12
            quantity = MAX_HEALTH - getHealthTracker();//QUANT = 12 - 10 / QUANT = 2
        
        int total = getHealthTracker() + quantity;
            
        
        return player.setHealthTracker(total);
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
        
        if(getHullTracker() >= MAX_HULL){
            addLog("Ship already has max integrity!");
            return false;
        }
        
        if(getHullTracker() + quantity > MAX_HULL)//IF 10 + 4 > 12
            quantity = MAX_HULL - getHullTracker();//QUANT = 12 - 10 / QUANT = 2
        
        int total = getHullTracker() + quantity;
        
        if(total > MAX_HULL){
            addLog("Ship already has max hulls!");
            return false;
        }
        
        return ship.setHullTracker(getHullTracker() + quantity);
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
    
    public boolean addSealedRoomTokens(int quantity){
       if(quantity < 1){
            addLog("Invalid quantity!");
            return false;
        }
       
       if(player.getRoomSealTokens() == MAX_SEALED_ROOMS){
            addLog("Cannot own more sealed rooms, max reached!");
            return false;
        }

       player.setRoomSealTokens(player.getRoomSealTokens() + quantity);
       return true;
   }

   public boolean removeSealedTokens(int quantity){
       if(quantity < 1){
            addLog("Invalid quantity!");
            return false;
        }

       int total = player.getRoomSealTokens()- quantity;

       if(total < 0)
           total = 0;

       player.setRoomSealTokens(total);

       return true;
   }
    
    /**Actions methods**/
    //Usar este metodo no UI para mostrar o custo do movimento
    public int getMovementCost(){
        CrewMember cm = player.getCrewMember(activeCrewMember-1);
        
        int freeMoves = cm.getMovement() - DEF_COST_A_MOVE;
        
        if(freeMoves <= 0){
            return DEF_COST_A_MOVE;
        }else if(cm.getMovementsBeforeFree() > 0 && cm.getMovementsBeforeFree() <= freeMoves){
            return 0; //Quando o Navigation Officer move-se 1 vez pode mover-se mais 1 vez de graça.
        }
        
        return DEF_COST_A_MOVE;
    }
    
    public int getHealCost(){
        CrewMember cm = player.getCrewMember(activeCrewMember-1);
        
        if(cm instanceof Doctor && cm.getRoom().getName().equalsIgnoreCase("Sick Bay") && !((Doctor)cm).hasHealedForFree()){
            return 0;
        }
        
        return DEF_COST_A_HEAL;
    }
    
    public int getFixHullCost(){
        CrewMember cm = player.getCrewMember(activeCrewMember-1);
        
        if(cm instanceof Engineer && cm.getRoom().getName().equalsIgnoreCase("Engineering") && !((Engineer)cm).hasFixedForFree()){
            return 0;
        }
        
        return DEF_COST_A_FIX_HULL;
    }
    
    public boolean moveActiveCrewMember(int roomNumber){
        return player.getCrewMember(activeCrewMember-1).move(roomNumber);
    }
    

    public int attackAliens(int roomNumber){
        return player.getCrewMember(activeCrewMember-1).attack(roomNumber);
    }
    
    public boolean healPlayer(){
        CrewMember cm = player.getCrewMember(activeCrewMember-1);
        
        if(!cm.special()){
            addLog("Selected crew member cannot execute this action!");
            return false;
        }
        
        return true;
    }
    
    public boolean fixHullTracker(){
        CrewMember cm = player.getCrewMember(activeCrewMember-1);
        
        if(!cm.special()){
            addLog("Selected crew member cannot execute this action!");
            return false;
        }
        
        return true;

    }
    
    public boolean placeTrap(int trapType){
        CrewMember cm = player.getCrewMember(activeCrewMember-1);
        
        return cm.placeTrap(trapType);
    }
    
    public boolean detonateParticleDispenser(int roomNumber){
        CrewMember cm = player.getCrewMember(activeCrewMember-1);
        
        return cm.detonateParticleDispenser(roomNumber);
        /*if(roomNumber < 1 || roomNumber > NUM_ROOMS){
            addLog("Selected room doesn't exist!");
            return false;
        }
        
        if(getActionPoints() < DEF_COST_A_DETONATE_TRAP_PARTICLE){
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
        
        if(nAliens > 0){
            roomToBoom.removeAllAliens();
            aliensCount = aliensCount - nAliens;
        }
        
        
        if(roomToBoom.getMembersInside().size() > 0){
            removeHealthFromPlayer(player.getHealthTracker());
        }
        
        removeActionPoints(DEF_COST_A_DETONATE_TRAP_PARTICLE);
        roomToBoom.removeTrap();
        
        addLog("Particle Dispenser detonated with success!" + " You killed " + nAliens + " aliens");
        
        return true;*/
    }
    
    public boolean sealRoom(int roomNumber){
        
        CrewMember cm = player.getCrewMember(activeCrewMember-1);
        
        return cm.sealRoom(roomNumber);
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
    
    
    public int getAvailableInspirations_Quant(){
        int i = DEF_INSPIRATIONS.length;
        
        for(CrewMember cm:player.getCrew()){
            if(cm instanceof RedShirt && ((RedShirt)cm).isAlive())
                i++;
        }
        return i;
    }
    
    public String getAvailableInspirations(){
        String s = "";
        int i;
        
        for(i = 0; i < DEF_INSPIRATIONS.length; i++){
            
            s+= (i+2) + " - " + DEF_INSPIRATIONS[i] + "(";
            s+= DEF_INSPIRATIONS_COST[i];
            
            s+= " IP)" + System.lineSeparator();
            
        }
        
        if(getPlayer().have_RedShirt(false, true)){
            s += (i+2) + " - Sacrifice Red Shirt (0 IP)" + System.lineSeparator();
        }
        
        return s;
    }
    
    public void startupSpecials(){
        
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
        
        //Doctor can add 2 health for 1 IP if in rest phase
        if(player.have_Doctor(false)){
            quant = 2;
            if(!addHealthToPlayer(quant)){
                quant = 1;
                if(!addHealthToPlayer(quant)){
                    return false;
                }
            }
        }else{
            if(!addHealthToPlayer(quant)){
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
        
        if(!addOrganicTrapTokens(1))
                return false;
        
        removeInspirationPoints(DEF_COST_I_BUILD_TRAP_ORGANIC);
        addLog("1 'Organic Detonator' Token was added to your inventory!");
        
        return true;
    }
    
    public boolean IP_addMovement(){
        if(player.getInspirationPoints() < DEF_COST_I_ADD_MOVEMENT){
            addLog("Not enough IP (Inspiration Points)!");
            return false;
        }
        
        CrewMember cm = player.getCrewMember(getActiveCrewMember()-1);
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
        
        if(!addSealedRoomTokens(1))
            return false;
        //player.setRoomSealTokens(player.getRoomSealTokens() + 1);
        
        removeInspirationPoints(DEF_COST_I_ADD_SEALED_TOKEN);
        addLog("1 'Seal Room' Token was added to your inventory!");
        
        return true;
    }
    
    public boolean IP_addAttackDie(){
        System.out.println("Increasing attack dice");
        
        if(player.getInspirationPoints() < DEF_COST_I_ADD_ATTACK_DIE){
            addLog("Not enough IP (Inspiration Points)!");
            return false;
        }
        
        CrewMember cm = player.getCrewMember(activeCrewMember -1);
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
                aliensCount--;
                addInspirationPoints(1);
                killedAliensCount[room.getId()-1]++;
                inspirationPointsEarned++;
            }
            else{
                room = alien.getRoom().chooseClosestRoom();
                
                //MOVE ALIEN

                if(room != null){
                    alien.enterRoom(room);
                    movedAliensCount[room.getId()-1]++;
                }


                //CHECK FOR TRAPS
                if(alien.getRoom().getTrapInside() != null && alien.getRoom().getTrapInside() instanceof OrganicDetonator){
                    alien.getRoom().removeAlienFromRoom(alien);
                    alien.getRoom().removeTrap();
                    aliensCount--;
                    addInspirationPoints(1);
                    killedAliensCount[alien.getRoom().getId()-1]++;
                    inspirationPointsEarned++;
                }
                //CHECK FOR CREW MEMBERS
                else if(!alien.getRoom().getMembersInside().isEmpty()){
                    if(rollDie(1) >= 5){
                        if(player.have_CommsOfficer(false)){
                            if(rollDie(1) > 2){
                                removeHealthFromPlayer(1);
                                playerHealthLoss++;
                            }else{
                                addLog("An Alien tried to attack a Comms Officer, but he dodged! No health loss...");
                            }
                        }else{
                            removeHealthFromPlayer(1);
                            playerHealthLoss++;
                        }
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
    
    public String getColorToString(int color){
        return COLOR[color];
    }
    
    public String getCrewMemberColorToString(int crewMember){
        if(player.getCrewMember(crewMember).getColor() == -1)
            return "Custom Color";
        else
            return getColorToString(player.getCrewMember(crewMember).getColor());
    }
    
    public String crewMemberInfoToString(){
        String s = "\tCrew Members:" + System.lineSeparator();
        
        for(int i=0; i < NUM_CREW_MEMBERS; i++){
            if(i>0)
                s += System.lineSeparator();
            s += "\t";
            if(i+1 == getActiveCrewMember())
                s += "[X] ";
            else
                s += "[ ] ";
            s += "Member #" + (i+1) + ": ";
            if(player.getCrewMember(i) != null){
                if(player.getCrewMember(i) instanceof RedShirt && !((RedShirt)player.getCrewMember(i)).isAlive()){
                    s += player.getCrewMember(i).getName() + " (" + getCrewMemberColorToString(i) + "), was sacrificed...";
                }else{
                    if(player.getCrewMember(i).isInside()){
                        s += player.getCrewMember(i).getName() + " (" + getCrewMemberColorToString(i) + "), is at " + player.getCrewMember(i).getRoom();
                    }else{
                        s += player.getCrewMember(i).getName() + " (" + getCrewMemberColorToString(i) + "), isn't at any Room, please select one!";
                    }
                }
            }else{
                s += " Not selected";
            }
        }
        
        return s;
    }
    
    public String newAliensInfoToString(){
        String s = "\tNew Aliens:" + System.lineSeparator();
        
        int i = 0;
        for(Alien alien:getNewAliens()){
            if(i>0)
                s += System.lineSeparator();
            s += "\t";
            if(i+1 == getActiveNewAlien())
                s += "[X] ";
            else
                s += "[ ] ";
            s += "Alien #" + (i+1) + " ";
            if(alien.isInside()){
                s += ", is at Room #" + alien.getRoom().getId() + " - " + alien.getRoom().getName();
            }else{
                s += ", isn't at any Room, please select one!";
            }

            i++;
        }
        
        return s;
    }
    
    public String currentJourneyToString(){
        String s;
        
        s = "\tCurrent Journey: ";
            for(int i = 1; i <= NUM_TURNS; i++){
                s += getJourneyTrackerTurn(i);
                if(i != NUM_TURNS)
                    s += " -> ";
            }
            
        return s;
    }
    
    public String activeCrewMemberInfoToString(){
        String s;
        
        s = "Active Member: ";
        if(player.getCrewMember(getActiveCrewMember()-1) != null){
            s += player.getCrewMember(getActiveCrewMember()-1).getName()+ " (" + getCrewMemberColorToString(getActiveCrewMember()-1) + "), ";

            if(player.getCrewMember(getActiveCrewMember()-1).getRoom() == null){
                s+= "isn't at any Room";
            }else{
                s+= player.getCrewMember(getActiveCrewMember()-1).getRoom();
            }
        }else{
            s += " Not selected";
        }
        
        return s;
    }
    
    public String trapTokensToString(){
        String s;
        
        s = "Organic Traps: " + getOrganicTrapTokens() 
          + " Particle Dispensers: " + getParticleTrapTokens();
        
        return s;
    }
    
    public String sealTokensToString(){
        String s;
        
        s = "Seal Room Tokens: " + player.getRoomSealTokens();
        
        return s;
    }
    
    /**Object methods**/
    @Override
    public String toString()
    {
        String s;
  
        s = "Player: " + getPlayer().getName();
        
        if(getCurrentTurn() == 0)
            s += ", Turn: START";
        else
            s += ", Turn: " + getCurrentTurn() ;
        
        s += ", Hull Integrity: " + getShip().getHullTracker() 
                + ", Total aliens inside: " + getAliensCount()
                + System.lineSeparator();
        
        s+= "IP: " + getPlayer().getInspirationPoints() 
                + ", AP: " + getPlayer().getActionPoints() 
                + ", Health: " + getPlayer().getHealthTracker() 
                + System.lineSeparator();
        s+= diceToString() + System.lineSeparator();
        
        s += System.lineSeparator() + currentJourneyToString() + System.lineSeparator();
        
        return s;
    }
}
