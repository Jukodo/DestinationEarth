package de.logic.data.members;

import de.logic.data.DataGame;
import java.io.Serializable;

public class TransporterChief extends CrewMember implements Serializable{
    
    public TransporterChief(DataGame dataGame) {
        super(dataGame, 0, 1);
    }
    
    public TransporterChief(DataGame dataGame, int color) {
        super(dataGame, 0, 1, color);
    }

    @Override
    public String getName() {
        return "TransporterChief";
    }
}
