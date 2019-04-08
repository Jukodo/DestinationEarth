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
        
        do{
            System.out.println("Crew Selection:");
            
            for(int i=0; i < NUM_CREW_MEMBERS; i++){
                System.out.print("\tMember #" + (i+1) + ": ");
                if(cm[i] != null){
                    if(cm[i].getColor() != 0){
                        System.out.print(cm[i].getName() + "(" + COLOR[cm[i].getColor()] + ")");
                    }else{
                        System.out.print(cm[i].getName() + "(Color not selected!)");
                    }
                }else{
                    System.out.print("Class not selected!");
                }
            }
            
            System.out.println();
            System.out.println("0 - Quit");
            System.out.println("1 - Select Class");
            System.out.println("2 - Select Color");
            System.out.println("3 - Lock In Selection");
            
            System.out.println();
            System.out.print("~>: ");
            
            input = sc.next();
            
            if(input.length() >= 1)
                op = Integer.parseInt(input);
            else
                op = -1;
            
        }while(op < 0 || op > 3);


        int crewNumber, crewType, crewColor;
        switch(op){
            
            case 0:
                quit = true;
                return;
                
            case 1:
                
                do{
                    System.out.println();
                    System.out.println("What member would you like to change (#): ");
                    
                    System.out.println();
                    System.out.print("~>: ");
                    
                    input = sc.next();
                    
                    if(input.length() >= 1)
                        op = Integer.parseInt(input);
                    else
                        op = -1;
                    
                }while(op < 1 || op > NUM_CREW_MEMBERS);
                
                crewNumber = op;
                
                do{
                    crewType = 0;
                    System.out.println();
                    System.out.println("Pick the class for Member #" + crewNumber + ": ");

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
                
                game.getState().selectCrewMember(crewNumber, crewType);
                break;
                
            case 2:
                do{
                    System.out.println();
                    System.out.println("What member would you like to change (#): ");
                    
                    System.out.println();
                    System.out.print("~>: ");
                    
                    input = sc.next();
                    
                    if(input.length() >= 1)
                        op = Integer.parseInt(input);
                    else
                        op = -1;
                    
                }while(op < 1 || op > NUM_CREW_MEMBERS);
                
                crewNumber = op;
                
                do{
                    crewColor = 0;

                    System.out.println();
                    System.out.println("Pick the color for Member #" + crewNumber + ": ");

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
                
                game.getState().selectCrewMemberColor(crewNumber, crewColor);
                break;
                
            case 3:
                
                if(game.getDataGame().crewClassNotRepeated() && game.getDataGame().crewColorNotRepeated())
                    game.getState().initializeCrewMembers();
                else
                    System.out.println("Crew Class or Color is repeated!");
                
                break;
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
            else if (state instanceof DiceRolling){
                uiDiceRolling();
            }
            
        }

    }
}
