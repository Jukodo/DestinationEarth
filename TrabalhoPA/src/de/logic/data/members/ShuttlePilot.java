package de.logic.data.members;

import de.logic.data.DataGame;

public class ShuttlePilot extends CrewMember{
    
    public ShuttlePilot(DataGame dataGame) {
        super(dataGame, 1, 1);
    }
    
    public ShuttlePilot(DataGame dataGame, int color) {
        super(dataGame, 1, 1, color);
    }
    
}
