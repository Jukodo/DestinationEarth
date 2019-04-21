package de.logic.data.members;

import de.logic.data.DataGame;
import java.io.Serializable;

public class Captain extends CrewMember implements Serializable{
    
    public Captain(DataGame dataGame) {
        super(dataGame, 1, 1);
    }
    
    public Captain(DataGame dataGame, int color) {
        super(dataGame, 1, 1, color);
    }

    @Override
    public String getName() {
        return "Captain";
    }
}
