package de.logic.data.members;

import de.logic.data.DataGame;
import java.io.Serializable;

public class SecurityOfficer extends CrewMember implements Serializable{
    
    public SecurityOfficer(DataGame dataGame) {
        super(dataGame, 1, 2);
    }
    
    public SecurityOfficer(DataGame dataGame, int color) {
        super(dataGame, 1, 2, color);
    }

    @Override
    public String getName() {
        return "SecurityOfficer";
    }
}
