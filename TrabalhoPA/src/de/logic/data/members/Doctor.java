package de.logic.data.members;

import de.logic.data.DataGame;

public class Doctor extends CrewMember {

    public Doctor(DataGame dataGame) {
        super(dataGame, 1, 1);
    }
    
    public Doctor(DataGame dataGame, int color) {
        super(dataGame, 1, 1, color);
    }

    @Override
    public String getName() {
        return "Doctor";
    }
}
