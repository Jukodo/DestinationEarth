package de.logic.data.members;

import de.logic.data.DataGame;

public class Captain extends CrewMember{
    
    public Captain(DataGame dataGame) {
        super(dataGame, 1, 1);
    }
    
    public Captain(DataGame dataGame, int color) {
        super(dataGame, 1, 1, color);
    }
    
}
