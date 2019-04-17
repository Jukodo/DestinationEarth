package de.ui.text;

import de.DestinationEarth;
import de.logic.data.Alien;
import static de.logic.data.Constants.*;
import de.logic.data.OrganicDetonator;
import de.logic.data.ParticleDispenser;
import de.logic.data.Room;
import de.logic.data.Trap;
import de.logic.data.members.CrewMember;
import de.logic.states.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TextUI {
    
    private DestinationEarth game;
    private boolean quit = false;
    private boolean inDebug = true;

    public TextUI(DestinationEarth game) 
    {
        this.game = game;
    }
    
    public void uiBeginning() throws IOException, ClassNotFoundException 
    {
        
        Scanner sc = new Scanner(System.in);
        String input;
        char c;
        
        //while(true){
            
        System.out.println("Welcome to Destination Earth, please select an option");
        
        do{
            System.out.println();
            System.out.println("0 - Quit");
            System.out.println();
            System.out.println("1 - New game");
            System.out.println("2 - Load game");
            System.out.println("3 - Settings");
            System.out.println();
            System.out.print("~>: ");

            input = sc.next();

            if(input.length() >= 1)
                c = input.charAt(0);
            else
                c = ' ';

        }while(c < '0' || c > '3');

        switch(c){

            case '0':
                quit = true;
                return;

            case '1':

                System.out.println();
                System.out.print("Please insert your player name: ");
                
                input = sc.next();

                if(input.length() > 0){
                    game.start(input);
                }

                return;
                
            default:
                return;

        }
        
    }
    
    
    public void uiDiceRolling() throws IOException, ClassNotFoundException{
        
        Scanner sc = new Scanner(System.in);
        String input;
        char c;

        System.out.println();
        System.out.println(game.diceToString());
        System.out.println("Select a way to roll your die(s): ");

        do {
            
            System.out.println();
            System.out.println("0 - Quit");
            System.out.println();
            System.out.println("1 - Roll randomly");
            if(inDebug)
                System.out.println("2 - Set roll value");
            System.out.println();
            System.out.print("~>: ");

            input = sc.next();

            if(input.length() >= 1)
                c = input.charAt(0);
            else
                c = ' ';

        }while(c < '0' || c > '2');
            
        switch(c){

            case '0':
                quit = true;
                return;

            case '1':

                if(input.length() > 0){
                    game.rollDice(); 
                }

            case '2':
                if(inDebug){
                    System.out.println();
                    for(int i = 0; i < game.getQuantityOfDiceToRoll(); i++){
                        System.out.println("Set value for die " + (i+1) + ": ");
                        
                    }
                }

                return;

            default:
                return;

        }

    }
    
    public void uiCrewSelection() throws IOException, ClassNotFoundException{
        //REMOVE LATER
        if(inDebug){
            if(!game.getDataGame().getPlayer().hasAllMembers()){
                game.selectCrewMember(1, 4);
                game.selectCrewMemberColor(1, 2);
                game.selectCrewMember(2, 5);
                game.selectCrewMemberColor(2, 3);
            }
        }
        
        int op;
        String input;
        
        Scanner sc = new Scanner(System.in);
        
        CrewMember[] cm = game.getDataGame().getPlayer().getCrew();
        
        do{
            System.out.println("Crew Selection:");
            
            for(int i=0; i < NUM_CREW_MEMBERS; i++){
                if(i>0)
                    System.out.println();
                System.out.print("\t");
                if(i+1 == game.getDataGame().getActiveCrewMember())
                    System.out.print("[X] ");
                else
                    System.out.print("[ ] ");
                System.out.print("Member #" + (i+1) + ": ");
                if(cm[i] != null){
                    if(cm[i].getColor() != 0){
                        System.out.print(cm[i].getName() + " (" + COLOR[cm[i].getColor()] + ")");
                    }else{
                        System.out.print(cm[i].getName() + " (Color not selected!)");
                    }
                }else{
                    System.out.print("Class not selected!");
                }
            }
            
            System.out.println();
            System.out.println("0 - Quit");
            System.out.println("1 - Swap Selected Member");
            System.out.println("2 - Select Class");
            System.out.println("3 - Select Color");
            System.out.println("4 - Lock In Selection");
            
            System.out.println();
            System.out.print("~>: ");
            
            input = sc.next();
            
            if(input.length() >= 1)
                op = Integer.parseInt(input);
            else
                op = -1;
            
        }while(op < 0 || op > 4);


        int crewType, crewColor;
        switch(op){
            
            case 0:
                quit = true;
                return;
                
            case 1:
                game.getDataGame().swapActiveCrewMember();
                break;
                
            case 2:
                do{
                    crewType = 0;
                    System.out.println();
                    System.out.println("Pick the class for Member #" + game.getDataGame().getActiveCrewMember() + ": ");

                    System.out.println("1  - Captain");
                    System.out.println("2  - Commander");
                    System.out.println("3  - CommsOfficer");
                    System.out.println("4  - Doctor");
                    System.out.println("5  - Engineer");
                    System.out.println("6  - MoralOfficer");
                    System.out.println("7  - NavigationOfficer");
                    System.out.println("8  - RedShirt");
                    System.out.println("9  - ScienceOfficer");
                    System.out.println("10 - SecurityOfficer");
                    System.out.println("11 - ShuttlePilot");
                    System.out.println("12 - TransporterChief");

                    System.out.println();
                    System.out.print("~>: ");
                    
                    input = sc.next();
                    
                    if(input.length() >= 1)
                        crewType = Integer.parseInt(input);
                    
                }while(crewType < 1 || crewType > 12);
                
                game.selectCrewMember(game.getDataGame().getActiveCrewMember(), crewType);
                break;
                
            case 3:
                do{
                    crewColor = 0;

                    System.out.println();
                    System.out.println("Pick the color for Member #" + game.getDataGame().getActiveCrewMember() + ": ");

                    System.out.println("1  - White");
                    System.out.println("2  - Blue");
                    System.out.println("3  - Cyan");
                    System.out.println("4  - Dark Gray");
                    System.out.println("5  - Gray");
                    System.out.println("6  - Green");
                    System.out.println("7  - Yellow");
                    System.out.println("8  - Magenta");
                    System.out.println("9  - Orange");
                    System.out.println("10 - Pink");
                    System.out.println("11 - Red");
                    System.out.println("12 - Black");
                    
                    System.out.println();
                    System.out.print("~>: ");
                    
                    input = sc.next();
                    
                    if(input.length() >= 1)
                        crewColor = Integer.parseInt(input);
                    
                }while(crewColor < 1 || crewColor > 12);
                
                game.selectCrewMemberColor(game.getDataGame().getActiveCrewMember(), crewColor);
                break;
                
            case 4:
                game.confirmCrewMemberSelection();
        }
    }
    
    public void uiCrewPlacement(){
        if(game.getDataGame().getDiceValue(2) > 0)
            game.placeCrewMember(game.getDataGame().getActiveCrewMember(), game.getDataGame().getDiceValue(2));
        
        //REMOVE LATER
        if(inDebug){
            if(!game.getDataGame().getPlayer().hasAllMembersOnBoard()){
                game.placeCrewMember(1, 1);
                game.placeCrewMember(2, 2);
            }
        }
        
        int op;
        String input;
        
        Scanner sc = new Scanner(System.in);
        
        CrewMember[] cm = game.getDataGame().getPlayer().getCrew();
        
        do{
            System.out.println("Crew Placement:");
            
            for(int i=0; i < NUM_CREW_MEMBERS; i++){
                if(i>0)
                    System.out.println();
                System.out.print("\t");
                if(i+1 == game.getDataGame().getActiveCrewMember())
                    System.out.print("[X] ");
                else
                    System.out.print("[ ] ");
                System.out.print("Member #" + (i+1) + ": ");
                if(cm[i].isInside()){
                    System.out.print(cm[i].getName() + ", is at Room #" + cm[i].getRoom().getId() + " - " + cm[i].getRoom().getName());
                }else{
                    System.out.print(cm[i].getName() + ", isn't at any Room, please select one!");
                }
            }
            
            System.out.println();
            System.out.println("0 - Quit");
            System.out.println("1 - Swap Selected Member");
            System.out.println("2 - Select Room");
            System.out.println("3 - Display Ship Structure");
            System.out.println("4 - Lock In Placement");
            
            System.out.println();
            System.out.print("~>: ");
            
            input = sc.next();
            
            if(input.length() >= 1)
                op = Integer.parseInt(input);
            else
                op = -1;
            
        }while(op < 0 || op > 4);
        
        switch(op){
            
            case 0:
                quit = true;
                return;
                
            case 1:
                game.getDataGame().swapActiveCrewMember();
                break;
            
            case 2:
                game.rollDice();
                break;
            case 3:
                System.out.println(game.getDataGame().getShip().toString());
                break;
            case 4:
                game.confirmCrewMemberPlacement();
        }
    }
    
    public void uiJourneySelection(){
        int op;
        String input;
        
        Scanner sc = new Scanner(System.in);
        
        do{
            System.out.println("Journey Selection:");
            
            System.out.print("\tCurrent Journey: ");
            for(int i = 1; i <= NUM_TURNS; i++){
                System.out.print(game.getDataGame().getJourneyTrackerTurn(i));
                if(i != NUM_TURNS)
                    System.out.print(" -> ");
            }
            
            System.out.println();
            System.out.println("0 - Quit");
            System.out.println("1 - Load Default Journey");
            System.out.println("2 - Randomize Journey");
            System.out.println("3 - Edit Journey");
            System.out.println("4 - Lock In Journey");
            
            System.out.println();
            System.out.print("~>: ");
            
            input = sc.next();
            
            if(input.length() >= 1)
                op = Integer.parseInt(input);
            else
                op = -1;
            
        }while(op < 0 || op > 4);
        
        switch(op){
            
            case 0:
                quit = true;
                return;
                
            case 1:
                System.out.println("You will lose your current Journey. Continue? (Y/N): ");
                
                System.out.println();
                System.out.print("~>: ");
                
                input = sc.next();
                
                if(input.trim().compareToIgnoreCase("Y") == 0){
                    System.out.println("Changing to default");//REMOVE LATER
                    game.generateJourney_ByDefault();
                }
                
                break;
                
            case 2:
                System.out.println("You will lose your current Journey. Continue? (Y/N): ");
                
                System.out.println();
                System.out.print("~>: ");
                
                input = sc.next();
                
                if(input.trim().compareToIgnoreCase("Y") == 0){
                    System.out.println("Changing to random");//REMOVE LATER
                    game.generateJourney_ByRandom();
                }
                
                break;
                
            case 3:
                //Get turn to change
                System.out.println();
                System.out.println("Turn being changed: ");

                System.out.println();
                System.out.print("~>: ");

                input = sc.next();

                if(input.length() >= 1)
                    op = Integer.parseInt(input);
                else
                    op = -1;
                
                if(op < 1 || op > NUM_TURNS)
                    break;
                
                //Get new event
                System.out.println();
                System.out.println("Current event: " + game.getDataGame().getJourneyTrackerTurn(op));
                System.out.println("Accepted events: R, [NUM]A, [NUM]A*");
                System.out.println("Min: " + MIN_SPAWN_ALIENS_TURN[op-1] + " Max: " + MAX_SPAWN_ALIENS_TURN[op-1]);

                System.out.println();
                System.out.print("~>: ");

                input = sc.next();
                
                if(game.getDataGame().isValid_JourneyTurn(op-1, input))
                    game.generateJourney_ByChoice(op, input.toUpperCase());
                break;
                
            case 4:
                game.confirmJourneySelection();
                break;
        }
    }
    
    public void uiJourneyPhase(){
        game.nextTurn();
    }
    
    public void uiScanningPhase(){
        if(game.getDataGame().getNewAliens().isEmpty()){
            game.scanTurn();
            return;
        }
        
        if(game.getDataGame().getDiceValue(2) > 0)
            game.placeNewAlien(game.getDataGame().getActiveNewAlien(), game.getDataGame().getDiceValue(2));
        
        int op;
        String input;
        
        Scanner sc = new Scanner(System.in);
        
        do{
            System.out.println("Scanning Phase:");
            
            int i = 0;
            
            for(Alien alien:game.getDataGame().getNewAliens()){
                if(i>0)
                    System.out.println();
                System.out.print("\t");
                if(i+1 == game.getDataGame().getActiveNewAlien())
                    System.out.print("[X] ");
                else
                    System.out.print("[ ] ");
                System.out.print("Alien #" + (i+1) + " ");
                if(alien.isInside()){
                    System.out.print(", is at Room #" + alien.getRoom().getId() + " - " + alien.getRoom().getName());
                }else{
                    System.out.print(", isn't at any Room, please select one!");
                }
                
                i++;
            }
            
            System.out.println();
            System.out.println("0 - Quit");
            System.out.println("1 - Swap Active New Alien");
            System.out.println("2 - Select Room");
            System.out.println("3 - Display Ship Structure");
            System.out.println("4 - Lock In New Aliens Placement");
            
            System.out.println();
            System.out.print("~>: ");
            
            input = sc.next();
            
            if(input.length() >= 1)
                op = Integer.parseInt(input);
            else
                op = -1;
            
        }while(op < 0 || op > 4);
        
        switch(op){
            
            case 0:
                quit = true;
                return;
            
            case 1:
                game.getDataGame().swapActiveNewAlien();
                break;
            
            case 2:
                game.rollDice();
                break;
                
            case 3:
                System.out.println(game.getDataGame().getShip().toString());
                break;
                
            case 4:
                game.confirmNewAliensPlacement();
        }
    }
    
    public void uiRestPhase(){
        int op;
        String input;
        
        Scanner sc = new Scanner(System.in);
        
        do{
            System.out.println("Rest Phase (" + game.getDataGame().getPlayer().getInspirationPoints() + "IP):");
            
            
            for(int i=0; i < NUM_CREW_MEMBERS; i++){
                if(i>0)
                    System.out.println();
                System.out.print("\t");
                if(i+1 == game.getDataGame().getActiveCrewMember())
                    System.out.print("[X] ");
                else
                    System.out.print("[ ] ");
                System.out.print("Member #" + (i+1) + ": ");
                if(game.getDataGame().getPlayer().getCrewMember(i).isInside()){
                    System.out.print(game.getDataGame().getPlayer().getCrewMember(i).getName() + ", is at Room #" + 
                            game.getDataGame().getPlayer().getCrewMember(i).getRoom().getId() + " - " + 
                            game.getDataGame().getPlayer().getCrewMember(i).getRoom().getName());
                }else{
                    System.out.print(game.getDataGame().getPlayer().getCrewMember(i).getName() + ", isn't at any Room, please select one!");
                }
            }
            
            System.out.println();
            System.out.println("0 - Quit");
            System.out.println("1 - Swap Active Member");
            System.out.println("2 - Recover Health (1 IP)");
            System.out.println("3 - Repair Hull (1 IP)");
            System.out.println("4 - Build Organic Detonator (2 IP)");
            System.out.println("5 - Increase Member Movement (4 IP)");
            System.out.println("6 - Build Particle Desperser (5 IP)");
            System.out.println("7 - Build Room Blocker (5 IP)");
            System.out.println("8 - Increase Attack Dice (6 IP)");
            System.out.println("9 - Increase Attack (6 IP)");
            System.out.println("10 - Skip Rest");
            
            System.out.println();
            System.out.print("~>: ");
            
            input = sc.next();
            
            if(input.length() >= 1)
                op = Integer.parseInt(input);
            else
                op = -1;
            
        }while(op < 0 || op > 10);
        
        switch(op){
            
            case 0:
                quit = true;
                return;
                
            case 1:
                game.getDataGame().swapActiveCrewMember();
                break;
                
            case 2:
                game.getDataGame().IP_addHealthPoint(1);
                break;
                
            case 3:
                game.getDataGame().IP_repairHull(1);
                break;
                
            case 4:
                game.getDataGame().IP_buildOrganicDetonator();
                break;
                
            case 5:
                game.getDataGame().IP_addMovement(1, game.getDataGame().getActiveCrewMember());
                break;
                
            case 6:
                game.getDataGame().IP_buildParticleDesperser();
                break;
                
            case 7:
                game.getDataGame().IP_addSealedRoomToken(1);
                break;
                
            case 8:
                game.getDataGame().IP_addAttackDie(1, game.getDataGame().getActiveCrewMember());
                break;
                
            case 9:
                game.getDataGame().IP_addValueToAttackDie(1);
                break;
                
            case 10:
                game.leaveRestPhase();
                break;
        }
    }
    
    public void uiCrewPhase(){
        
        int op;
        String input;
        
        Scanner sc = new Scanner(System.in);
        
        CrewMember[] cm = game.getDataGame().getPlayer().getCrew();
        
        do{
            System.out.println("Crew Phase");
            System.out.println(game.toString());
            System.out.println("Crew Members:");
            
             for(int i=0; i < NUM_CREW_MEMBERS; i++){
                if(i>0)
                    System.out.println();
                System.out.print("\t");
                if(i+1 == game.getDataGame().getActiveCrewMember())
                    System.out.print("[X] ");
                else
                    System.out.print("[ ] ");
                System.out.print("Member #" + (i+1) + ": ");
                if(cm[i].isInside()){
                    System.out.print(cm[i].getName() + ", is at Room #" + cm[i].getRoom().getId() + " - " + cm[i].getRoom().getName());
                }else{
                    System.out.print(cm[i].getName() + ", isn't at any Room, please select one!");
                }
            }
            
            System.out.println("\t");
            
            int i = 0;
            
            
            System.out.println();
            System.out.println("0 - Quit");
            System.out.println();
            
            System.out.println("1 - Swap Selected Member");
            System.out.println(game.getAvailableActions());
            System.out.println(game.getDataGame().getAvailableActions_Quant()+2 + " - Leave Crew Phase");
            
            System.out.println();
            System.out.print("~>: ");
            
            input = sc.next();
            
            if(input.length() >= 1)
                op = Integer.parseInt(input);
            else
                op = -1;
            
        }while(op < 0 || op > game.getDataGame().getAvailableActions_Quant() +2);
        
        if(op == 0){
            quit = true;
            return;
        }
        else if(op == 1){
            game.swapCrewMember();
            return;
        }
        else if(op > 1 && op < (game.getDataGame().getAvailableActions_Quant() + 2)){
            game.executeAction(op);
            return;
        }
        else if(op == game.getDataGame().getAvailableActions_Quant() +2){
            game.leaveCrewPhase();
        }
        else{
            return;
        }
            
    }
    
    public void uiMoveCrewMember(){
        
        int op;
        String input;
        
        Scanner sc = new Scanner(System.in);
        
        CrewMember[] cm = game.getDataGame().getPlayer().getCrew();
        
        do{
            System.out.println("Move Crew Member");
            
            System.out.println("Active Member: " + cm[game.getDataGame().getActiveCrewMember()-1].getName()+ ", " + cm[game.getDataGame().getActiveCrewMember()-1].getRoom());
 
            System.out.println();
            System.out.println("0 - Quit");
            System.out.println("1 - Select a room to move");
            System.out.print("~>: ");
            
            input = sc.next();
            
            if(input.length() >= 1)
                op = Integer.parseInt(input);
            else
                op = -1;
            
        }while(op < 0 || op > 3);
        
        switch(op){
            case 0:
                quit = true;
                return;
                
            case 1:
                int opRoom = 0;
                do{
                    System.out.println(game.getDataGame().getShip().toString());
                    System.out.print("Room: ");

                    input = sc.next();

                    if(input.length() >= 1)
                        opRoom = Integer.parseInt(input);
                    else
                        opRoom = -1;
                }while(opRoom < 0 || opRoom > 12);
                
                game.spendAbilityPoints(opRoom);
                
                break;
        }
    }
    
    public void uiPlaceTrap(){
        
        int op;
        String input;
        
        Scanner sc = new Scanner(System.in);
        
        CrewMember[] cm = game.getDataGame().getPlayer().getCrew();
        
        do{
            System.out.println("Place a Trap");
            
            System.out.println("Active Member: " + cm[game.getDataGame().getActiveCrewMember()-1].getName()+ ", " + cm[game.getDataGame().getActiveCrewMember()-1].getRoom());
            System.out.println("Organic Traps: " + game.getOrganicTrapTokens() + " Particle Dispensers: " + game.getParticleTrapTokens());
                
 
            System.out.println();
            System.out.println("0 - Quit");
            System.out.println("1 - Select a room to place a trap");
            System.out.print("~>: ");
            
            input = sc.next();
            
            if(input.length() >= 1)
                op = Integer.parseInt(input);
            else
                op = -1;
            
        }while(op < 0 || op > 1);
        
        switch(op){
            case 0:
                quit = true;
                return;
                
            case 1:
                int opRoom = 0;
                do{
                    System.out.println(game.getDataGame().getShip().toString());
                    System.out.print("Room: ");

                    input = sc.next();

                    if(input.length() >= 1)
                        opRoom = Integer.parseInt(input);
                    else
                        opRoom = -1;
                }while(opRoom < 0 || opRoom > 12);
                
                do{
                    System.out.println("Select the type of trap to place: ");
                    System.out.println("1 - Organic Detonator");
                    System.out.println("2 - Particle Dispenser");
                    System.out.print("~>: ");

                    input = sc.next();

                    if(input.length() >= 1)
                        op = Integer.parseInt(input);
                    else
                        op = -1;
                }while(op < 0 || op > 2);
                
                Trap trap;
                
                if(op == 1)
                    game.placeTrap(opRoom, new OrganicDetonator(game.getDataGame()));
                else if(op == 2)
                    game.placeTrap(opRoom, new ParticleDispenser(game.getDataGame()));
                
                
                
                break;
        }
    }
    
    public void uiDetonateParticleDispenser(){
        
        int op;
        String input;
        
        Scanner sc = new Scanner(System.in);
        
        CrewMember[] cm = game.getDataGame().getPlayer().getCrew();
        
        do{
            System.out.println("Detonate a particle dispenser");
            
            System.out.println("Active Member: " + cm[game.getDataGame().getActiveCrewMember()-1].getName()+ ", " + cm[game.getDataGame().getActiveCrewMember()-1].getRoom());
                
 
            System.out.println();
            System.out.println("0 - Quit");
            System.out.println("1 - Select a room with a particle dispenser");
            System.out.print("~>: ");
            
            input = sc.next();
            
            if(input.length() >= 1)
                op = Integer.parseInt(input);
            else
                op = -1;
            
        }while(op < 0 || op > 3);
        
        switch(op){
            case 0:
                quit = true;
                return;
                
            case 1:
                int opRoom = 0;
                do{
                    System.out.println(game.getDataGame().getShip().toString());
                    System.out.print("Room: ");

                    input = sc.next();

                    if(input.length() >= 1)
                        opRoom = Integer.parseInt(input);
                    else
                        opRoom = -1;
                }while(opRoom < 0 || opRoom > 12);
                
                game.detonateParticleDispenser(opRoom);
                
                break;
        }
    }
    
    public void uiSealRoom(){
        
        int op;
        String input;
        
        Scanner sc = new Scanner(System.in);
        
        CrewMember[] cm = game.getDataGame().getPlayer().getCrew();
        
        do{
            System.out.println("Seal a Room");
            
            System.out.println("Active Member: " + cm[game.getDataGame().getActiveCrewMember()-1].getName()+ ", " + cm[game.getDataGame().getActiveCrewMember()-1].getRoom());
                
 
            System.out.println();
            System.out.println("0 - Quit");
            System.out.println("1 - Select a room to seal");
            System.out.print("~>: ");
            
            input = sc.next();
            
            if(input.length() >= 1)
                op = Integer.parseInt(input);
            else
                op = -1;
            
        }while(op < 0 || op > 3);
        
        switch(op){
            case 0:
                quit = true;
                return;
                
            case 1:
                int opRoom = 0;
                do{
                    System.out.println(game.getDataGame().getShip().toString());
                    System.out.print("Room: ");

                    input = sc.next();

                    if(input.length() >= 1)
                        opRoom = Integer.parseInt(input);
                    else
                        opRoom = -1;
                }while(opRoom < 0 || opRoom > 12);
                
                game.spendAbilityPoints(opRoom);
                
                break;
        }
    }
     
    public void uiAlienPhase(){
        game.moveAliens();
    }

    public void run() throws IOException, ClassNotFoundException{
        
        while (!quit) {
            IStates state = game.getState();
           
            if (state instanceof Beginning) {
                uiBeginning();
            } 
            else if (state instanceof CrewSelection) {
                uiCrewSelection();
            }
            else if (state instanceof CrewPlacement) {
                uiCrewPlacement();
            }
            else if (state instanceof JourneySelection) {
                uiJourneySelection();
            }
            else if (state instanceof JourneyPhase) {
                uiJourneyPhase();
            }
            else if (state instanceof ScanningPhase) {
                uiScanningPhase();
            }
            else if (state instanceof RestPhase) {
                uiRestPhase();
            }
            else if(state instanceof CrewPhase){
                uiCrewPhase();
            }
            else if (state instanceof MoveCrewMember){
                uiMoveCrewMember();
            }
            else if (state instanceof PlaceTrap){
                uiPlaceTrap();
            }
            else if(state instanceof DetonateParticleDispenser){
                uiDetonateParticleDispenser();
            }
            else if (state instanceof SealRoom){
                uiSealRoom();
            }
            else if (state instanceof AlienPhase){
                uiAlienPhase();
            }
            else if (state instanceof DiceRolling){
                uiDiceRolling();
            }
        }
    }
}
