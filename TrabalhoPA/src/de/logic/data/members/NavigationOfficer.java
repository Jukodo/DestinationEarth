package de.logic.data.members;

import de.logic.data.DataGame;

public class NavigationOfficer extends CrewMember{
    
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
