package de.logic.data.members;

import de.logic.data.DataGame;

public class Engineer extends CrewMember{
    
    public Engineer(DataGame dataGame) {
        super(dataGame, 1, 1);
    }
    
    public Engineer(DataGame dataGame, int color) {
        super(dataGame, 1, 1, color);
    }

    @Override
    public String getName() {
        return "Engineer";
    }
}
