package de;

import de.ui.text.TextUI;
import java.io.IOException;

public class TrabalhoPA {

    public static void main(String[] args) {
        TextUI textUI = new TextUI(new DestinationEarth());
        try { 
            textUI.run();
        } catch (IOException | ClassNotFoundException ex) {
            
        }
    }
    
}
