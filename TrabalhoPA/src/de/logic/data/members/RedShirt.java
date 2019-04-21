package de.logic.data.members;

import de.logic.data.DataGame;
import java.io.Serializable;

public class RedShirt extends CrewMember implements Serializable{

    public RedShirt(DataGame dataGame) {
        super(dataGame, 1, 1);
    }

    public RedShirt(DataGame dataGame, int color) {
        super(dataGame, 1, 1, color);
    }

    @Override
    public String getName() {
        return "RedShirt";
    }
}
