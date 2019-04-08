/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.ui.text;

import de.DestinationEarth;
import de.logic.states.*;
import java.io.IOException;
import java.util.Scanner;


/**
 *
 * @author Tiago
 */
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
        
       
        //}
        
    }
    
    public void uiCrewSelection() throws IOException, ClassNotFoundException{
        
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
