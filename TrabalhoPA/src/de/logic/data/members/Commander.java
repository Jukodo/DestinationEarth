package de.logic.data.members;

import de.logic.data.DataGame;

public class Commander extends CrewMember {
    
    public Commander(DataGame dataGame) {
        super(dataGame, 1, 1);
    }
    
    public Commander(DataGame dataGame, int color) {
        super(dataGame, 1, 1, color);
    }

    @Override
    public String getName() {
        return "Commander";
    }
}
