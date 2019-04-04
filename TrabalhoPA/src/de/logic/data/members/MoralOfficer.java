package de.logic.data.members;

import de.logic.data.DataGame;

public class MoralOfficer extends CrewMember{
    
    public MoralOfficer(DataGame dataGame) {
        super(dataGame, 1, 1);
    }
    
    public MoralOfficer(DataGame dataGame, int color) {
        super(dataGame, 1, 1, color);
    }
    
}

