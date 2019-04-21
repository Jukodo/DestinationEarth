package de.logic.data.members;

import de.logic.data.DataGame;
import java.io.Serializable;

public class ShuttlePilot extends CrewMember implements Serializable{
    
    public ShuttlePilot(DataGame dataGame) {
        super(dataGame, 1, 1);
    }
    
    public ShuttlePilot(DataGame dataGame, int color) {
        super(dataGame, 1, 1, color);
    }

    @Override
    public String getName() {
        return "ShuttlePilot";
    }
}
