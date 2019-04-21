package de.logic.data.members;

import de.logic.data.DataGame;
import java.io.Serializable;

public class NavigationOfficer extends CrewMember implements Serializable{
    
    public NavigationOfficer(DataGame dataGame) {
        super(dataGame, 2, 1);
    }
    
    public NavigationOfficer(DataGame dataGame, int color) {
        super(dataGame, 2, 1, color);
    }

    @Override
    public String getName() {
        return "NavigationOfficer";
    }
}
