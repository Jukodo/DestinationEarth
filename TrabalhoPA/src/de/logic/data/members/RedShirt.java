package de.logic.data.members;

import de.logic.data.DataGame;

public class RedShirt extends CrewMember{

    public RedShirt(DataGame dataGame) {
        super(dataGame, 1, 1);
    }

    public RedShirt(DataGame dataGame, int color) {
        super(dataGame, 1, 1, color);
    }
     
}
