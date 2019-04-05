/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.ui.text;

import de.DestinationEarth;
import de.logic.states.*;
import java.io.IOException;


/**
 *
 * @author Tiago
 */
public class TextUI {
    
    private DestinationEarth game;
    private boolean quit = false;

    public TextUI(DestinationEarth game) 
    {
        this.game = game;
    }
    
    public void uiBeginning() throws IOException, ClassNotFoundException 
    {
        System.out.println(game);
    } 

    public void run() throws IOException, ClassNotFoundException 
    {

        //while (!quit) {
            
            IStates state = game.getState();
           
            if (state instanceof Beginning) {
                uiBeginning();
            } /*else if (state instanceof AwaitPlacement) {
                uiAWaitPlacement();
            } else if (state instanceof AwaitReturn) {
                uiAWaitReturn();
            }*/
            
        //}

    }
}
