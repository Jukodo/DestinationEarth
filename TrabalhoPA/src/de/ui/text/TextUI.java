package de.ui.text;

import de.DestinationEarth;
import static de.logic.data.Constants.*;
import de.logic.data.OrganicDetonator;
import de.logic.data.ParticleDispenser;
import de.logic.data.members.ScienceOfficer;
import de.logic.states.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class TextUI {
    
    private DestinationEarth game;
    private boolean quit = false;
    private final boolean inDebug = true;

    public TextUI(DestinationEarth game) 
    {
        this.game = game;
    }
    
    public void uiBeginning() throws IOException, FileNotFoundException, ClassNotFoundException{
        Scanner sc = new Scanner(System.in);
        String input;
        int op;
            
        System.out.println("Welcome to Destination Earth, please select an option");
        
        do{
            System.out.println();
            System.out.println("0 - Quit");
            System.out.println();
            System.out.println("1 - New game");
            System.out.println("2 - Load game");
            System.out.println();
            System.out.print("~>: ");

            input = sc.next();
            
            try {
                op = Integer.parseInt(input);
            }
            catch (NumberFormatException e)
            {
               op = -1;
            }

        }while(op < 0 || op > 3);

        switch(op){

            case 0:
                quit = true;
                return;

            case 1:

                System.out.println();
                System.out.print("Please insert your player name: ");
                
                input = sc.next();

                System.out.println();
                
                if(input.length() > 0){
                    game.start(input);
                }

                return;
                
            case 2:
                game = game.loadGame();
                return;
                
            default:
                return;

        }
        
    }
    
    
    public void uiDiceRolling() throws IOException, ClassNotFoundException{
        
        Scanner sc = new Scanner(System.in);
        String input;
        int op;

        System.out.println();
        
        //Dice Info
        System.out.println(game.diceToString());
        
        System.out.println("Select a way to roll your die(s): ");

        do {
            
            System.out.println();
            System.out.println("0 - Quit");
            System.out.println();
            System.out.println("1 - Roll randomly");
            if(inDebug){
                for(int i = 0; i < game.getQuantityOfDiceToRoll(); i++){
                    System.out.println((i+2) + " - Set value for die " + (i+1));        
                }
            }
            System.out.println(game.getQuantityOfDiceToRoll()+2 + " - Continue");
            System.out.println();
            System.out.print("~>: ");

            input = sc.next();

            if(input.length() >= 1){
                try {
                    op = Integer.parseInt(input);
                 }
                 catch (NumberFormatException e)
                 {
                    op = -1;
                 }
            }
                
            else
                op = -1;

        }while(op < 0 || op > game.getQuantityOfDiceToRoll()+2);
       

        if(op == 0){
            quit = true;
            return;
        }
        else if(op == 1){
            game.rollDice(); 
            return;

        }
        else if(op > 1 && op < game.getQuantityOfDiceToRoll()+2){ 
            System.out.print("Insert the value for die " + (op-1) + ": ");

            int value = 0;

            input = sc.next();
            
            if(input.length() >= 1){
                try {
                    value = Integer.parseInt(input);
                 }
                 catch (NumberFormatException e)
                 {
                    value = 0;
                 }
            }

          game.setRollValue(op-2, value);
        }
        else{
            game.confirmRoll();
        }

    }
    
    public void uiCrewSelection() throws IOException, ClassNotFoundException{
        //REMOVE LATER
        if(inDebug){
            if(!game.getPlayer().hasAllMembers()){
                game.selectCrewMember(1, 8);
                game.selectCrewMemberColor(1, 2);
                game.selectCrewMember(2, 3);
                game.selectCrewMemberColor(2, 3);
            }
        }
        
        int op;
        String input;
        
        Scanner sc = new Scanner(System.in);
        
        do{
            System.out.println("Crew Selection:");
            
            //Crew Member Info
            System.out.println(game.crewMemberInfoToString());
            
            System.out.println();
            System.out.println("0 - Quit");
            System.out.println();
            System.out.println("1 - Swap Selected Member");
            System.out.println("2 - Select Class");
            System.out.println("3 - Select Color");
            System.out.println();
            System.out.println("4 - Lock In Selection");
            
            System.out.println();
            System.out.print("~>: ");
            
            input = sc.next();
            
            if(input.length() >= 1){
                try {
                    op = Integer.parseInt(input);
                 }
                 catch (NumberFormatException e)
                 {
                    op = -1;
                 }
            }
                
            else
                op = -1;
            
        }while(op < 0 || op > 4);


        int crewType, crewColor;
        switch(op){
            
            case 0:
                quit = true;
                return;
                
            case 1:
                game.swapActiveCrewMember();
                break;
                
            case 2:
                do{
                    crewType = 0;
                    System.out.println();
                    System.out.println(game.activeCrewMemberInfoToString());
                    System.out.println();
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
                    
                    if(input.length() >= 1){
                        try {
                            crewType = Integer.parseInt(input);
                        }
                        catch (NumberFormatException e)
                        {
                           crewType = -1;
                        }
                    }
                    
                }while(crewType < 1 || crewType > 12);
                
                game.selectCrewMember(game.getActiveCrewMember(), crewType);
                break;
                
            case 3:
                do{
                    crewColor = 0;

                    System.out.println();
                    System.out.println(game.activeCrewMemberInfoToString());
                    System.out.println();
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
                    
                    if(input.length() >= 1){
                        try {
                            crewColor = Integer.parseInt(input);
                        }
                        catch (NumberFormatException e)
                        {
                           crewColor = -1;
                        }                        
                    }
                    
                }while(crewColor < 1 || crewColor > 12);
                
                game.selectCrewMemberColor(game.getActiveCrewMember(), crewColor);
                break;
                
            case 4:
                game.confirmCrewMemberSelection();
        }
    }
    
    public void uiCrewPlacement(){
        if(game.getDiceValue(2) > 0)
            game.placeCrewMember(game.getActiveCrewMember(), game.getDiceValue(2));
        
        //REMOVE LATER
        if(inDebug){
            if(!game.getPlayer().hasAllMembersOnBoard()){
                game.placeCrewMember(1, 1);
                game.placeCrewMember(2, 2);
            }
        }
        
        int op;
        String input;
        
        Scanner sc = new Scanner(System.in);
        
        do{
            System.out.println("Crew Placement:");
            
            //Crew Member Info
            System.out.println(game.crewMemberInfoToString());
            
            System.out.println();
            System.out.println("0 - Quit");
            System.out.println();
            System.out.println("1 - Swap Selected Member");
            System.out.println("2 - Select Room");
            System.out.println("3 - Display Ship Structure");
            System.out.println();
            System.out.println("4 - Lock In Placement");
            
            System.out.println();
            System.out.print("~>: ");
            
            input = sc.next();
            
            if(input.length() >= 1){
                try {
                    op = Integer.parseInt(input);
                 }
                 catch (NumberFormatException e)
                 {
                    op = -1;
                 }
            }
            else
                op = -1;
            
        }while(op < 0 || op > 4);
        
        switch(op){
            
            case 0:
                quit = true;
                return;
                
            case 1:
                game.swapActiveCrewMember();
                break;
            
            case 2:
                game.rollDice();
                break;
            case 3:
                System.out.println(game.getShip().toString());
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
            
            //Current Journey Info
            System.out.println(game.currentJourneyToString());
            
            System.out.println();
            System.out.println("0 - Quit");
            System.out.println();
            System.out.println("1 - Load Default Journey");
            System.out.println("2 - Randomize Journey");
            System.out.println("3 - Edit Journey");
            System.out.println();
            System.out.println("4 - Lock In Journey");
            
            System.out.println();
            System.out.print("~>: ");
            
            input = sc.next();
            
            if(input.length() >= 1){
                try {
                    op = Integer.parseInt(input);
                 }
                 catch (NumberFormatException e)
                 {
                    op = -1;
                 }
            }
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
                    System.out.println("Journey Tracker set to default!");
                    game.generateJourney_ByDefault();
                }
                
                break;
                
            case 2:
                System.out.println("You will lose your current Journey. Continue? (Y/N): ");
                
                System.out.println();
                System.out.print("~>: ");
                
                input = sc.next();
                
                if(input.trim().compareToIgnoreCase("Y") == 0){
                    System.out.println("Journey Tracker set randomly!");
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

                if(input.length() >= 1){
                    try {
                        op = Integer.parseInt(input);
                    }
                    catch (NumberFormatException e)
                    {
                       op = -1;
                    }
                }
                else
                    op = -1;
                
                if(op < 1 || op > NUM_TURNS)
                    break;
                
                //Get new event
                System.out.println();
                System.out.println("Current event: " + game.getJourneyTrackerTurn(op));
                System.out.println("Accepted events: R, [NUM]A, [NUM]A*");
                System.out.println("Min: " + MIN_SPAWN_ALIENS_TURN[op-1] + " Max: " + MAX_SPAWN_ALIENS_TURN[op-1]);

                System.out.println();
                System.out.print("~>: ");

                input = sc.next();
                
                if(game.isValid_JourneyTurn(op-1, input))
                    game.generateJourney_ByChoice(op, input.toUpperCase());
                break;
                
            case 4:
                game.confirmJourneySelection();
                break;
        }
    }
    
    public void uiJourneyPhase() throws IOException{
        int op;
        String input;
        
        Scanner sc = new Scanner(System.in);
        
        do{
            System.out.println("Destination Earth:");
            
            //Game Stats
            System.out.println(game.toString());
            
            System.out.println("0 - Quit");
            System.out.println();
            System.out.println("1 - Next Turn");
            System.out.println("2 - Save Game");
            
            System.out.println();
            System.out.print("~>: ");
            
            input = sc.next();
            
            if(input.length() >= 1){
                try {
                    op = Integer.parseInt(input);
                 }
                 catch (NumberFormatException e)
                 {
                    op = -1;
                 }
            }
            else
                op = -1;
            
        }while(op < 0 || op > 2);
        
        switch(op){
            
            case 0:
                quit = true;
                return;
            
            case 1:
                game.nextTurn();
                break;
            
            case 2:
                game.saveGame();
                break;
        }
    }
    
    public void uiScanningPhase(){
        if(game.getNewAliens().isEmpty()){
            game.scanTurn();
            return;
        }
        
        if(game.getDiceValue(2) > 0)
            game.placeNewAlien(game.getActiveNewAlien(), game.getDiceValue(2));
        
        int op;
        String input;
        
        Scanner sc = new Scanner(System.in);
        
        do{
            System.out.println("Scanning Phase:");
            
            //Game Stats
            System.out.println(game.toString());
            
            //New Aliens Info
            System.out.println(game.newAliensInfoToString());
            
            System.out.println();
            System.out.println("0 - Quit");
            System.out.println();
            System.out.println("1 - Swap Active New Alien");
            System.out.println("2 - Select Room");
            System.out.println("3 - Display Ship Structure");
            System.out.println();
            System.out.println("4 - Lock In New Aliens Placement");
            
            System.out.println();
            System.out.print("~>: ");
            
            input = sc.next();
            
            if(input.length() >= 1){
                try {
                    op = Integer.parseInt(input);
                 }
                 catch (NumberFormatException e)
                 {
                    op = -1;
                 }
            }
            else
                op = -1;
            
        }while(op < 0 || op > 4);
        
        switch(op){
            
            case 0:
                quit = true;
                return;
            
            case 1:
                game.swapActiveNewAlien();
                break;
            
            case 2:
                game.rollDice();
                break;
                
            case 3:
                System.out.println(game.getShip().toString());
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
            System.out.println("Rest Phase:");
            
            //Game Stats
            System.out.println(game.toString());
            
            //Crew Members Info
            System.out.println(game.crewMemberInfoToString());
            
            System.out.println();
            System.out.println("0 - Quit");
            System.out.println();
            System.out.println(game.getAvailableInspirations());
            System.out.println(game.getAvailableInspirations_Quant()+2 + " - Leave Rest Phase");
            
            System.out.println();
            System.out.print("~>: ");
            
            input = sc.next();
            
            if(input.length() >= 1){
                try {
                    op = Integer.parseInt(input);
                 }
                 catch (NumberFormatException e)
                 {
                    op = -1;
                 }
            }
            else
                op = -1;
            
        }while(op < 0 || op > (game.getAvailableInspirations_Quant()+2));
        
        switch(op){
            
            case 0:
                quit = true;
                return;
                
            case 1:
                game.swapActiveCrewMember();
                break;
                
            case 2:
                game.IP_addHealthPoint();
                break;
                
            case 3:
                game.IP_repairHull();
                break;
                
            case 4:
                game.IP_buildOrganicDetonator();
                break;
                
            case 5:
                game.IP_addMovement(game.getActiveCrewMember());
                break;
                
            case 6:
                game.IP_buildParticleDesperser();
                break;
                
            case 7:
                game.IP_addSealedRoomToken();
                break;
                
            case 8:
                game.IP_addAttackDie(game.getActiveCrewMember());
                break;
                
            case 9:
                game.IP_addValueToAttackDie();
                break;
                
            case 10:
                if(game.getPlayer().haveAlive_RedShirt()){
                    game.sacrificeCrewMember();
                    break;
                }
                game.leaveRestPhase();
                break;
            case 11:
                game.leaveRestPhase();
                break;
        }
    }
    
    public void uiCrewPhase(){
        
        int op;
        String input;
        
        Scanner sc = new Scanner(System.in);
        
        do{
            System.out.println("Crew Phase");
            
            //Game Stats
            System.out.println(game.toString());
            
            //Crew Members Info
            System.out.println(game.crewMemberInfoToString());
            
            System.out.println();
            System.out.println("0 - Quit");
            System.out.println();
            
            System.out.println("1 - Swap Selected Member");
            System.out.println(game.getAvailableActions());
            System.out.println(game.getAvailableActions_Quant()+2 + " - Leave Crew Phase");
            
            System.out.println();
            System.out.print("~>: ");
            
            input = sc.next();
            
            if(input.length() >= 1){
                try {
                    op = Integer.parseInt(input);
                 }
                 catch (NumberFormatException e)
                 {
                    op = -1;
                 }
            }
            else
                op = -1;
            
        }while(op < 0 || op > game.getAvailableActions_Quant() +2);
        
        if(op == 0){
            quit = true;
            return;
        }
        else if(op == 1){
            game.swapCrewMember();
            return;
        }
        else if(op > 1 && op < (game.getAvailableActions_Quant() + 2)){
            game.executeAction(op);
            return;
        }
        else if(op == game.getAvailableActions_Quant() +2){
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
        
        do{
            System.out.println("Move Crew Member");
            
            //Active Crew Member Info
            System.out.println(game.activeCrewMemberInfoToString());
            
            System.out.println();
            System.out.println("0 - Quit");
            System.out.println("1 - Select a room to move");
            System.out.print("~>: ");
            
            input = sc.next();
            
            if(input.length() >= 1){
                try {
                    op = Integer.parseInt(input);
                 }
                 catch (NumberFormatException e)
                 {
                    op = -1;
                 }
            }
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
                    System.out.println(game.getShip().toString());
                    System.out.print("Room: ");

                    input = sc.next();

                    if(input.length() >= 1){
                        try {
                            opRoom = Integer.parseInt(input);
                         }
                         catch (NumberFormatException e)
                         {
                            opRoom = -1;
                         }
                    }
                    else
                        opRoom = -1;
                }while(opRoom < 0 || opRoom > 12);
                
                game.moveCrewMember(opRoom);
                
                break;
        }
    }
    
    public void uiAttackAliens(){
        
        int op;
        String input;
        
        Scanner sc = new Scanner(System.in);
        
        
        if(game.getPlayer().getCrewMember(game.getActiveCrewMember()-1) instanceof ScienceOfficer){
             do{
                System.out.println("Attack aliens");

                System.out.println();
                System.out.println("0 - Quit");
                System.out.println("1 - Select a room with aliens");
                System.out.print("~>: ");

                input = sc.next();

                if(input.length() >= 1){
                    try {
                        op = Integer.parseInt(input);
                    }
                    catch (NumberFormatException e)
                    {
                        op = -1;
                    }
                }
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
                        System.out.println(game.getShip().toString());
                        System.out.print("Room: ");

                        input = sc.next();

                        if(input.length() >= 1){
                            try {
                                opRoom = Integer.parseInt(input);
                            }
                            catch (NumberFormatException e)
                            {
                                opRoom = -1;
                            }
                        }
                        else
                            opRoom = -1;
                    }while(opRoom < 0 || opRoom > 12);

                    game.attackAliens(opRoom);

                    break;
            }
        }
        else{
            game.attackAliens(0); //Quarto atual
        }
 
    }
    
    public void uiPlaceTrap(){
        
        int op;
        String input;
        
        Scanner sc = new Scanner(System.in);
        
        do{
            System.out.println("Place a Trap");
            
            //Active Crew Member Info
            System.out.println(game.activeCrewMemberInfoToString());
            
            //Trap Tokens Info
            System.out.println(game.trapTokensToString());
                
 
            System.out.println();
            System.out.println("0 - Quit");
            System.out.println();
            System.out.println("1 - Place an Organic Detonator");
            System.out.println("2 - Place a Particle Dispenser");
            System.out.print("~>: ");
            
            input = sc.next();
            
            if(input.length() >= 1){
                try {
                    op = Integer.parseInt(input);
                }
                catch (NumberFormatException e)
                {
                    op = -1;
                }
            }
            else
                op = -1;
            
        }while(op < 0 || op > 2);
        
        switch(op){
            case 0:
                quit = true;
                return;
                
            case 1:
               game.placeTrap(new OrganicDetonator(game.getDataGame()));
               return;
               
            case 2:
                game.placeTrap(new ParticleDispenser(game.getDataGame()));
                return;
        }
    }
    
    public void uiDetonateParticleDispenser(){
        
        int op;
        String input;
        
        Scanner sc = new Scanner(System.in);
        
        do{
            System.out.println("Detonate a particle dispenser");
            
            //Active Crew Member Info
            System.out.println(game.activeCrewMemberInfoToString());
 
            System.out.println();
            System.out.println("0 - Quit");
            System.out.println();
            System.out.println("1 - Select a room with a particle dispenser");
            System.out.print("~>: ");
            
            input = sc.next();
            
            if(input.length() >= 1){
                try {
                    op = Integer.parseInt(input);
                }
                catch (NumberFormatException e)
                {
                    op = -1;
                }
            }
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
                    System.out.println(game.getShip().toString());
                    System.out.print("Room: ");

                    input = sc.next();

                    if(input.length() >= 1){
                        try {
                            opRoom = Integer.parseInt(input);
                        }
                        catch (NumberFormatException e)
                        {
                            opRoom = -1;
                        }
                    }
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
        
        do{
            System.out.println("Seal a Room");

            //Active Crew Member Info
            System.out.println(game.activeCrewMemberInfoToString());
            
            System.out.println(game.sealTokensToString());
 
            System.out.println();
            System.out.println("0 - Quit");
            System.out.println("1 - Select a room to seal");
            System.out.print("~>: ");
            
            input = sc.next();
            
            if(input.length() >= 1){
                try {
                    op = Integer.parseInt(input);
                }
                catch (NumberFormatException e)
                {
                    op = -1;
                }
            }
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
                    System.out.println(game.getShip().toString());
                    System.out.print("Room: ");

                    input = sc.next();

                    if(input.length() >= 1){
                        try {
                            opRoom = Integer.parseInt(input);
                        }
                        catch (NumberFormatException e)
                        {
                            opRoom = -1;
                        }
                    }
                    else
                        opRoom = -1;
                }while(opRoom < 0 || opRoom > 12);
                
                game.sealRoom(opRoom);
                
                break;
        }
    }
     
    public void uiAlienPhase(){
        game.moveAliens();
    }
    
    public void uiGameOver(){
        
        int op;
        String input;
        
        Scanner sc = new Scanner(System.in);
        
        do{
            System.out.println("Game Over!");
            
            System.out.println();
            System.out.println("0 - Quit");
            System.out.println("1 - Replay");
            System.out.print("~>: ");
            
            input = sc.next();
            
            if(input.length() >= 1){
                try {
                    op = Integer.parseInt(input);
                }
                catch (NumberFormatException e)
                {
                    op = -1;
                }
            }
            else
                op = -1;
            
        }while(op < 0 || op > 1);
        
        switch(op){
            case 0:
                quit = true;
                return;
                
            case 1:
                game.playAgain();
                break;
        }
    }
    
    public void writeLogs(){
        for(String log:game.getLogs()){
            System.out.println(log);
        }
        game.clearLogs();
    }

    public void run() throws IOException, ClassNotFoundException{
        
        while (!quit) {
            IStates state = game.getState();
           
            writeLogs();
            
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
            else if(state instanceof AttackAliens){
                uiAttackAliens();
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
            else if (state instanceof GameOver){
                uiGameOver();
            }
            else if (state instanceof DiceRolling){
                uiDiceRolling();
            }
        }
    }
}
