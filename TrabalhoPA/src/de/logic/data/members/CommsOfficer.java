package de.logic.data.members;

import de.logic.data.DataGame;

public class CommsOfficer extends CrewMember {
    
    public CommsOfficer(DataGame dataGame) {
        super(dataGame, 1, 1);
    }
    
    public CommsOfficer(DataGame dataGame, int color) {
        super(dataGame, 1, 1, color);
    }

}
