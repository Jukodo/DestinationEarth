package de.logic.data.members;

import de.logic.data.DataGame;

public class TransporterChief extends CrewMember {
    
    public TransporterChief(DataGame dataGame) {
        super(dataGame, 0, 1);
    }
    
    public TransporterChief(DataGame dataGame, int color) {
        super(dataGame, 0, 1, color);
    }
    
}
