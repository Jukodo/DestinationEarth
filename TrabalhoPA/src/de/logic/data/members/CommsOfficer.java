package de.logic.data.members;

import de.logic.data.DataGame;
import java.io.Serializable;

public class CommsOfficer extends CrewMember implements Serializable{
    
    public CommsOfficer(DataGame dataGame) {
        super(dataGame, 1, 1);
    }
    
    public CommsOfficer(DataGame dataGame, int color) {
        super(dataGame, 1, 1, color);
    }

    @Override
    public String getName() {
        return "CommsOfficer";
    }
}
