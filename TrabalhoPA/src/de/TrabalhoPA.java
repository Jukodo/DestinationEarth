/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de;

import de.ui.text.TextUI;
import java.io.IOException;



/**
 *
 * @author Tiago
 */
public class TrabalhoPA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TextUI textUI = new TextUI(new DestinationEarth());
        try { 
            textUI.run();
        } catch (IOException | ClassNotFoundException ex) {
          //...
        }
        //...
        
       
    }
    
}
