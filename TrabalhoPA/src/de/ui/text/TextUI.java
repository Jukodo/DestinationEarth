package de.ui.text;

import de.DestinationEarth;
import static de.logic.data.Constants.*;
import de.logic.data.members.CrewMember;
import de.logic.states.*;
import java.io.IOException;
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
        int op;
        String input;
        
        Scanner sc = new Scanner(System.in);
        
        CrewMember[] cm = game.getDataGame().getPlayer().getCrew();
        
        //REMOVE LATER
        if(inDebug){
            game.getDataGame().selectCrewMember(1, 1);
            game.getDataGame().selectCrewMemberColor(1, 2);
            game.getDataGame().selectCrewMember(2, 2);
            game.getDataGame().selectCrewMemberColor(2, 3);
        }
        
        do{
            System.out.println("Crew Selection:");
            
            for(int i=0; i < NUM_CREW_MEMBERS; i++){
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

                    System.out.println("1  - White (Unselect)");
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
                game.initializeCrewMemberSelection();
        }
    }
    
    public void uiCrewPlacement(){
        int dicesValue = 0;
                
        for(int i=0; i<2; i++)
            dicesValue += game.getDataGame().getDieValue(i);
        
        if(dicesValue > 0 && dicesValue <= NUM_ROOMS){
            game.placeCrewMember(game.getDataGame().getActiveCrewMember(), dicesValue);
            game.getDataGame().resetDices();
        }
        
        int op;
        String input;
        
        Scanner sc = new Scanner(System.in);
        
        CrewMember[] cm = game.getDataGame().getPlayer().getCrew();
        
        do{
            System.out.println("Crew Placement:");
            
            for(int i=0; i < NUM_CREW_MEMBERS; i++){
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
                game.initializeCrewMemberPlacement();
        }
    }

    public void run() throws IOException, ClassNotFoundException 
    {

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
            else if (state instanceof DiceRolling){
                uiDiceRolling();
            }
            
        }

    }
}
