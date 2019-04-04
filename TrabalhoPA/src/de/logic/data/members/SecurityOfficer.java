package de.logic.data.members;

import de.logic.data.DataGame;

public class SecurityOfficer extends CrewMember {
    
    public SecurityOfficer(DataGame dataGame) {
        super(dataGame, 1, 2);
    }
    
    public SecurityOfficer(DataGame dataGame, int color) {
        super(dataGame, 1, 2, color);
    }
    
}
